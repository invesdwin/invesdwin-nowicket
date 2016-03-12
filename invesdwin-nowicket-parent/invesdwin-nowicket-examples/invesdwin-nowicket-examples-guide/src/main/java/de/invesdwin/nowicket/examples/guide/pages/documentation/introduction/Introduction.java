package de.invesdwin.nowicket.examples.guide.pages.documentation.introduction;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.documentation.concept.Concept;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Introduction extends AValueObject {

    public Concept readFirstChapter() {
        return new Concept();
    }

    public String title() {
        return "Introduction";
    }

}
