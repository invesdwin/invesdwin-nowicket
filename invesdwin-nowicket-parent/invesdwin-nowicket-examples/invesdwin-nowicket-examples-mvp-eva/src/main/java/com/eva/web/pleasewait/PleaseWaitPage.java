package com.eva.web.pleasewait;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("pleasewait")
@NotThreadSafe
public class PleaseWaitPage extends AEvaWebPage {

    public PleaseWaitPage(final IModel<PleaseWait> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

    public PleaseWaitPage() {
        this(Model.of(new PleaseWait()));
    }

}
