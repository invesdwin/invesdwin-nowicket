package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelTab;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedColumnModelElement;

@NotThreadSafe
public class TabbedColumnHtmlElement extends AModelHtmlElement<TabbedColumnModelElement, Object> {

    public TabbedColumnHtmlElement(final HtmlContext context, final TabbedColumnModelElement modelElement) {
        super(context, modelElement.getWicketId());
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    public ITab createWicketTab() {
        //cannot be delegated to BindingBuilder since it might be required in a model that gets refreshed each request cycle
        return new ModelTab(this, getTitleModel(), getModel(), getTargetObjectModel());
    }

    @Override
    protected boolean isHiddenByModalContainer() {
        //never hidden, or else modals will be disabled all the time
        return false;
    }

}
