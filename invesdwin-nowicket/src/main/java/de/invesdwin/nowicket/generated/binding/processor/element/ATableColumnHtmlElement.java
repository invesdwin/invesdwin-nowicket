package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableColumnModelElement;

@NotThreadSafe
public abstract class ATableColumnHtmlElement<E extends ATableColumnModelElement<?>, M> extends AModelHtmlElement<E, M> {

    public ATableColumnHtmlElement(final HtmlContext context, final E modelElement) {
        super(context, modelElement.getWicketId());
    }

    public String getColumnId() {
        return getModelElement().getBeanPathElement().getAccessor().getBeanPathFragment();
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
    public IModel<String> getTitleModel(final IModel<Object> targetObjectModel) {
        return new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                final Object target;
                if (targetObjectModel == null) {
                    target = null;
                } else {
                    target = targetObjectModel.getObject();
                }
                return new StringResourceModel(getWicketId(), getContext().getMarkupContainer(),
                        getContext().getMarkupContainer().getDefaultModel(), getModelElement().getBeanPathElement()
                                .getTitle(target)).getObject();
            }
        };
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
