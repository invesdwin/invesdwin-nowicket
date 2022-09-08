package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.repeater.IItemReuseStrategy;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.UpdatingReuseIfModelsEqualStrategy;

/**
 * Adapted from org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
 * 
 * Though it replaces the old model with the new model instance in the item.
 */
@Immutable
public final class ModelTabUpdatingReuseIfModelsEqualStrategy extends UpdatingReuseIfModelsEqualStrategy {
    private static final long serialVersionUID = 1L;

    private static IItemReuseStrategy instance = new ModelTabUpdatingReuseIfModelsEqualStrategy();

    private ModelTabUpdatingReuseIfModelsEqualStrategy() {}

    public static IItemReuseStrategy getInstance() {
        return instance;
    }

    @Override
    protected <T> void updateModel(final IModel<T> model, final Item<T> oldItem) {
        final AModelTab oldTab = (AModelTab) oldItem.getModel().getObject();
        final AModelTab newTab = (AModelTab) model.getObject();
        final IModel<Object> oldTabModel = oldTab.getPanelModel();
        final IModel<Object> newTabModel = newTab.getPanelModel();
        if (oldTabModel != null && newTabModel != null) {
            oldTabModel.setObject(newTabModel.getObject());
        }
    }

}
