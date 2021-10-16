package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.norva.beanpath.spi.element.TabbedBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TabbedColumnBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.collections.delegate.DelegateList;

@NotThreadSafe
public class TabbedModelElement extends AChoiceModelElement<TabbedBeanPathElement> {

    private final List<TabbedColumnModelElement> rawColumns;
    private List<TabbedColumnModelElement> columns;

    public TabbedModelElement(final AModelContext context, final TabbedBeanPathElement beanPathElement) {
        super(context, beanPathElement);
        rawColumns = new ArrayList<TabbedColumnModelElement>();
        for (final TabbedColumnBeanPathElement rawColumn : beanPathElement.getRawColumns()) {
            rawColumns.add(new TabbedColumnModelElement(context, rawColumn));
        }
    }

    public List<TabbedColumnModelElement> getRawColumns() {
        return Collections.unmodifiableList(rawColumns);
    }

    public List<TabbedColumnModelElement> getColumns(final IModel<Object> targetObjectModel) {
        if (columns == null) {
            columns = Collections.unmodifiableList(new DelegateList<TabbedColumnModelElement>(null) {
                @Override
                public List<TabbedColumnModelElement> getDelegate() {
                    final List<TabbedColumnModelElement> delegate = new ArrayList<TabbedColumnModelElement>();
                    for (final TabbedColumnBeanPathElement column : getBeanPathElement()
                            .getColumnsFromTarget(targetObjectModel.getObject())) {
                        final TabbedColumnModelElement columnElement = (TabbedColumnModelElement) getContext()
                                .getElementRegistry()
                                .getElement(column.getBeanPath());
                        delegate.add(columnElement);
                    }
                    return delegate;
                }

                @Override
                public int size() {
                    return getBeanPathElement().getColumnsFromTarget(targetObjectModel.getObject()).size();
                }

                @Override
                public boolean isEmpty() {
                    return getBeanPathElement().getColumnsFromTarget(targetObjectModel.getObject()).isEmpty();
                }
            });
        }
        return Collections.unmodifiableList(columns);
    }

    @Override
    protected void onFirstAccept() {
        super.onFirstAccept();
        for (final TabbedColumnModelElement rawColumn : rawColumns) {
            Assertions.assertThat(rawColumn.accept()).isTrue();
        }
    }

    @Override
    protected void innerAccept(final IModelVisitor visitor) {
        visitor.visitTabbed(this);
    }

}
