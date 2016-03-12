package de.invesdwin.nowicket.examples.guide.pages.documentation.concept;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.documentation.introduction.Introduction;
import de.invesdwin.nowicket.examples.guide.pages.documentation.wicketintegration.WicketIntegration;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Concept extends AValueObject {

    public Introduction goBackToIntroduction() {
        return new Introduction();
    }

    public WicketIntegration readNextChapter() {
        return new WicketIntegration();
    }

    public String getContractModel2UIImg() {
        return "ContractModel2UI.png";
    }

    public String getNakedObjectsVsMvcImg() {
        return "NakedObjectsVsMVC.png";
    }

}
