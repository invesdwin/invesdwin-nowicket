package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.html.Input;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.spi.element.AChoiceBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NoChoiceModelElement;

@NotThreadSafe
public class CheckBoxInputHtmlElement
        extends AChoiceHtmlElement<AChoiceModelElement<? extends AChoiceBeanPathElement>> {

    public static final String INPUT_TYPE_CHECKBOX = Input.checkbox;

    public CheckBoxInputHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public AChoiceModelElement<? extends AChoiceBeanPathElement> getModelElement() {
        return NoChoiceModelElement.maybeWrap(super.getModelElement());
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitCheckBoxInput(this);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public IModel<Boolean> getBooleanModel() {
        if (isBoolean()) {
            return (IModel) getModel();
        } else {
            throw new IllegalArgumentException("Not of boolean type: " + this);
        }
    }

    public Component createWicketCheckBox() {
        if (isBoolean()) {
            return getContext().getBindingBuilder().createBooleanCheckBoxInput(this);
        } else if (isMultiSelection()) {
            return getContext().getBindingBuilder().createMultiSelectionCheckBoxInput(this);
        } else {
            return getContext().getBindingBuilder().createSingleSelectionCheckBoxInput(this);
        }
    }

    private boolean isBoolean() {
        return getModelElement().getBeanPathElement().getAccessor().getRawType().isBoolean();
    }

    @Override
    public boolean isEager() {
        return super.isEager() || !isBoolean() && isSingleSelection();
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

}
