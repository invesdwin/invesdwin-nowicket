package com.bsgcoach.web.request;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.bsgcoach.web.ABsgCoachWebPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("request")
@NotThreadSafe
public class RequestPage extends ABsgCoachWebPage {

    public RequestPage() {
        this(Model.of(new Request()));
    }

    public RequestPage(final IModel<Request> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }
}
