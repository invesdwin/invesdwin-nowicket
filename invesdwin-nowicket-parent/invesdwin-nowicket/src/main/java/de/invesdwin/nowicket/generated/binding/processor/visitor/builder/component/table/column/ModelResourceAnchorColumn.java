package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.IResource;

import de.invesdwin.nowicket.generated.binding.processor.element.TableAnchorColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelResourceLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;

@NotThreadSafe
public class ModelResourceAnchorColumn extends PropertyColumn<Object, String> {

    private final TableAnchorColumnHtmlElement element;

    public ModelResourceAnchorColumn(final TableAnchorColumnHtmlElement element) {
        super(element.getTitleModelFromTarget(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        return new BeanPathModel<Object>(rowModel, getPropertyExpression());
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        final ModelResourceLink link = newLink(componentId, rowModel);
        link.add(new ModelComponentBehavior(element, link, rowModel));
        item.add(link);
    }

    protected ModelResourceLink newLink(final String componentId, final IModel<Object> rowModel) {
        return new ModelResourceLink(componentId, (IResource) getDataModel(rowModel).getObject(),
                element.getTitleModelFromTarget(rowModel)) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                tag.setName("a");
                super.onComponentTag(tag);
            }
        };
    }

}
