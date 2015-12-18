package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.ListMultipleChoice;

import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;

@NotThreadSafe
public class ModelListMultipleChoice extends ListMultipleChoice<Object> {

    public ModelListMultipleChoice(final SelectHtmlElement e) {
        super(e.getWicketId(), e.getSelectionModel(), e.getChoiceModel(), e.getChoiceRenderer());
    }
}
