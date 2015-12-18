package de.invesdwin.nowicket.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class PageNotFoundPanel extends AErrorPanel {

    public PageNotFoundPanel(final String id) {
        this(id, Model.of(new PageNotFound()));
    }

    public PageNotFoundPanel(final String id, final IModel<PageNotFound> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
