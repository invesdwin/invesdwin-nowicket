package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents.DynamicComponents;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class TagTransformations extends AValueObject {

    public DynamicComponents goBackToPreviousChapter() {
        return new DynamicComponents();
    }

    public Object readNextChapter() {
        return null;
    }

}
