package com.eva.web.details;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("details")
public class DetailsPage extends AEvaWebPage {

    public DetailsPage() {
        this(Model.of(new Details()));
    }

    public DetailsPage(final IModel<Details> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
