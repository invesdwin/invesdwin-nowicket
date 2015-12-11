package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;

@NotThreadSafe
public class I18nDelegateModel extends AbstractReadOnlyModel<String> {

    private final Component component;
    private final IModel<?> delegate;

    public I18nDelegateModel(final Component component, final IModel<?> delegate) {
        this.component = component;
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
                localizedMessage = new StringResourceModel(message, component, HtmlContext.getModel(component), message).getObject();
            } catch (final MissingResourceException e) {
                localizedMessage = message;
            }
            return localizedMessage;
        }
    }

    public static void wrapExistingModel(final Component component) {
        component.setDefaultModel(new I18nDelegateModel(component, component.getDefaultModel()));
    }

}
