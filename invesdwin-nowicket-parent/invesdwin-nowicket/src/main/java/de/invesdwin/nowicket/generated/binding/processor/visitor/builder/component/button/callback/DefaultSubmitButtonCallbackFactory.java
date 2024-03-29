package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.model.IModel;

import de.invesdwin.norva.beanpath.spi.element.simple.invoker.IBeanPathActionInvoker;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

@Immutable
public class DefaultSubmitButtonCallbackFactory implements ISubmitButtonCallbackFactory {

    @Override
    public ISubmitButtonCallback createSubmitButtonCallback(final IHtmlElement<?, ?> element,
            final IModel<Object> targetObjectModel, final IModel<Object[]> parametersModel,
            final IBeanPathActionInvoker invoker) {
        return new DefaultSubmitButtonCallback(element, targetObjectModel, parametersModel, invoker);
    }

}
