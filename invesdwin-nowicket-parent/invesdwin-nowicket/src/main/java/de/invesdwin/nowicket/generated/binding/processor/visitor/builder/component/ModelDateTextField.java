package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig.Day;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig.TodayButton;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;

@NotThreadSafe
public class ModelDateTextField extends DateTextField {

    private final AttributeModifier typeAttributeModifier = new AttributeModifier("type", "text");

    public ModelDateTextField(final DateInputHtmlElement element) {
        this(element, newDateTextFieldConfig(element));
    }

    public ModelDateTextField(final DateInputHtmlElement element, final DateTextFieldConfig config) {
        super(element.getWicketId(), element.getModel(), config);
    }

    public static DateTextFieldConfig newDateTextFieldConfig(final DateInputHtmlElement element) {
        return new DateTextFieldConfig().withFormat(element.getFormat(AWebSession.get().getLocale()).toPattern())
                .highlightToday(true)
                .autoClose(false)
                .allowKeyboardNavigation(false)
                .showTodayButton(TodayButton.LINKED)
                .withWeekStart(Day.Monday)
                .forceParse(false);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        //dynamically update language
        getConfig().withLanguage(AWebSession.get().getLocale().getLanguage());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        //fix wicket complaining about wrong tag
        typeAttributeModifier.onComponentTag(this, tag);
        super.onComponentTag(tag);
    }

}
