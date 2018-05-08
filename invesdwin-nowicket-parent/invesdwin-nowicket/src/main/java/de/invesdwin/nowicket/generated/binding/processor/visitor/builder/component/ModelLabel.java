package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelLabel extends Label {

    public ModelLabel(final IHtmlElement<?, ?> element) {
        this(element, element.getModel());
    }

    public ModelLabel(final IHtmlElement<?, ?> element, final IModel<?> model) {
        super(element.getWicketId(), model);
        setEscapeModelStrings(false);
        I18nModel.wrapExistingModel(this);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
    }

    @Override
    public boolean isVisible() {
        final IModel<?> model = getDefaultModel();
        if (model == null) {
            return false;
        }
        final Object modelObject = model.getObject();
        if (modelObject == null || Strings.isEmpty(getDefaultModelObjectAsString(modelObject))) {
            return false;
        }
        return super.isVisible();
    }

}
