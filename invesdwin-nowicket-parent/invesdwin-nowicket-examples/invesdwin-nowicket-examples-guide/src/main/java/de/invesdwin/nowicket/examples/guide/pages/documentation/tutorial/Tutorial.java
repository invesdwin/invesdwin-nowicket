package de.invesdwin.nowicket.examples.guide.pages.documentation.tutorial;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.documentation.installation.Installation;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class Tutorial extends AValueObject {

    public Installation goBackToPreviousChapter() {
        return new Installation();
    }

    public Object readNextChapter() {
        return null;
    }

}
