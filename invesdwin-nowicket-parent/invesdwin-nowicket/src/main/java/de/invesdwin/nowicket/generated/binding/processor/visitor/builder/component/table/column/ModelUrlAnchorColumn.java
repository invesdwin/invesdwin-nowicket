package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableAnchorColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelExternalLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.UrlAnchorModel;

@NotThreadSafe
public class ModelUrlAnchorColumn extends PropertyColumn<String, String> {

    private final TableAnchorColumnHtmlElement element;

    public ModelUrlAnchorColumn(final TableAnchorColumnHtmlElement element) {
        super(element.getTitleModel(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IModel<Object> getDataModel(final IModel<String> rowModel) {
        return (IModel) new UrlAnchorModel(element, rowModel, getPropertyExpression());
    }

    @Override
    public void populateItem(final Item<ICellPopulator<String>> item, final String componentId,
            final IModel<String> rowModel) {
        item.add(newLink(componentId, rowModel));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected ModelExternalLink newLink(final String componentId, final IModel<String> rowModel) {
        return new ModelExternalLink(componentId, (IModel) getDataModel(rowModel),
                element.getTitleModel((IModel) rowModel)) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                tag.setName("a");
                super.onComponentTag(tag);
            }
        };
    }

}
