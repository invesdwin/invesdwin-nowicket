package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.StringResourceModel;
import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.impl.clazz.BeanClassContainer;
import de.invesdwin.norva.beanpath.spi.IBeanPathContainer;
import de.invesdwin.norva.beanpath.spi.element.table.column.ITableColumnBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableColumnModelElement;

@NotThreadSafe
public abstract class ATableColumnHtmlElement<E extends ATableColumnModelElement<?>, M>
        extends AModelHtmlElement<E, M> {

    private IModel<Object> tableObjectModel;

    public ATableColumnHtmlElement(final HtmlContext context, final E modelElement) {
        super(context, modelElement.getWicketId());
    }

    public String getColumnId() {
        return getModelElement().getColumnId();
    }

    @Override
    public Element getElement() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected final void innerAccept(final IHtmlVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    /**
     * Passing null here results in static title model.
     */
    @Override
    public IModel<String> getTitleModelFromTarget(final IModel<Object> targetObjectModel) {
        return new IModel<String>() {
            @Override
            public String getObject() {
                final Object target;
                if (targetObjectModel == null) {
                    target = null;
                } else {
                    target = targetObjectModel.getObject();
                }
                return new StringResourceModel(getWicketId(), getContext().getMarkupContainer(),
                        getContext().getMarkupContainer().getDefaultModel())
                                .setDefaultValue(getModelElement().getBeanPathElement().getTitleFromTarget(target))
                                .getObject();
            }
        };
    }

    protected abstract boolean isRowContainer();

    protected IModel<Object> getTableObjectModel() {
        if (tableObjectModel == null) {
            tableObjectModel = new LoadableDetachableModel<Object>() {
                @Override
                protected Object load() {
                    final Object rootObject = getContext().getModelObjectContext().getModelObject();
                    final ITableColumnBeanPathElement element = getModelElement().getBeanPathElement();
                    IBeanPathContainer tableContainer = element.getContainer();
                    if (isRowContainer()) {
                        tableContainer = tableContainer.getParent();
                    }
                    final BeanClassContainer container = tableContainer.unwrap(BeanClassContainer.class);
                    return container.getTargetFromRoot(rootObject);
                }
            };
        }
        return tableObjectModel;
    }

    @Deprecated
    @Override
    public IModel<M> getModel() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<Object> getTargetObjectModel() {
        throw new UnsupportedOperationException();
    }

    public abstract IColumn<? extends Object, String> createWicketColumn(IBindingBuilder bindingBuilder);

}
