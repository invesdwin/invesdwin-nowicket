package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("dynamiceditorfields")
public class DynamicEditorFieldsPage extends AExampleWebPage {

    public DynamicEditorFieldsPage() {
        this(Model.of(new DynamicEditorFields()));
    }

    public DynamicEditorFieldsPage(final IModel<DynamicEditorFields> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
