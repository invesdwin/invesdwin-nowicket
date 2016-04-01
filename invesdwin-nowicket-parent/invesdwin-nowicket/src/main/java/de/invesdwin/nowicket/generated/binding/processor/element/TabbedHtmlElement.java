package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedModelElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class TabbedHtmlElement extends AModelHtmlElement<TabbedModelElement, Object> {

    private final List<TabbedColumnHtmlElement> rawColumns;
    private List<TabbedColumnHtmlElement> columns;

    public TabbedHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
        rawColumns = new ArrayList<TabbedColumnHtmlElement>();
        for (final TabbedColumnModelElement rawColumn : getModelElement().getRawColumns()) {
            rawColumns.add(new TabbedColumnHtmlElement(context, rawColumn));
        }
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    public List<TabbedColumnHtmlElement> getRawColumns() {
        return Collections.unmodifiableList(rawColumns);
    }

    public List<TabbedColumnHtmlElement> getColumns() {
        if (columns == null) {
            columns = new ArrayList<TabbedColumnHtmlElement>();
            for (final TabbedColumnModelElement column : getModelElement().getColumns()) {
                final TabbedColumnHtmlElement columnElement = (TabbedColumnHtmlElement) getContext()
                        .getElementRegistry().getElement(column.getWicketId());
                columns.add(columnElement);
            }
        }
        return Collections.unmodifiableList(columns);
    }

    @Override
    protected void onFirstAccept() {
        super.onFirstAccept();
        for (final TabbedColumnHtmlElement rawColumn : rawColumns) {
            Assertions.assertThat(rawColumn.accept()).isTrue();
        }
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitTabbed(this);
    }

    public List<ITab> createWicketTabs() {
        final List<ITab> tabs = new ArrayList<ITab>();
        for (final TabbedColumnHtmlElement column : getColumns()) {
            tabs.add(column.createWicketTab());
        }
        return tabs;
    }

    @Override
    protected boolean isHiddenByModalContainer() {
        //never hidden, or else modals will be disabled all the time
        return false;
    }

}
