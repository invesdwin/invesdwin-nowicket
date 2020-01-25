package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleIFrameWebPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.CarSearchPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("fifthcar")
public class CarDetailsPage extends AExampleIFrameWebPage {

    public CarDetailsPage() {
        super(null);
        //redirect to car search
        throw new RestartResponseAtInterceptPageException(CarSearchPage.class);
    }

    public CarDetailsPage(final IModel<CarDetails> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
