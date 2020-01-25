package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.norva.beanpath.annotation.Tabbed;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.CarSearch;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.CarTabs;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@Eager
@NotThreadSafe
@GeneratedMarkup
public class CarDetails extends AValueObject {

    private final CarSearch parent;
    private final CarTabs tabs;

    public CarDetails(final CarSearch parent, final String brand, final String licenseNumber) {
        this.parent = parent;
        this.tabs = new CarTabs(brand, licenseNumber);
    }

    @Tabbed
    public CarTabs getTabs() {
        return tabs;
    }

    public CarSearch back() {
        return parent;
    }
}
