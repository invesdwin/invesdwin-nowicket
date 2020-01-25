package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.steppage;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class PageWizardStep extends AValueObject {

    private final Object wizardStep;

    public PageWizardStep(final Object wizardStep) {
        this.wizardStep = wizardStep;
    }

    public Object getWizardStep() {
        return wizardStep;
    }

}
