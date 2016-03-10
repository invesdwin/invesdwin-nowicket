package de.invesdwin.nowicket.examples.guide.pages.documentation.concept;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.documentation.wicketintegration.WicketIntegration;
import de.invesdwin.nowicket.examples.guide.pages.home.Home;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Concept extends AValueObject {

    public Home goBackToIntroduction() {
        return new Home();
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
