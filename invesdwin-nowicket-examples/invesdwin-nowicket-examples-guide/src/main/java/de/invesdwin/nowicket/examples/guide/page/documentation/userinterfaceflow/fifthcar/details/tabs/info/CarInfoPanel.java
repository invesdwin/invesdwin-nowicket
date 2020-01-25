package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.info;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CarInfoPanel extends Panel {

    public CarInfoPanel(final String id, final IModel<CarInfo> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
