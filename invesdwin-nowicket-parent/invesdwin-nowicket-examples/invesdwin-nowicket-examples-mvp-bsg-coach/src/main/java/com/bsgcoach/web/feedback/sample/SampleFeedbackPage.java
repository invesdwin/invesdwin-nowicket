package com.bsgcoach.web.feedback.sample;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

import com.bsgcoach.web.feedback.FeedbackPage;

@MountPath("sampleFeedback")
@NotThreadSafe
public class SampleFeedbackPage extends FeedbackPage {

    public SampleFeedbackPage() {
        super();
    }

    public SampleFeedbackPage(final IModel<SampleFeedback> model) {
        super(model);
    }

}
