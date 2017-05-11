package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.util.lang.ADelegateComparator;
import de.invesdwin.util.math.Integers;

/**
 * http://stackoverflow.com/questions/2726373/how-do-i-display-a-wicket-datatable-sorted-by-a-specific-column-by-default
 */
@NotThreadSafe
public class ModelSortableDataProvider extends SortableDataProvider<Object, String> {

    private final TableHtmlElement element;

    public ModelSortableDataProvider(final TableHtmlElement element) {
        this.element = element;
    }

    @Override
    public Iterator<? extends Object> iterator(final long first, final long count) {
        final List<Object> rows = element.getChoiceModel().getObject();

        if (getSort() != null) {
            final ADelegateComparator<Object> comparator = new ADelegateComparator<Object>() {
                @Override
                protected Comparable<?> getCompareCriteria(final Object e) {
                    final AbstractReadOnlyModel<Object> eModel = new AbstractReadOnlyModel<Object>() {
                        @Override
                        public Object getObject() {
                            return e;
                        }
                    };
                    final IModel<Comparable<Object>> model = new BeanPathModel<Comparable<Object>>(eModel,
                            getSort().getProperty());
                    return model.getObject();
                }
            };
            comparator.sort(rows, getSort().isAscending());
        }

        long toIndex = first + count;
        if (toIndex > rows.size()) {
            toIndex = rows.size();
        }
        return rows.subList(Integers.checkedCast(first), Integers.checkedCast(toIndex)).listIterator();
    }

    @Override
    public long size() {
        final List<Object> rows = element.getChoiceModel().getObject();
        return rows.size();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IModel<Object> model(final Object object) {
        return (IModel) Model.of((Serializable) object);
    }
}