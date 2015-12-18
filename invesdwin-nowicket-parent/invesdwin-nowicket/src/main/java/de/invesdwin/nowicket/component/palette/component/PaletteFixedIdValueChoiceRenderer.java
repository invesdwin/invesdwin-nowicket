package de.invesdwin.nowicket.component.palette.component;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

/**
 * Fixes a bug with wicket Palette identifying the id values wrong by relying on the choice model here.
 */
@NotThreadSafe
public class PaletteFixedIdValueChoiceRenderer<T> implements IChoiceRenderer<T> {
    private final IModel<List<T>> choiceModel;
    private final IChoiceRenderer<T> choiceRenderer;

    public PaletteFixedIdValueChoiceRenderer(final IModel<List<T>> choiceModel, final IChoiceRenderer<T> choiceRenderer) {
        this.choiceModel = choiceModel;
        this.choiceRenderer = choiceRenderer;
    }

    @Override
    public Object getDisplayValue(final T object) {
        return choiceRenderer.getDisplayValue(object);
    }

    @Override
    public String getIdValue(final T object, final int index) {
        //fixes bug where every element is regarded as id 0 and thus the choices are not rendered anymore
        final List<T> choices = choiceModel.getObject();
        final int indexOf = choices.indexOf(object);
        if (indexOf >= 0) {
            return String.valueOf(indexOf);
        } else {
            throw new IllegalArgumentException("No indexOf found for object [" + object + "] on index [" + index + "]");
        }
    }

    @Override
    public T getObject(final String id, final IModel<? extends List<? extends T>> choices) {
        return choiceRenderer.getObject(id, choices);
    }
}