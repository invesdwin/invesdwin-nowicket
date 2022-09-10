package de.invesdwin.nowicket.component.palette;

import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.component.palette.component.PaletteFixedIdValueChoiceRenderer;
import de.invesdwin.nowicket.component.palette.theme.BootstrapPaletteTheme;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.IEagerFilter;

@NotThreadSafe
public class BootstrapPalette<T> extends Palette<T> implements IEagerFilter {

    public BootstrapPalette(final String id, final IModel<? extends Collection<T>> model,
            final IModel<? extends Collection<? extends T>> choicesModel,
            final IChoiceRenderer<? super T> choiceRenderer, final int rows, final boolean allowOrder) {
        this(id, model, choicesModel, choiceRenderer, rows, allowOrder, false);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BootstrapPalette(final String id, final IModel<? extends Collection<T>> model,
            final IModel<? extends Collection<? extends T>> choicesModel,
            final IChoiceRenderer<? super T> choiceRenderer, final int rows, final boolean allowOrder,
            final boolean allowMoveAll) {
        super(id, model, choicesModel, new PaletteFixedIdValueChoiceRenderer(choicesModel, choiceRenderer), rows,
                allowOrder, allowMoveAll);
        add(new BootstrapPaletteTheme());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear(); //remove styling from it
        super.onComponentTag(tag);
    }

    @Override
    protected Component newAddAllComponent() {
        return addButtonCss(super.newAddAllComponent());
    }

    @Override
    protected Component newRemoveAllComponent() {
        return addButtonCss(super.newRemoveAllComponent());
    }

    @Override
    protected Component newAddComponent() {
        return addButtonCss(super.newAddComponent());
    }

    @Override
    protected Component newRemoveComponent() {
        return addButtonCss(super.newRemoveComponent());
    }

    @Override
    protected Component newUpComponent() {
        return addButtonCss(super.newUpComponent());
    }

    @Override
    protected Component newDownComponent() {
        return addButtonCss(super.newDownComponent());
    }

    protected Component addButtonCss(final Component button) {
        return button.add(AttributeModifier.append("class", "btn btn-outline-secondary btn-sm"));
    }

    @Override
    public boolean isEagerAllowed(final IHtmlElement<?, ?> element, final Component component) {
        if (component instanceof BootstrapPalette) {
            return false;
        }
        return true;
    }

}
