package de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.tabs.tab2;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.TabbedPanelPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.tabs.TabbedPanelTabs;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class TabbedPanelTab2 extends AValueObject {

    public String getTabLinks() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<ul><li>");
        sb.append(TabbedPanelPage.newLink("Go to Tab 1 (you should still see your required input value there)", true,
                TabbedPanelTabs.TAB_INDEX_TAB1));
        sb.append("</li><li>");
        sb.append(TabbedPanelPage.newLink("Go to Tab 3 (you can enter tab 3, even if it is randomly disabled)", true,
                TabbedPanelTabs.TAB_INDEX_TAB3));
        sb.append("</li><li>");
        sb.append(TabbedPanelPage.newLink("Go to Tab 4 (you cannot enter tab 4, if it is randomly hidden)", true,
                TabbedPanelTabs.TAB_INDEX_TAB4));
        sb.append("</li></ul>");
        return sb.toString();
    }

}
