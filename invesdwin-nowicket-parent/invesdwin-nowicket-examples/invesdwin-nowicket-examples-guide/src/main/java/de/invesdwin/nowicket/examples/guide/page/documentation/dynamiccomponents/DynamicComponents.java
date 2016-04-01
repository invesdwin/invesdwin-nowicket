package de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.UserInterfaceFlow;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class DynamicComponents extends AValueObject {

    public UserInterfaceFlow goBackToPreviousChapter() {
        return new UserInterfaceFlow();
    }

    public Object readNextChapter() {
        return null;
    }

}
