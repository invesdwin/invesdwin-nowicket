package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class I18nModel extends AbstractReadOnlyModel<String> {

    private static final ThreadLocal<Boolean> NESTED_PAGE_RETRIEVAL = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    private final Component component;
    private final IModel<?> delegate;
    private final Class<? extends Component> componentClass;

    public I18nModel(final Component component, final IModel<?> delegate) {
        this.componentClass = null;
        this.component = component;
        this.delegate = delegate;
    }

    public I18nModel(final Component component, final String property) {
        this(component, Model.of(property));
    }

    /**
     * Uses the page from the RequestCycle to lookup the component.
     */
    public I18nModel(final Class<? extends Component> componentClass, final String property) {
        this(componentClass, Model.of(property));
    }

    /**
     * Uses the page from the RequestCycle to lookup the component.
     */
    public I18nModel(final Class<? extends Component> componentClass, final Model<?> delegate) {
        this.componentClass = componentClass;
        this.component = null;
        this.delegate = delegate;
    }

    @Override
    public String getObject() {
        final Object modelObject = delegate.getObject();
        if (modelObject == null) {
            return null;
        } else {
            final String message = modelObject.toString();
            if (message == null) {
                return null;
            }
            String localizedMessage;
            try {
                final Component usedComponent = getComponent();
                localizedMessage = new StringResourceModel(message, usedComponent, HtmlContext.getModel(usedComponent))
                        .setDefaultValue(message).getObject();
            } catch (final Throwable e) {
                localizedMessage = message;
            }
            return localizedMessage;
        }
    }

    private Component getComponent() {
        if (component == null && !NESTED_PAGE_RETRIEVAL.get()) {
            NESTED_PAGE_RETRIEVAL.set(true);
            try {
                final IPageRequestHandler handler = PageRequestHandlerTracker.getLastHandler(RequestCycle.get());
                if (handler != null) {
                    if (componentClass != null) {
                        final Component foundComponent = Components.findComponent(componentClass,
                                (Page) handler.getPage());
                        if (foundComponent != null) {
                            return foundComponent;
                        }
                    }
                    return (Page) handler.getPage();
                }
            } finally {
                NESTED_PAGE_RETRIEVAL.set(false);
            }
        }
        return component;
    }

    public static void wrapExistingModel(final Component component) {
        component.setDefaultModel(new I18nModel(component, component.getDefaultModel()));
    }

}
