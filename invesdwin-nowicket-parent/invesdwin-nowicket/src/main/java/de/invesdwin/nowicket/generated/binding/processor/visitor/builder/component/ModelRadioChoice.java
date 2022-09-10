package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;

@NotThreadSafe
public class ModelRadioChoice extends BootstrapRadioChoice<Object> {

    public ModelRadioChoice(final AChoiceHtmlElement<?> element) {
        super(element.getWicketId(), element.getModel(), element.getChoiceModel(), element.getChoiceRenderer());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear();
        super.onComponentTag(tag);
    }

}
