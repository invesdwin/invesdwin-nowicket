package de.invesdwin.nowicket.component.header.render.preact;

import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * Adapted from org.apache.wicket.ajax.AjaxRequestHandler and org.apache.wicket.page.PartialPageUpdate
 * 
 * Use addPreact methods to update components with the preact diffing algorithm, which might improve client side
 * rendering performance on large table updates using ajax or websocket (due to less dom updates). Though some
 * components might not work well with this mode of updating. That is why we don't make this update mode the default for
 * the application.
 */
@NotThreadSafe
public final class PreactPartialPageRequestHandler implements IPartialPageRequestHandler {

    private static final MetaDataKey<PreactPartialPageRequestHandler> KEY_PREACT_HANDLER = new MetaDataKey<PreactPartialPageRequestHandler>() {
    };
    private final IPartialPageRequestHandler delegate;
    private PreactPartialPageUpdate peactUpdate;

    private PreactPartialPageRequestHandler(final IPartialPageRequestHandler delegate) {
        this.delegate = delegate;
    }

    public IPartialPageRequestHandler getDelegate() {
        return delegate;
    }

    @Override
    public IRequestablePage getPage() {
        return delegate.getPage();
    }

    @Override
    public Integer getPageId() {
        return delegate.getPageId();
    }

    @Override
    public boolean isPageInstanceCreated() {
        return delegate.isPageInstanceCreated();
    }

    @Override
    public Integer getRenderCount() {
        return delegate.getRenderCount();
    }

    @Override
    public Class<? extends IRequestablePage> getPageClass() {
        return delegate.getPageClass();
    }

    @Override
    public PageParameters getPageParameters() {
        return delegate.getPageParameters();
    }

    @Override
    public void addChildren(final MarkupContainer parent, final Class<?> childCriteria) {
        delegate.addChildren(parent, childCriteria);
    }

    @Override
    public void add(final Component... components) {
        delegate.add(components);
    }

    @Override
    public void add(final Component component, final String markupId) {
        delegate.add(component, markupId);
    }

    public void addChildrenPreact(final MarkupContainer parent, final Class<?> childCriteria) {
        Args.notNull(parent, "parent");
        Args.notNull(childCriteria, "childCriteria");

        parent.visitChildren(childCriteria, new IVisitor<Component, Void>() {
            @Override
            public void component(final Component component, final IVisit<Void> visit) {
                addPreact(component);
                visit.dontGoDeeper();
            }
        });
    }

    public void addPreact(final Component... components) {
        for (final Component component : components) {
            Args.notNull(component, "component");

            if (!component.getOutputMarkupId() && !(component instanceof Page)) {
                throw new IllegalArgumentException(
                        "cannot update component that does not have setOutputMarkupId property set to true. Component: "
                                + component.toString());
            }

            addPreact(component, component.getMarkupId());
        }
    }

    public void addPreact(final Component component, final String markupId) {
        getPreactUpdate().add(component, markupId);
    }

    @Override
    public void respond(final IRequestCycle requestCycle) {
        if (peactUpdate != null) {
            peactUpdate.respond(requestCycle);
        } else {
            delegate.respond(requestCycle);
        }
    }

    public void render() {
        if (peactUpdate != null) {
            peactUpdate.render();
            peactUpdate = null;
        }
    }

    private PreactPartialPageUpdate getPreactUpdate() {
        if (peactUpdate == null) {
            peactUpdate = new PreactPartialPageUpdate(delegate);
        }
        return peactUpdate;
    }

    @Override
    public void appendJavaScript(final CharSequence javascript) {
        delegate.appendJavaScript(javascript);
    }

    @Override
    public void prependJavaScript(final CharSequence javascript) {
        delegate.prependJavaScript(javascript);
    }

    @Override
    public void focusComponent(final Component component) {
        delegate.focusComponent(component);
    }

    @Override
    public Collection<? extends Component> getComponents() {
        return delegate.getComponents();
    }

    @Override
    public IHeaderResponse getHeaderResponse() {
        return delegate.getHeaderResponse();
    }

    public static PreactPartialPageRequestHandler of(final RequestCycle requestCycle,
            final IPartialPageRequestHandler handler) {
        if (handler == null) {
            return null;
        } else if (handler instanceof PreactPartialPageRequestHandler) {
            return (PreactPartialPageRequestHandler) handler;
        } else {
            PreactPartialPageRequestHandler preactHandler = requestCycle.getMetaData(KEY_PREACT_HANDLER);
            if (preactHandler == null) {
                preactHandler = new PreactPartialPageRequestHandler(handler);
                requestCycle.setMetaData(KEY_PREACT_HANDLER, preactHandler);
            }
            return preactHandler;
        }
    }

}
