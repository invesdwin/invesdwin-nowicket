package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.html.Input;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class PasswordInputHtmlElement extends AModelHtmlElement<TextInputModelElement, String> {

    public static final String INPUT_TYPE_PASSWORD = Input.password;

    public PasswordInputHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void onFirstAccept() {
        super.onFirstAccept();
        Assertions.assertThat(String.class).isAssignableFrom(
                getModelElement().getBeanPathElement().getModifier().getAccessor().getRawType().getType());
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitPasswordInput(this);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

}
