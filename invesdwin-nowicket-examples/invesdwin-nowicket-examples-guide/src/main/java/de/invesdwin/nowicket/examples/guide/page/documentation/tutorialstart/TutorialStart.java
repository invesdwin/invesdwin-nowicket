package de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.DataTypes;
import de.invesdwin.nowicket.examples.guide.page.documentation.installation.Installation;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class TutorialStart extends AValueObject {

    public Installation goBackToPreviousChapter() {
        return new Installation();
    }

    public DataTypes readNextChapter() {
        return new DataTypes();
    }

}
