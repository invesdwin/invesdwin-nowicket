package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import de.invesdwin.norva.beanpath.spi.element.simple.invoker.IBeanPathActionInvoker;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

public interface ISubmitButtonCallbackFactory extends Serializable {

    ISubmitButtonCallback createSubmitButtonCallback(IHtmlElement<?, ?> element, IModel<Object> targetObjectModel,
            IBeanPathActionInvoker invoker);

}
