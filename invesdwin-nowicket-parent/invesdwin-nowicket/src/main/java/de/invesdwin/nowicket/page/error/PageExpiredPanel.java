package de.invesdwin.nowicket.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class PageExpiredPanel extends AErrorPanel {

    public PageExpiredPanel(final String id) {
        this(id, Model.of(new PageExpired()));
    }

    public PageExpiredPanel(final String id, final IModel<PageExpired> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
