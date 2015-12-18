package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback;

import java.io.Serializable;

import org.apache.wicket.Component;

public interface ISubmitButtonCallback extends Serializable {

    void onSubmit(Component component);

    void onError(Component component);

}
