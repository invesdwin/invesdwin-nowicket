package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tabbedandpanels;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Tabbed;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.CarTabs;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.TripRow;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.create.NewTrip;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class TabbedAndPanels extends AValueObject {

    @Tabbed
    private final CarTabs defaultTabbed = new CarTabs("LunarIndustries", "MO-ON 1234") {
        {
            final NewTrip trip1 = new NewTrip();
            trip1.setDistanceInKM(384403);
            trip1.setFrom("Earth");
            trip1.setTo("Moon");
            getTripInfo().getTripBook().add(new TripRow(trip1));

            final NewTrip trip2 = new NewTrip();
            trip2.setDistanceInKM(384403);
            trip2.setFrom("Moon");
            trip2.setTo("Earth");
            getTripInfo().getTripBook().add(new TripRow(trip2));
        }
    };
    @Tabbed
    private final CarTabs asUnorderedList = (CarTabs) defaultTabbed.clone();
    @Tabbed
    private final CarTabs asCollapsible = (CarTabs) defaultTabbed.clone();
    @Tabbed
    private final CarTabs asAccordion = (CarTabs) defaultTabbed.clone();

    public CarTabs getDefaultTabbed() {
        return defaultTabbed;
    }

    public CarTabs getAsUnorderedList() {
        return asUnorderedList;
    }

    public CarTabs getAsCollapsible() {
        return asCollapsible;
    }

    public CarTabs getAsAccordion() {
        return asAccordion;
    }

}
