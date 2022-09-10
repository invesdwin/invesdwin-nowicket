package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallback;
import de.invesdwin.nowicket.generated.markup.processor.element.TableRemoveFromButtonColumnModelElement;

@NotThreadSafe
public class TableRemoveFromButtonColumnHtmlElement extends TableSubmitButtonColumnHtmlElement {

    public TableRemoveFromButtonColumnHtmlElement(final HtmlContext context,
            final TableRemoveFromButtonColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Override
    public TableRemoveFromButtonColumnModelElement getModelElement() {
        return (TableRemoveFromButtonColumnModelElement) super.getModelElement();
    }

    @Override
    protected boolean isRowContainer() {
        return false;
    }

    @Override
    public ISubmitButtonCallback getButtonCallback(final IModel<Object> targetObjectModel) {
        return getContext().getSubmitButtonCallbackFactory()
                .createSubmitButtonCallback(this, getTableObjectModel(),
                        () -> new Object[] { targetObjectModel.getObject() },
                        getModelElement().getBeanPathElement().getInvoker());
    }

}
