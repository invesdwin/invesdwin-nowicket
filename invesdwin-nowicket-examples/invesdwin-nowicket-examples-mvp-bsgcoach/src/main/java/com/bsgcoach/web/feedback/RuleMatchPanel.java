package com.bsgcoach.web.feedback;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class RuleMatchPanel extends Panel {

    public RuleMatchPanel(final String id, final IModel<RuleMatch> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
