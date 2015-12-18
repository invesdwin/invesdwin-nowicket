package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.DropDownChoice;

import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;

@NotThreadSafe
public class ModelDropDownChoice extends DropDownChoice<Object> {

    public ModelDropDownChoice(final SelectHtmlElement element) {
        super(element.getWicketId(), element.getModel(), element.getChoiceModel(), element.getChoiceRenderer());
    }

}
