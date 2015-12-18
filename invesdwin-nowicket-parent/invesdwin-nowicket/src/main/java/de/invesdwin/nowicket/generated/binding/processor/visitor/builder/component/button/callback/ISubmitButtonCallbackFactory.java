package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.norva.beanpath.spi.element.simple.invoker.IBeanPathActionInvoker;

public interface ISubmitButtonCallbackFactory extends Serializable {

    ISubmitButtonCallback createSubmitButtonCallback(final IHtmlElement<?, ?> element,
            final IModel<Object> targetObjectModel, final IBeanPathActionInvoker invoker);

}
