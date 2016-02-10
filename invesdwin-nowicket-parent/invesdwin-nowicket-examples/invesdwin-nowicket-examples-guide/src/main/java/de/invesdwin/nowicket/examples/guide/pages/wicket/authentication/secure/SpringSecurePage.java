package de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.secure;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("springsecure")
//@AuthorizeInstantiation(IExampleRoles.ADMIN) instead of auth-roles we use declarative security in the spring xml
public class SpringSecurePage extends AExampleWebPage {

    public SpringSecurePage() {
        this(Model.of(new SpringSecure()));
    }

    public SpringSecurePage(final IModel<SpringSecure> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
