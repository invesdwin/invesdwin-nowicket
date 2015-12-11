package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedColumnModelElement;

@NotThreadSafe
public class TabbedColumnHtmlElement extends AModelHtmlElement<TabbedColumnModelElement, Object> {

    public TabbedColumnHtmlElement(final HtmlContext context, final TabbedColumnModelElement modelElement) {
        super(context, modelElement.getWicketId());
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    public ITab createWicketTab() {
        return getContext().getBindingBuilder().createTabbedColumn(this);
    }

}
