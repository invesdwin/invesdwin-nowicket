package de.invesdwin.nowicket.component.modal.panel;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelLabel;

@NotThreadSafe
public class ModalMessagePanel extends Panel {

    public ModalMessagePanel(final String id, final IModel<ModalMessage> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (ModalMessageConstants.message.equals(e.getWicketId())) {
                    return new ModelLabel(e);
                }
                return null;
            }
        }).bind();
    }
}
