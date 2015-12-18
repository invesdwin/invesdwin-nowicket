package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.NoWicketProperties;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NoChoiceModelElement;
import de.invesdwin.norva.beanpath.spi.element.AChoiceBeanPathElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class SelectHtmlElement extends AChoiceHtmlElement<AChoiceModelElement<? extends AChoiceBeanPathElement>> {

    public static final String ATTR_SIZE = "size";

    public SelectHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public AChoiceModelElement<? extends AChoiceBeanPathElement> getModelElement() {
        return NoChoiceModelElement.maybeWrap(super.getModelElement());
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitSelect(this);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    public Component createWicketSelect() {
        if (isMultiSelect()) {
            return getContext().getBindingBuilder().createMultiSelect(this);
        } else {
            return getContext().getBindingBuilder().createSingleSelect(this);
        }
    }

    public int getMaxRows() {
        final String attrSize = getElement().attr(ATTR_SIZE);
        if (Strings.isBlank(attrSize)) {
            return NoWicketProperties.DEFAULT_SELECT_MAX_ROWS;
        } else {
            return Integer.parseInt(attrSize);
        }
    }

}
