package de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.component.header.font.FontAwesome6IconType;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.ModelTabbedPanel;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
@MountPath("tabbedpanel")
public class TabbedPanelPage extends AExampleWebPage {

    private static final String PAGE_PARAM_SELECTED_TAB = "selectedTabIndex";

    public TabbedPanelPage() {
        this(Model.of(new TabbedPanel()));
    }

    public TabbedPanelPage(final IModel<TabbedPanel> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        handleSelectedTabPageParameter();
    }

    private void handleSelectedTabPageParameter() {
        final PageParameters pageParameters = getPageParameters();
        final String selectedTab = Strings.asString(pageParameters.get(PAGE_PARAM_SELECTED_TAB));
        if (Strings.isNotBlank(selectedTab)) {
            try {
                final int selectedTabIndex = Integer.parseInt(selectedTab);
                final ModelTabbedPanel tabbedPanel = HtmlContext.get(this)
                        .getComponentRegistry()
                        .getComponent(TabbedPanelConstants.tabs);
                tabbedPanel.setSelectedTab(selectedTabIndex);
            } catch (final NumberFormatException | IndexOutOfBoundsException e) {
                //ignore
            }
            pageParameters.remove(PAGE_PARAM_SELECTED_TAB);
            throw new RestartResponseException(getClass(), pageParameters); //remove page param
        }
    }

    public static final String newLink(final String text, final boolean withIcon, final Integer selectedTab) {
        final PageParameters params = new PageParameters();
        if (selectedTab != null) {
            params.add(PAGE_PARAM_SELECTED_TAB, selectedTab);
        }
        final CharSequence href = RequestCycle.get().urlFor(TabbedPanelPage.class, params);
        String link = "<a style=\"white-space: nowrap";
        link += "\" href=\"" + href + "\">" + text;
        if (withIcon) {
            link += " <i class=\"" + FontAwesome6IconType.share_s.cssClassName() + "\"></i>";
        }
        link += "</a>";
        return link;
    }

}
