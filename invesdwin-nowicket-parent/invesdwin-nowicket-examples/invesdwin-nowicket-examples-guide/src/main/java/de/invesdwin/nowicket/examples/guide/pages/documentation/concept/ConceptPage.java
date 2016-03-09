package de.invesdwin.nowicket.examples.guide.pages.documentation.concept;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("concept")
public class ConceptPage extends AExampleWebPage {

    public ConceptPage() {
        this(Model.of(new Concept()));
    }

    public ConceptPage(final IModel<Concept> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
