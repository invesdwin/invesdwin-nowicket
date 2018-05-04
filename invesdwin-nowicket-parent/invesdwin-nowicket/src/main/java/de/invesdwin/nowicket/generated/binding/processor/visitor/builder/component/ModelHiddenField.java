package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.HiddenField;

import de.invesdwin.nowicket.generated.binding.processor.element.ITextInputHtmlElement;

@NotThreadSafe
public class ModelHiddenField extends HiddenField<Object> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelHiddenField(final ITextInputHtmlElement<Object> element) {
        super(element.getWicketId(), element.getModel(), (Class) element.getType());
    }

}
