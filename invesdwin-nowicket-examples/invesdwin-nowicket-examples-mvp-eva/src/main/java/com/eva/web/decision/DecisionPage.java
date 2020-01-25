package com.eva.web.decision;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("decision")
@NotThreadSafe
public class DecisionPage extends AEvaWebPage {

    public DecisionPage() {
        this(Model.of(new Decision()));
    }

    public DecisionPage(final IModel<Decision> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
