package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.steppage;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.PanelFactory;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;

@NotThreadSafe
public class PageWizardStepPage extends AExampleWebPage {

    public PageWizardStepPage(final IModel<PageWizardStep> model) {
        super(model);
        final Panel panel = PanelFactory.get().getPanel("panel", model.getObject().getWizardStep());
        add(panel);
    }

}
