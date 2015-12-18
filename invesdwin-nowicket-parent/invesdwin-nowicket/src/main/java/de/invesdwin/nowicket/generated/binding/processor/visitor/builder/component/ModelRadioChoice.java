package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.RadioChoice;

import de.invesdwin.nowicket.generated.binding.processor.element.RadioInputHtmlElement;

@NotThreadSafe
public class ModelRadioChoice extends RadioChoice<Object> {

    public ModelRadioChoice(final RadioInputHtmlElement element) {
        super(element.getWicketId(), element.getModel(), element.getChoiceModel(), element.getChoiceRenderer());
        //fix layout for bootstrap
        setPrefix("<label class=\"radio\">");
        setSuffix("</label>");
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear();
        tag.getAttributes().put("class", "radio");
        super.onComponentTag(tag);
    }

}
