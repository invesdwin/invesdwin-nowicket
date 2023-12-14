package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.util.lang.comparator.ACriteriaComparator;
import de.invesdwin.util.lang.comparator.IComparator;
import de.invesdwin.util.lang.comparator.ISortAlgorithm;
import de.invesdwin.util.lang.comparator.SortAlgorithm;
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
        final List<Object> rows = newRows();

        final SortParam<String> sort = getSort();
        if (sort != null) {
            sort(rows, sort);
        }

        long toIndex = first + count;
        if (toIndex > rows.size()) {
            toIndex = rows.size();
        }
        return rows.subList(Integers.checkedCast(first), Integers.checkedCast(toIndex)).listIterator();
    }

    protected void sort(final List<Object> rows, final SortParam<String> sort) {
        final String sortProperty = sort.getProperty();
        final IComparator<Object> comparator = new ACriteriaComparator<Object>() {
            @Override
            public Comparable<?> getCompareCriteriaNotNullSafe(final Object e) {
                final IModel<Object> eModel = new IModel<Object>() {
                    @Override
                    public Object getObject() {
                        return e;
                    }
                };
                final IModel<Comparable<Object>> model = new BeanPathModel<Comparable<Object>>(eModel, sortProperty);
                return model.getObject();
            }

            @Override
            public ISortAlgorithm getSortAlgorithm() {
                //a race condition could make the comparison unstable
                return SortAlgorithm.TIMSORT_FALLBACK_BUBBLESORT;
            }
        };
        comparator.asAscending(sort.isAscending()).sort(rows);
    }

    protected List<Object> newRows() {
        return element.getChoiceModel().getObject();
    }

    @Override
    public long size() {
        final List<Object> rows = newRows();
        return rows.size();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IModel<Object> model(final Object object) {
        return (IModel) Model.of((Serializable) object);
    }
}