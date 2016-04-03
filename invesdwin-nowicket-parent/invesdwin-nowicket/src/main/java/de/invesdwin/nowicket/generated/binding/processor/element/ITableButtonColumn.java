package de.invesdwin.nowicket.generated.binding.processor.element;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallback;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

public interface ITableButtonColumn<E extends IModelElement<?>, M> extends IHtmlElement<E, M> {

    ISubmitButtonCallback getButtonCallback(IModel<Object> targetObjectModel);

    IModel<String> getIconCssClassModel();

    String getColumnId();

}
