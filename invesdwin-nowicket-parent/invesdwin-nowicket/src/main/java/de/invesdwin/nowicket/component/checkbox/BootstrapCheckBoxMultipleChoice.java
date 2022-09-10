package de.invesdwin.nowicket.component.checkbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.value.IValueMap;
import org.apache.wicket.util.value.ValueMap;

/**
 * Adapted from: de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice
 */
@NotThreadSafe
public class BootstrapCheckBoxMultipleChoice<T> extends CheckBoxMultipleChoice<T> {

    private boolean inline = false;

    public BootstrapCheckBoxMultipleChoice(final String id) {
        this(id, new ArrayList<T>());
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final List<T> choices) {
        this(id, choices, new ChoiceRenderer<T>());
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final List<T> choices,
            final IChoiceRenderer<? super T> renderer) {
        this(id, null, choices, renderer);
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final IModel<? extends Collection<T>> model,
            final List<T> choices) {
        this(id, model, choices, new ChoiceRenderer<T>());
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final IModel<? extends Collection<T>> model,
            final List<T> choices, final IChoiceRenderer<? super T> renderer) {
        this(id, model, new ListModel<>(choices), renderer);
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final IModel<? extends List<? extends T>> choices) {
        this(id, choices, new ChoiceRenderer<T>());
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final IModel<? extends Collection<T>> model,
            final IModel<? extends List<? extends T>> choices) {
        this(id, model, choices, new ChoiceRenderer<T>());
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final IModel<? extends List<? extends T>> choices,
            final IChoiceRenderer<? super T> renderer) {
        this(id, null, choices, renderer);
    }

    public BootstrapCheckBoxMultipleChoice(final String id, final IModel<? extends Collection<T>> model,
            final IModel<? extends List<? extends T>> choices, final IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);

        setInline(false);
        setLabelPosition(LabelPosition.AFTER);
    }

    public boolean isInline() {
        return inline;
    }

    public BootstrapCheckBoxMultipleChoice<T> setInline(final boolean inline) {
        String cssClass = "form-check";
        if (inline) {
            cssClass += " form-check-inline";
        }
        setPrefix("<div class=\"" + cssClass + "\">");
        setSuffix("</div>");

        this.inline = inline;
        return this;
    }

    @Override
    protected IValueMap getAdditionalAttributesForLabel(final int index, final T choice) {
        final IValueMap attrs = new ValueMap();
        attrs.put("class", "form-check-label");
        return attrs;
    }

    @Override
    protected IValueMap getAdditionalAttributes(final int index, final T choice) {
        final IValueMap attrs = new ValueMap();
        attrs.put("class", "form-check-input");
        return attrs;
    }
}
