package de.invesdwin.nowicket.examples.guide.page.documentation.validation;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.DataTypes;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Validation extends AValueObject {

    public DataTypes goBackToPreviousChapter() {
        return new DataTypes();
    }

    public Object readNextChapter() {
        return null;
    }

}
