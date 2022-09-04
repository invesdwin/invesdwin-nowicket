package de.invesdwin.nowicket.component.header.render.preact;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestHandler;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTargetListenerCollection;
import org.apache.wicket.core.request.handler.AbstractPartialPageRequestHandler;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.feedback.FeedbackDelay;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.page.PartialPageUpdate;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.lang.Classes;

import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.collections.Collections;
import de.invesdwin.util.lang.reflection.Reflections;

@NotThreadSafe
public class PreactPartialPageUpdate {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(PartialPageUpdate.class);
    private static final MethodHandle GET_UPDATE_METHOD_HANDLE;
    private static final MethodHandle WRITE_HEADER_CONTRIBUTION_METHOD_HANDLE;

    static {
        GET_UPDATE_METHOD_HANDLE = newGetUpdateMethodHandle();
        WRITE_HEADER_CONTRIBUTION_METHOD_HANDLE = newWriteHeaderContributionMethodHandle();
    }

    private final IPartialPageRequestHandler delegate;
    private final Page page;
    private final WebResponse response;
    private final PartialPageUpdate delegateUpdate;
    private final Map<String, Component> markupIdToComponent = new LinkedHashMap<>();
    private final ResponseBuffer bodyBuffer;
    private boolean componentsFrozen;

    public PreactPartialPageUpdate(final IPartialPageRequestHandler delegate) {
        this.delegate = delegate;
        this.page = RequestCycles.getPage();
        this.response = (WebResponse) page.getResponse();
        this.bodyBuffer = new ResponseBuffer(response);

        final AbstractPartialPageRequestHandler cDelegate = (AbstractPartialPageRequestHandler) delegate;
        try {
            this.delegateUpdate = (PartialPageUpdate) GET_UPDATE_METHOD_HANDLE.invoke(cDelegate);
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static MethodHandle newGetUpdateMethodHandle() {
        try {
            final Method getUpdate = AbstractPartialPageRequestHandler.class.getDeclaredMethod("getUpdate");
            Reflections.makeAccessible(getUpdate);
            final Lookup lookup = MethodHandles.lookup();
            return lookup.unreflect(getUpdate);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static MethodHandle newWriteHeaderContributionMethodHandle() {
        try {
            final Method writeHeaderContribution = PartialPageUpdate.class.getDeclaredMethod("writeHeaderContribution",
                    Response.class, Component.class);
            Reflections.makeAccessible(writeHeaderContribution);
            final Lookup lookup = MethodHandles.lookup();
            return lookup.unreflect(writeHeaderContribution);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void onBeforeRespond(final AWebApplication app) {
        if (delegate instanceof AjaxRequestHandler) {
            final AjaxRequestHandler cDelegate = (AjaxRequestHandler) delegate;
            final AjaxRequestTargetListenerCollection listeners = app.getAjaxRequestTargetListeners();
            if (listeners != null) {
                for (final AjaxRequestTarget.IListener listener : listeners) {
                    listener.onBeforeRespond(markupIdToComponent, cDelegate);
                }
            }
        }
    }

    private void onAfterRespond(final AWebApplication app) {
        if (delegate instanceof AjaxRequestHandler) {
            final AjaxRequestHandler cDelegate = (AjaxRequestHandler) delegate;
            final AjaxRequestTargetListenerCollection listeners = app.getAjaxRequestTargetListeners();

            // invoke onafterresponse event on listeners
            if (listeners != null) {
                final Map<String, Component> components = Collections.unmodifiableMap(markupIdToComponent);
                for (final AjaxRequestTarget.IListener listener : listeners) {
                    listener.onAfterRespond(components, cDelegate);
                }
            }
        }
    }

    private void writeComponents(final AWebApplication app) {
        final String encoding = app.getRequestCycleSettings().getResponseRequestEncoding();

        componentsFrozen = true;

        final List<Component> toBeWritten = new ArrayList<>(markupIdToComponent.size());

        // delay preparation of feedbacks after all other components
        try (FeedbackDelay delay = new FeedbackDelay(RequestCycle.get())) {
            for (final Component component : markupIdToComponent.values()) {
                if (!containsAncestorFor(component) && prepareComponent(component)) {
                    toBeWritten.add(component);
                }
            }

            // .. now prepare all postponed feedbacks
            delay.beforeRender();
        }

        // write components
        for (final Component component : toBeWritten) {
            writeComponent(component.getAjaxRegionMarkupId(), component, encoding);
        }
    }

    protected void writeComponent(final String markupId, final Component component, final String encoding) {
        // substitute our encoding response for the old one so we can capture
        // component's markup in a manner safe for transport inside CDATA block
        final Response oldResponse = RequestCycle.get().setResponse(bodyBuffer);

        try {
            // render any associated headers of the component
            writeHeaderContribution(component);

            bodyBuffer.reset();

            try {
                component.renderPart();
            } catch (final RuntimeException e) {
                bodyBuffer.reset();
                throw e;
            }
        } finally {
            // Restore original response
            RequestCycle.get().setResponse(oldResponse);
        }

        writeComponent(markupId, bodyBuffer.getContents());

        bodyBuffer.reset();
    }

    private void writeHeaderContribution(final Component component) {
        try {
            WRITE_HEADER_CONTRIBUTION_METHOD_HANDLE.invoke(delegateUpdate, response, component);
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void writeComponent(final String markupId, final CharSequence contents) {
        final StringBuilder js = new StringBuilder("window.preactRenderHtml(`");
        js.append(contents);
        js.append("`, '");
        js.append(markupId);
        js.append("')");
        //need to prepend or else onClick handlers will fail to get registered
        delegate.prependJavaScript(js);
    }

    private boolean containsAncestorFor(final Component component) {
        Component cursor = component.getParent();
        while (cursor != null) {
            if (markupIdToComponent.containsValue(cursor)) {
                return true;
            }
            cursor = cursor.getParent();
        }
        return false;
    }

    /**
     * Prepare a single component
     *
     * @param component
     *            the component to prepare
     * @return whether the component was prepared
     */
    private boolean prepareComponent(final Component component) {
        if (component.getRenderBodyOnly()) {
            throw new IllegalStateException(
                    "A partial update is not possible for a component that has renderBodyOnly enabled. Component: "
                            + component.toString());
        }

        component.setOutputMarkupId(true);

        // Initialize temporary variables
        final Page parentPage = component.findParent(Page.class);
        if (parentPage == null) {
            // dont throw an exception but just ignore this component, somehow
            // it got removed from the page.
            //CHECKSTYLE:OFF
            LOG.warn("Component '{}' not rendered because it was already removed from page", component);
            //CHECKSTYLE:ON
            return false;
        }

        try {
            component.beforeRender();
        } catch (final RuntimeException e) {
            bodyBuffer.reset();
            throw e;
        }

        return true;
    }

    public void respond(final IRequestCycle requestCycle) {
        render();
        delegate.respond(requestCycle);
    }

    public void render() {
        final AWebApplication app = (AWebApplication) page.getApplication();
        onBeforeRespond(app);
        writeComponents(app);
        onAfterRespond(app);
    }

    public void add(final Component component, final String markupId) {
        Args.notEmpty(markupId, "markupId");
        Args.notNull(component, "component");

        if (component instanceof Page) {
            if (component != page) {
                throw new IllegalArgumentException("Cannot add another page");
            }
        } else {
            final Page pageOfComponent = component.findParent(Page.class);
            if (pageOfComponent == null) {
                // no longer on page - log the error but don't block the user of the application
                // (which was the behavior in Wicket <= 7).
                //CHECKSTYLE:OFF
                LOG.warn("Component '{}' not cannot be updated because it was already removed from page", component);
                //CHECKSTYLE:ON
                return;
            } else if (pageOfComponent != page) {
                // on another page
                throw new IllegalArgumentException(
                        "Component " + component.toString() + " cannot be updated because it is on another page.");
            }

            if (component instanceof AbstractRepeater) {
                throw new IllegalArgumentException("Component " + Classes.name(component.getClass())
                        + " is a repeater and cannot be added to a partial page update directly. "
                        + "Instead add its parent or another markup container higher in the hierarchy.");
            }
        }

        if (componentsFrozen) {
            throw new IllegalStateException("A partial update of the page is being rendered, component "
                    + component.toString() + " can no longer be added");
        }

        component.setMarkupId(markupId);
        markupIdToComponent.put(markupId, component);
    }

}
