package com.eva.web.feedback;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("feedback")
public class FeedbackPage extends AEvaWebPage {

    public FeedbackPage(final IModel<Feedback> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

    public FeedbackPage() {
        this(Model.of(new Feedback()));
    }

}
