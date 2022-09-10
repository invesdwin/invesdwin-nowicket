package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;

import de.invesdwin.nowicket.component.checkbox.BootstrapCheckBoxMultipleChoice;
import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;

@NotThreadSafe
public class ModelCheckBoxMultipleChoice extends BootstrapCheckBoxMultipleChoice<Object> {

    public ModelCheckBoxMultipleChoice(final AChoiceHtmlElement<?> element) {
        super(element.getWicketId(), element.getSelectionModel(), element.getChoiceModel(),
                element.getChoiceRenderer());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear();
        super.onComponentTag(tag);
    }

}
