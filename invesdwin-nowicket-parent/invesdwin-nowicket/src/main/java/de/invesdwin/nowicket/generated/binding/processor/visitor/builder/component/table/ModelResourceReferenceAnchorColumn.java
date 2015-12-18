package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.ResourceReference;

import de.invesdwin.nowicket.generated.binding.processor.element.TableAnchorColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelResourceLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;

@NotThreadSafe
public class ModelResourceReferenceAnchorColumn extends PropertyColumn<ResourceReference, String> {

    private final TableAnchorColumnHtmlElement element;

    public ModelResourceReferenceAnchorColumn(final TableAnchorColumnHtmlElement element) {
        super(element.getTitleModel(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @Override
    public IModel<Object> getDataModel(final IModel<ResourceReference> rowModel) {
        return new BeanPathModel<Object>(rowModel, getPropertyExpression());
    }

    @Override
    public void populateItem(final Item<ICellPopulator<ResourceReference>> item, final String componentId,
            final IModel<ResourceReference> rowModel) {
        item.add(newLink(componentId, rowModel));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected ModelResourceLink newLink(final String componentId, final IModel<ResourceReference> rowModel) {
        return new ModelResourceLink(componentId, (ResourceReference) getDataModel(rowModel).getObject(),
                element.getTitleModel((IModel) rowModel)) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                tag.setName("a");
                super.onComponentTag(tag);
            }
        };
    }

}
