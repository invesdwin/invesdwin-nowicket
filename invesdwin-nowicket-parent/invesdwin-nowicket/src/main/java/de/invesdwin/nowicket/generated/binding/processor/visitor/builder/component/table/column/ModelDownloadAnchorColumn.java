package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableAnchorColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelDownloadLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;

@NotThreadSafe
public class ModelDownloadAnchorColumn extends PropertyColumn<Object, String> {

    private final TableAnchorColumnHtmlElement element;

    public ModelDownloadAnchorColumn(final TableAnchorColumnHtmlElement element) {
        super(element.getTitleModel(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        return new BeanPathModel(rowModel, getPropertyExpression());
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        final ModelDownloadLink link = newLink(componentId, rowModel);
        link.add(new ModelComponentBehavior(element, link, rowModel));
        item.add(link);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected ModelDownloadLink newLink(final String componentId, final IModel<Object> rowModel) {
        return new ModelDownloadLink(componentId, (IModel) getDataModel(rowModel), element.getTitleModel(rowModel)) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                tag.setName("a");
                super.onComponentTag(tag);
            }
        };
    }
}
