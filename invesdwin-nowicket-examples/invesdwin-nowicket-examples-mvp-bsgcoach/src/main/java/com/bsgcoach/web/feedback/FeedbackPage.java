package com.bsgcoach.web.feedback;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

import com.bsgcoach.web.ABsgCoachWebPage;
import com.bsgcoach.web.request.RequestPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("feedback")
@NotThreadSafe
public class FeedbackPage extends ABsgCoachWebPage {

    public FeedbackPage() {
        super(null);
        setResponsePage(RequestPage.class);
    }

    public FeedbackPage(final IModel<? extends Feedback> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
