package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorType;
import de.invesdwin.nowicket.generated.markup.processor.element.TableAnchorColumnModelElement;
import de.invesdwin.util.error.UnknownArgumentException;

@NotThreadSafe
public class TableAnchorColumnHtmlElement extends ATableColumnHtmlElement<TableAnchorColumnModelElement, Object> {

    public static final String BLANK_TARGET = AnchorHtmlElement.TARGET_BLANK;

    public TableAnchorColumnHtmlElement(final HtmlContext context, final TableAnchorColumnModelElement element) {
        super(context, element);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    public AnchorType getType() {
        return getModelElement().getType();
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        switch (getType()) {
        case URL:
            return bindingBuilder.createUrlAnchorColumn(this);
        case FILE:
            return bindingBuilder.createFileAnchorColumn(this);
        case RESOURCE:
            return bindingBuilder.createResourceAnchorColumn(this);
        case RESOURCE_REFERENCE:
            return bindingBuilder.createResourceReferenceAnchorColumn(this);
        default:
            throw UnknownArgumentException.newInstance(AnchorType.class, getType());
        }
    }

}
