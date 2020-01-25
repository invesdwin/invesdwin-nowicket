package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxchoice;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("ajaxchoice")
@NotThreadSafe
public class AjaxChoicePage extends AExampleWebPage {

    public AjaxChoicePage() {
        this(Model.of(new AjaxChoice()));
    }

    public AjaxChoicePage(final IModel<AjaxChoice> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
