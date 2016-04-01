package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleIFrameWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("fifthcarsearch")
public class CarSearchPage extends AExampleIFrameWebPage {

    public CarSearchPage() {
        this(Model.of(new CarSearch()));
    }

    public CarSearchPage(final IModel<CarSearch> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
