package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.TextField;

import de.invesdwin.nowicket.generated.binding.processor.element.TextInputHtmlElement;

@NotThreadSafe
public class ModelTextField extends TextField<Object> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelTextField(final TextInputHtmlElement element) {
        super(element.getWicketId(), element.getModel(), (Class) element.getType());
    }

}
