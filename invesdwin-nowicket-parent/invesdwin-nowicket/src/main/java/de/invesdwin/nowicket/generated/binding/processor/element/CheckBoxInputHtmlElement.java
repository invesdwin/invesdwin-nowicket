package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.html.Input;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.CheckBoxInputModelElement;

@NotThreadSafe
public class CheckBoxInputHtmlElement extends AModelHtmlElement<CheckBoxInputModelElement, Boolean> {

    public static final String INPUT_TYPE_CHECKBOX = Input.checkbox;

    public CheckBoxInputHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitCheckBoxInput(this);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

}
