package com.bsgcoach.web.footer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.component.footer.AFooter;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class FooterPanel extends AFooter {

    public FooterPanel(final String id) {
        this(id, Model.of(new Footer()));
    }

    public FooterPanel(final String id, final IModel<Footer> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
