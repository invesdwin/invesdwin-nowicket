package de.invesdwin.nowicket.examples.guide.page.documentation.closingwords;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.TagTransformations;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class ClosingWords extends AValueObject {

    public TagTransformations goBackToPreviousChapter() {
        return new TagTransformations();
    }

}
