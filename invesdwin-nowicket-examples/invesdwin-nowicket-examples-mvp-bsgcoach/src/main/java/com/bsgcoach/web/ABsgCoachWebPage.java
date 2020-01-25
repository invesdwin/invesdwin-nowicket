package com.bsgcoach.web;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Page;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.bsgcoach.web.footer.FooterPanel;
import com.bsgcoach.web.guide.RedirectToGuidePage;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.component.footer.AFooter;

@NotThreadSafe
public abstract class ABsgCoachWebPage extends AWebPage {

    private static final boolean INVERTED_HEADER_AND_FOOTER = true;
    private static final ResourceReference LOGO = new PackageResourceReference(ABsgCoachWebPage.class, "logo.png");
    private static final PackageResourceReference BACKGROUND = new PackageResourceReference(ABsgCoachWebPage.class,
            "bg.jpg");

    public ABsgCoachWebPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected Navbar newNavbar(final String id) {
        final Navbar navbar = super.newNavbar(id);
        navbar.setBrandName(null);
        navbar.setBrandImage(LOGO, Model.of("bsg-coach"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(RedirectToGuidePage.class, Model.of("Home")).setIconType(GlyphIconType.home)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(ABaseWebApplication.get().getHomePage(), Model.of("Get Feedback"))
                        .setIconType(GlyphIconType.upload)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT,
                new NavbarExternalLink(Model.of("mailto:gsubes@gmail.com"))
                        .setLabel(Model.of("Tell us what you think!")).setIconType(GlyphIconType.envelope)));

        navbar.setInverted(INVERTED_HEADER_AND_FOOTER);

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
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        final StringBuilder bgCss = new StringBuilder();
        bgCss.append("body {\n");
        bgCss.append("    background: url(");
        bgCss.append(RequestCycle.get().urlFor(BACKGROUND, null));
        bgCss.append(") no-repeat center center fixed;\n");
        bgCss.append("}\n");
        bgCss.append("nav {\n");
        bgCss.append("    opacity: 0.75;\n");
        bgCss.append("}\n");
        bgCss.append(".footer .panel-footer {\n");
        bgCss.append("    background-color: #222;\n");
        bgCss.append("    border-color: #080808;\n");
        bgCss.append("    opacity: 0.75;\n");
        bgCss.append("}\n");
        response.render(CssHeaderItem.forCSS(bgCss, "bsgBgCss"));

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
