package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.AbstractDateTextFieldConfig.Day;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.AbstractDateTextFieldConfig.TodayButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;
import de.invesdwin.util.lang.reflection.Reflections;

@NotThreadSafe
public class ModelDateTextField extends DateTextField {

    private final AttributeModifier typeAttributeModifier = new AttributeModifier("type", "text");

    public ModelDateTextField(final DateInputHtmlElement element) {
        this(element, newDateTextFieldConfig(element));
    }

    public ModelDateTextField(final DateInputHtmlElement element, final DateTextFieldConfig config) {
        super(element.getWicketId(), element.getModel(), config);
        //fix underlying wicket date pattern so that time information is not broken (sadly minutes will become month due to javascript conversion)
        final org.apache.wicket.markup.html.form.TextField<?> converterDelegate = Reflections.field("converterDelegate")
                .ofType(org.apache.wicket.markup.html.form.TextField.class)
                .in(this)
                .get();
        Reflections.field("datePattern").ofType(String.class).in(converterDelegate).set(getFormat(element));
    }

    public static DateTextFieldConfig newDateTextFieldConfig(final DateInputHtmlElement element) {
        return new DateTextFieldConfig().withFormat(getFormat(element))
                .highlightToday(true)
                //we need autoclose enabled or else we get detached from the text field on eager value change
                .autoClose(true)
                .withFormat(getFormat(element))
                .allowKeyboardNavigation(false)
                .showTodayButton(TodayButton.LINKED)
                .withWeekStart(Day.Monday)
                .forceParse(false);
    }

    private static String getFormat(final DateInputHtmlElement element) {
        return element.getFormat(AWebSession.get().getLocale()).toPattern();
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
