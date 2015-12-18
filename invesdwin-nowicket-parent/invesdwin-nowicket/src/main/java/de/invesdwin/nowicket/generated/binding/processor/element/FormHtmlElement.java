package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class FormHtmlElement extends ANoModelHtmlElement {

    public static final String WICKET_ID = "form";

    public FormHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
        Assertions.assertThat(getWicketId()).isEqualTo(WICKET_ID);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitForm(this);
    }

}
