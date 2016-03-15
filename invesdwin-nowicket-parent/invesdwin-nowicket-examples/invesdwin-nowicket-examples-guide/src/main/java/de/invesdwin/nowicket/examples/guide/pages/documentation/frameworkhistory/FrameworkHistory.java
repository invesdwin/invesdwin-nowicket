package de.invesdwin.nowicket.examples.guide.pages.documentation.frameworkhistory;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.documentation.wicketintegration.WicketIntegration;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class FrameworkHistory extends AValueObject {

    public WicketIntegration goBackToPreviousChapter() {
        return new WicketIntegration();
    }

    public Object readNextChapter() {
        return null;
    }

    public String getHistoryImg() {
        return "History.png";
    }

}
