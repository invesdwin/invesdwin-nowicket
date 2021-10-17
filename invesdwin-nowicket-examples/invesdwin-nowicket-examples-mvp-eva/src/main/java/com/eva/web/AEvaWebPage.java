package com.eva.web;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Page;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
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

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.component.footer.AFooter;

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

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(RedirectToGuidePage.class, Model.of("Home")).setIconType(GlyphIconType.home)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(ABaseWebApplication.get().getHomePage(), Model.of("Decision"))
                        .setIconType(GlyphIconType.home)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(PleaseWaitPage.class, Model.of("Ask EVA"))
                        .setIconType(FontAwesomeIconType.question_circle)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(FeedbackPage.class, Model.of("Feedback"))
                        .setIconType(FontAwesomeIconType.exclamation_circle)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(DashboardPage.class, Model.of("Dashboard"))
                        .setIconType(FontAwesomeIconType.tachometer)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(DetailsPage.class, Model.of("Details"))
                        .setIconType(FontAwesomeIconType.search)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT,
                new NavbarExternalLink(Model.of("mailto:gsubes@gmail.com")).setLabel(Model.of(""))
                        .setIconType(GlyphIconType.envelope)));

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
        if (AWebApplication.get().usesDeploymentConfig()) {
            //CHECKSTYLE:OFF fdate
            response.render(JavaScriptHeaderItem
                    .forScript("(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){" //
                            + "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o)," //
                            + "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)" //
                            + "})(window,document,'script','//www.google-analytics.com/analytics.js','ga');" //
                            + "ga('create', 'UA-75774568-1', 'auto');" //
                            + "ga('send', 'pageview');", "googleAnalytics"));
            //CHECKSTYLE:ON
        }
    }
}
