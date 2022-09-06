package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.repeater.IItemFactory;
import org.apache.wicket.markup.repeater.IItemReuseStrategy;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Generics;

/**
 * Adapted from org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
 * 
 * Though it replaces the old model with the new model instance in the item.
 */
@Immutable
public final class UpdatingReuseIfModelsEqualStrategy implements IItemReuseStrategy {
    private static final long serialVersionUID = 1L;

    private static IItemReuseStrategy instance = new UpdatingReuseIfModelsEqualStrategy();

    private UpdatingReuseIfModelsEqualStrategy() {}

    public static IItemReuseStrategy getInstance() {
        return instance;
    }

    @Override
    public <T> Iterator<Item<T>> getItems(final IItemFactory<T> factory, final Iterator<IModel<T>> newModels,
            final Iterator<Item<T>> existingItems) {
        final Map<IModel<T>, Item<T>> modelToItem = Generics.newHashMap();
        while (existingItems.hasNext()) {
            final Item<T> item = existingItems.next();
            modelToItem.put(item.getModel(), item);
        }

        return new Iterator<Item<T>>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return newModels.hasNext();
            }

            @Override
            public Item<T> next() {
                final IModel<T> model = newModels.next();
                final Item<T> oldItem = modelToItem.get(model);

                final Item<T> item;
                if (oldItem == null) {
                    item = factory.newItem(index, model);
                } else {
                    oldItem.setIndex(index);
                    oldItem.getModel().setObject(model.getObject());
                    item = oldItem;
                }
                index++;

                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

}
