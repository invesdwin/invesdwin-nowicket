package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.validation.Validation;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class UserInterfaceFlow extends AValueObject {

    public Validation goBackToPreviousChapter() {
        return new Validation();
    }

    public Object readNextChapter() {
        return null;
    }

}
