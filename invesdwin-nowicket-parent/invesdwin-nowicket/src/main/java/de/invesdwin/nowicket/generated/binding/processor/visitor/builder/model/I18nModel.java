package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.util.MissingResourceException;

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

@NotThreadSafe
public class I18nModel extends AbstractReadOnlyModel<String> {

    private final Component component;
    private final IModel<?> delegate;

    public I18nModel(final Component component, final IModel<?> delegate) {
        this.component = component;
        this.delegate = delegate;
    }

    public I18nModel(final Component component, final String property) {
        this.component = component;
        this.delegate = Model.of(property);
    }

    /**
     * Uses the page from the RequestCycle as component, thus cannot retrieve properties from nested components.
     */
    public I18nModel(final String property) {
        this.component = null;
        this.delegate = Model.of(property);
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
            } catch (final MissingResourceException e) {
                localizedMessage = message;
            }
            return localizedMessage;
        }
    }

    private Component getComponent() {
        if (component == null) {
            final IPageRequestHandler handler = PageRequestHandlerTracker.getLastHandler(RequestCycle.get());
            if (handler != null) {
                return (Page) handler.getPage();
            }
        }
        return component;
    }

    public static void wrapExistingModel(final Component component) {
        component.setDefaultModel(new I18nModel(component, component.getDefaultModel()));
    }

}
