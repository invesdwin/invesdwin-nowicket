package de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.norva.beanpath.annotation.ColumnOrder;
import de.invesdwin.norva.beanpath.annotation.Tabbed;
import de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab1.TabbedPanelTab1;
import de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab2.TabbedPanelTab2;
import de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab3.TabbedPanelTab3;
import de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab4.TabbedPanelTab4;
import de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.tabs.TabbedPanelTabsConstants;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.bean.AValueObject;

@ColumnOrder({ TabbedPanelTabsConstants.tab1, TabbedPanelTabsConstants.tab2, TabbedPanelTabsConstants.tab3,
        TabbedPanelTabsConstants.tab4 })
@Tabbed
@NotThreadSafe
public class TabbedPanelTabs extends AValueObject {

    public static final int TAB_INDEX_TAB1 = 0;
    public static final int TAB_INDEX_TAB2 = 1;
    public static final int TAB_INDEX_TAB3 = 2;
    public static final int TAB_INDEX_TAB4 = 3;

    private final TabbedPanelTab1 tab1 = new TabbedPanelTab1();
    private final TabbedPanelTab2 tab2 = new TabbedPanelTab2();
    private final TabbedPanelTab3 tab3 = new TabbedPanelTab3();
    private final TabbedPanelTab4 tab4 = new TabbedPanelTab4();

    private transient RequestCycle lastRandomizedRequestCycle;
    private boolean disableTab3;
    private boolean hideTab4;

    public TabbedPanelTab1 getTab1() {
        return tab1;
    }

    public TabbedPanelTab2 getTab2() {
        return tab2;
    }

    public TabbedPanelTab3 getTab3() {
        return tab3;
    }

    public TabbedPanelTab4 getTab4() {
        return tab4;
    }

    /**
     * We can also disable tabs dynamically
     */
    public boolean disableTab3() {
        randomizeOncePerRequestCycle();
        return disableTab3;
    }

    /**
     * Or hide tabs dynamically
     */
    public boolean hideTab4() {
        randomizeOncePerRequestCycle();
        return hideTab4;
    }

    private void randomizeOncePerRequestCycle() {
        if (lastRandomizedRequestCycle != RequestCycle.get()) {
            lastRandomizedRequestCycle = RequestCycle.get();
            Assertions.assertThat(lastRandomizedRequestCycle == RequestCycle.get())
                    .as("RequestCycle.get() always returns a different instance?!?")
                    .isTrue(); //sanity check
            disableTab3 = new JDKRandomGenerator().nextBoolean();
            hideTab4 = new JDKRandomGenerator().nextBoolean();
        }
    }

}
