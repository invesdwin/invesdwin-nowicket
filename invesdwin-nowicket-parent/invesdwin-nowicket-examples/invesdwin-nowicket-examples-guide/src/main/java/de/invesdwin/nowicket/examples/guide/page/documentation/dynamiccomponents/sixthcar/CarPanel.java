package de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents.sixthcar;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CarPanel extends Panel {

    public CarPanel(final String id, final IModel<Car> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
