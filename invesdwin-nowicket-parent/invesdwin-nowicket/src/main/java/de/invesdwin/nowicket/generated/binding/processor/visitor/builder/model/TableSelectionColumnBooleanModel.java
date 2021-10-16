package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.model.IModel;

import de.invesdwin.norva.beanpath.spi.element.simple.modifier.SelectionBeanPathPropertyModifier;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSelectionButtonColumnHtmlElement;

@NotThreadSafe
public class TableSelectionColumnBooleanModel implements IModel<Boolean> {

    private static final MetaDataKey<OnlyOnceTruePerRequestCycleModel> KEY_SHOULD_UPDATE_MODEL = new MetaDataKey<OnlyOnceTruePerRequestCycleModel>() {
    };
    private final TableSelectionButtonColumnHtmlElement element;
    private final IModel<Object> rootObjectModel;
    private final IModel<Object> rowModel;
    private final boolean singleSelection;

    public TableSelectionColumnBooleanModel(final TableSelectionButtonColumnHtmlElement element,
            final IModel<Object> rowModel) {
        this.element = element;
        this.rootObjectModel = element.getContext().getRootObjectModel();
        this.rowModel = rowModel;
        this.singleSelection = element.isSingleSelection();
    }

    @Override
    public void detach() {
        rowModel.detach();
    }

    @Override
    public Boolean getObject() {
        final Object row = rowModel.getObject();
        final SelectionBeanPathPropertyModifier selectionModifier = element.getModelElement()
                .getBeanPathElement()
                .getSelectionModifier();
        final boolean selected = selectionModifier.isSelectedFromRoot(rootObjectModel.getObject(), row);
        return selected;
    }

    @Override
    public void setObject(final Boolean object) {
        if (!shouldUpdateModel()) {
            //a following checkbox under this one might get invoked as well
            return;
        }
        final Object row = rowModel.getObject();
        final SelectionBeanPathPropertyModifier selectionModifier = element.getModelElement()
                .getBeanPathElement()
                .getSelectionModifier();
        if (object) {
            selectionModifier.selectFromRoot(rootObjectModel.getObject(), row);
        } else {
            selectionModifier.unselectFromRoot(rootObjectModel.getObject(), row);
        }
    }

    private boolean shouldUpdateModel() {
        if (singleSelection) {
            final Component tableComponent = element.getContext()
                    .getComponentRegistry()
                    .getComponent(element.getWicketId());
            OnlyOnceTruePerRequestCycleModel shouldUpdateModel = tableComponent.getMetaData(KEY_SHOULD_UPDATE_MODEL);
            if (shouldUpdateModel == null) {
                shouldUpdateModel = new OnlyOnceTruePerRequestCycleModel();
                tableComponent.setMetaData(KEY_SHOULD_UPDATE_MODEL, shouldUpdateModel);
            }
            return shouldUpdateModel.getObject();
        } else {
            return true;
        }
    }

}
