package de.invesdwin.nowicket.examples.guide.pages.wicket.modalwindow.panel;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ModalWindowPanel extends Panel {

    public ModalWindowPanel(final String id, final IModel<ModalWindow> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
