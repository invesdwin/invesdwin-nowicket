package de.invesdwin.nowicket.examples.guide.page.documentation.installation;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelLabel;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
@MountPath("installation")
public class InstallationPage extends AExampleWebPage {

    public InstallationPage() {
        this(Model.of(new Installation()));
    }

    public InstallationPage(final IModel<Installation> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (Strings.equalsAny(e.getWicketId(), InstallationConstants.pomXmlContent,
                        InstallationConstants.webXmlContent, InstallationConstants.pageHtmlContent)) {
                    return new ModelLabel(e);
                }
                return super.create(e);
            }
        }).bind();
    }

}
