package de.invesdwin.nowicket.examples.guide.page.documentation.wicketintegration;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.concept.Concept;
import de.invesdwin.nowicket.examples.guide.page.documentation.frameworkhistory.FrameworkHistory;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class WicketIntegration extends AValueObject {

    public Concept goBackToPreviousChapter() {
        return new Concept();
    }

    public FrameworkHistory readNextChapter() {
        return new FrameworkHistory();
    }

    public String getDevelopmentWorkflowImg() {
        return "DevelopmentWorkflow.png";
    }

    public String getWicketsInternalProcessingImg() {
        return "WicketsInternalProcessing.png";
    }

}
