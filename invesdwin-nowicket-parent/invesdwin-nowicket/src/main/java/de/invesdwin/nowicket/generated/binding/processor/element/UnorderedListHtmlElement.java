package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.spi.element.AChoiceBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NoChoiceModelElement;

@NotThreadSafe
public class UnorderedListHtmlElement extends AChoiceHtmlElement<AChoiceModelElement<?>> {

    public UnorderedListHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public AChoiceModelElement<? extends AChoiceBeanPathElement> getModelElement() {
        return NoChoiceModelElement.maybeWrap(super.getModelElement());
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitUnorderedList(this);
    }

    @Override
    public boolean isEnabledFromTarget(final IModel<Object> rootObjectModel, final IModel<Object> targetObjectModel) {
        //always enabled
        return true;
    }

}
