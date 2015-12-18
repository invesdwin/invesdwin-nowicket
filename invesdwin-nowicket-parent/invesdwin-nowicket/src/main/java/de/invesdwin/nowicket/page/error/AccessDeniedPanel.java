package de.invesdwin.nowicket.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class AccessDeniedPanel extends AErrorPanel {

    public AccessDeniedPanel(final String id) {
        this(id, Model.of(new AccessDenied()));
    }

    public AccessDeniedPanel(final String id, final IModel<AccessDenied> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
