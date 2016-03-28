package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.TutorialStart;
import de.invesdwin.nowicket.generated.binding.annotation.Forced;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class DataTypes extends AValueObject {

    @Forced
    public TutorialStart goBackToPreviousChapter() {
        return new TutorialStart();
    }

    @Forced
    public Object readNextChapter() {
        return null;
    }

}
