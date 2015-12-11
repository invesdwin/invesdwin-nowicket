package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.html.Input;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;

@NotThreadSafe
public class TextInputHtmlElement extends AModelHtmlElement<TextInputModelElement, Object> {

    public static final String INPUT_TYPE_TEXT = Input.text;

    public TextInputHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitTextInput(this);
    }

    public Class<?> getType() {
        return getModelElement().getBeanPathElement().getModifier().getAccessor().getRawType().getType();
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

}
