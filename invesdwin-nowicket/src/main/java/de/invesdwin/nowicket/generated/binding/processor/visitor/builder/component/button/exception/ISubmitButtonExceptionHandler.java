package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.exception;

import java.io.Serializable;

import org.apache.wicket.Component;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

public interface ISubmitButtonExceptionHandler extends Serializable {

    void handleSubmitButtonException(IHtmlElement<?, ?> element, Component component, Throwable t);

}
