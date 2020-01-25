package de.invesdwin.nowicket.examples.guide.page.documentation.closingwords;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.component.header.BtnPrimaryEnterBindingJsReference;
import de.invesdwin.nowicket.examples.guide.component.cornify.CornifyHeaderContributor;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("closingwords")
@NotThreadSafe
public class ClosingWordsPage extends AExampleWebPage {

    public ClosingWordsPage() {
        this(Model.of(new ClosingWords()));
    }

    public ClosingWordsPage(final IModel<ClosingWords> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        CornifyHeaderContributor.INSTANCE.renderHead(response);
        BtnPrimaryEnterBindingJsReference.INSTANCE.renderHead(response);
    }

}
