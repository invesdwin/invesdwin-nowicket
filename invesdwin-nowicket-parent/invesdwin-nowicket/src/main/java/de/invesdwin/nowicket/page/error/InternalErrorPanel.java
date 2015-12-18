package de.invesdwin.nowicket.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class InternalErrorPanel extends AErrorPanel {

    public InternalErrorPanel(final String id) {
        this(id, Model.of(new InternalError()));
    }

    public InternalErrorPanel(final String id, final IModel<InternalError> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
