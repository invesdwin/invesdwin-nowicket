package de.invesdwin.nowicket.examples.guide.pages.documentation.wicketintegration;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.documentation.concept.Concept;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class WicketIntegration extends AValueObject {

    public Concept goBackToConcept() {
        return new Concept();
    }

    public Object readNextChapter() {
        return null;
    }

}
