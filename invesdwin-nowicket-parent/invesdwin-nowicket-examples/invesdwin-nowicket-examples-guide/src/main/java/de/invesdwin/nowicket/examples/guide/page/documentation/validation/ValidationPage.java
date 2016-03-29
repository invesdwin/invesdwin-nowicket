package de.invesdwin.nowicket.examples.guide.page.documentation.validation;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("validation")
@NotThreadSafe
public class ValidationPage extends AExampleWebPage {

    public ValidationPage() {
        this(Model.of(new Validation()));
    }

    public ValidationPage(final IModel<Validation> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
