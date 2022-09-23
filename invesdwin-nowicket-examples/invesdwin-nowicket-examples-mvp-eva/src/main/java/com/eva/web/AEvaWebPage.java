package com.eva.web;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Page;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.eva.web.dashboard.DashboardPage;
import com.eva.web.details.DetailsPage;
import com.eva.web.feedback.FeedbackPage;
import com.eva.web.footer.FooterPanel;
import com.eva.web.guide.RedirectToGuidePage;
import com.eva.web.pleasewait.PleaseWaitPage;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.component.footer.AFooter;
import de.invesdwin.nowicket.component.navbar.Navbar;

@NotThreadSafe
public abstract class AEvaWebPage extends AWebPage {

    private static final ResourceReference LOGO = new PackageResourceReference(AEvaWebPage.class, "logo.png");

    public AEvaWebPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected Navbar newNavbar(final String id) {
        final Navbar navbar = super.newNavbar(id);
        navbar.setBrandName(null);
        navbar.setBrandImage(LOGO, Model.of("EVA"));

        navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                new NavbarButton<Void>(RedirectToGuidePage.class, Model.of("Home"))
                        .setIconType(FontAwesome6IconType.house_s)));
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                new NavbarButton<Void>(ABaseWebApplication.get().getHomePage(), Model.of("Decision"))
                        .setIconType(FontAwesome6IconType.house_s)));
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                new NavbarButton<Void>(PleaseWaitPage.class, Model.of("Ask EVA"))
                        .setIconType(FontAwesome6IconType.circle_question_s)));
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                new NavbarButton<Void>(FeedbackPage.class, Model.of("Feedback"))
                        .setIconType(FontAwesome6IconType.circle_exclamation_s)));
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                new NavbarButton<Void>(DashboardPage.class, Model.of("Dashboard"))
                        .setIconType(FontAwesome6IconType.gauge_s)));
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                new NavbarButton<Void>(DetailsPage.class, Model.of("Details"))
                        .setIconType(FontAwesome6IconType.magnifying_glass_s)));
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.RIGHT,
                new NavbarExternalLink(Model.of("mailto:gsubes@gmail.com")).setLabel(Model.of(""))
                        .setIconType(FontAwesome6IconType.envelope_s)));

        return navbar;
    }

    @Override
    protected Class<? extends Page> getNavbarHomePage() {
        return RedirectToGuidePage.class;
    }

    @Override
    protected AFooter newFooter(final String id) {
        return new FooterPanel(id);
    }

    @Override
    public IModel<String> getTitleModel() {
        return Model.of(super.getTitleModel().getObject() + " - EVA");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        //CHECKSTYLE:OFF
        //        if (AWebApplication.get().usesDeploymentConfig()) {
        //            response.render(JavaScriptHeaderItem
        //                    .forScript("(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){" //
        //                            + "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o)," //
        //                            + "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)" //
        //                            + "})(window,document,'script','//www.google-analytics.com/analytics.js','ga');" //
        //                            + "ga('create', 'UA-75774568-1', 'auto');" //
        //                            + "ga('send', 'pageview');", "googleAnalytics"));
        //        }
        //CHECKSTYLE:ON
    }
}
