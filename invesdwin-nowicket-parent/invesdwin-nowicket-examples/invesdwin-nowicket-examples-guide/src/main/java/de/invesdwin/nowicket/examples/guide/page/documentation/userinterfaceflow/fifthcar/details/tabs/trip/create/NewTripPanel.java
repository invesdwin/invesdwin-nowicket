package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.create;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class NewTripPanel extends Panel {

    public NewTripPanel(final String id, final IModel<NewTrip> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
