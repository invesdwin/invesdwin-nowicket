package de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.tabs.TabbedPanelTabs;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Objects;

@GeneratedMarkup
@NotThreadSafe
public class TabbedPanel extends AValueObject {

    private final TabbedPanelTabs tabs = new TabbedPanelTabs();

    //we could also put @Tabbed here if the type itself does not have the annotation
    public TabbedPanelTabs getTabs() {
        return tabs;
    }

    @Override
    public int hashCode() {
        //configure page cache to always reuse instance
        return getClass().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        //configure page cache to always reuse instance
        if (obj == null) {
            return false;
        }
        return Objects.equals(getClass(), obj.getClass());
    }

}
