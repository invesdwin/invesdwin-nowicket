package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.norva.beanpath.annotation.Format;
import de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab.AjaxTimerTab;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.list.Lists;
import de.invesdwin.util.time.date.FDate;

@GeneratedMarkup
@NotThreadSafe
public class AjaxTimer extends AValueObject {

    private static final int MAX_TABS_COUNT = 3;
    private final AtomicInteger tabIndex = new AtomicInteger(1);

    /*
     * Use a CopyOnWriteArrayList to make this thread safe if the tabs change asynchronously caused by a different
     * thread.
     */
    private final List<AjaxTimerTab> tabs = new ArrayList<>();

    private boolean refreshAutomatically = true;

    private FDate lastRefresh = new FDate();

    public List<AjaxTimerTab> getTabs() {
        return tabs;
    }

    public void refresh() {
        lastRefresh = new FDate();
        Lists.maybeTrimSizeStart(getTabs(), MAX_TABS_COUNT);
        final AjaxTimerTab tab = new AjaxTimerTab(String.valueOf(tabIndex.incrementAndGet()));
        getTabs().add(tab);
    }

    @Eager
    public void setRefreshAutomatically(final boolean refreshAutomatically) {
        this.refreshAutomatically = refreshAutomatically;
    }

    public boolean isRefreshAutomatically() {
        return refreshAutomatically;
    }

    @Format(FDate.FORMAT_ISO_DATE_TIME_MS)
    public FDate getLastRefresh() {
        return lastRefresh;
    }

    @Format(FDate.FORMAT_ISO_DATE_TIME_MS)
    public FDate getLastRefreshCheck() {
        return new FDate();
    }

}
