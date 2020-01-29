package de.invesdwin.nowicket.examples.guide.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink.Target;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.component.consent.CookieConsent;
import de.invesdwin.nowicket.component.footer.AFooter;
import de.invesdwin.nowicket.examples.guide.component.prettify.PrettifyHeaderContributor;
import de.invesdwin.nowicket.examples.guide.page.documentation.closingwords.ClosingWordsPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.concept.ConceptPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.DataTypesPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents.DynamicComponentsPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.frameworkhistory.FrameworkHistoryPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.installation.InstallationPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.introduction.IntroductionPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.TagTransformationsPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.TutorialStartPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.UserInterfaceFlowPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.validation.ValidationPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.wicketintegration.WicketIntegrationPage;
import de.invesdwin.nowicket.examples.guide.page.footer.FooterPanel;
import de.invesdwin.nowicket.examples.guide.page.wicket.ajaxchoice.AjaxChoicePage;
import de.invesdwin.nowicket.examples.guide.page.wicket.ajaxdatatable.AjaxDataTablePage;
import de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.AjaxTimerPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.authentication.secure.SpringSecurePage;
import de.invesdwin.nowicket.examples.guide.page.wicket.authentication.secure.WicketSecurePage;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.DynamicEditorFieldsPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.fileupload.FileUploadPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.forminput.FormInputPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.guestbook.GuestbookExamplePage;
import de.invesdwin.nowicket.examples.guide.page.wicket.helloworld.HelloWorldPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.modalwindow.ModalWindowStartPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.TabbedPanelPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.websocket.WebSocketPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.WizardStartPage;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.lang.uri.URIs;

@NotThreadSafe
public abstract class AExampleWebPage extends AWebPage {

    private static final String PAGE_PARAM_THEME = "theme";
    private static final String TITLE = "invesdwin-NoWicket";
    private static final ResourceReference LOGO = new PackageResourceReference(AExampleWebPage.class, "logo.png");

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(AExampleWebPage.class);

    public AExampleWebPage(final IModel<?> model) {
        super(model);

        add(new WebMarkupContainer("sumome") {
            @Override
            public boolean isVisible() {
                return AWebApplication.get().usesDeploymentConfig();
            }
        });

        add(new CookieConsent("cookieConsent", Model.of(URIs.asUri("http://invesdwin.de/privacypolicy.html"))));
    }

    @Override
    protected Navbar newNavbar(final String id) {

        final Navbar navbar = super.newNavbar(id);
        navbar.setBrandName(Model.of(TITLE));
        navbar.setBrandImage(LOGO, Model.of("logo"));

        addDocumentationNav(navbar, ComponentPosition.LEFT);
        addWicketExamplesNav(navbar, ComponentPosition.LEFT);
        addSourceCodeNav(navbar, ComponentPosition.RIGHT);
        addThemesNav(navbar, ComponentPosition.RIGHT);

        return navbar;
    }

    private void addThemesNav(final Navbar navbar, final ComponentPosition position) {
        final DropDownButton dropdown = new NavbarDropDownButton(Model.of(" Themes")) {

            @Override
            public boolean isActive(final Component item) {
                return false;
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
                final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
                final List<ITheme> themes = settings.getThemeProvider().available();

                for (final ITheme theme : themes) {
                    final PageParameters params = new PageParameters();
                    final String themeName = theme.name();
                    params.set(PAGE_PARAM_THEME, themeName);

                    final IModel<String> labelModel = new IModel<String>() {
                        @Override
                        public String getObject() {
                            final String activeThemeName = Bootstrap.getSettings(getApplication())
                                    .getActiveThemeProvider()
                                    .getActiveTheme()
                                    .name();
                            String themeLabel = themeName.toLowerCase();
                            if (themeName.equals(activeThemeName)) {
                                themeLabel = "<b>" + themeLabel + "</b>";
                            }
                            return themeLabel;
                        }
                    };

                    subMenu.add(new MenuBookmarkablePageLink<Void>(getPageClass(), params, labelModel) {

                        @Override
                        protected Component newLabel(final String markupId) {
                            final Label label = (Label) super.newLabel(markupId);
                            label.setEscapeModelStrings(false);
                            return label;
                        }

                    });
                }

                return subMenu;
            }
        }.setIconType(GlyphIconType.book);
        navbar.addComponents(new ImmutableNavbarComponent(dropdown, position));
    }

    private void addSourceCodeNav(final Navbar navbar, final ComponentPosition position) {
        navbar.addComponents(NavbarComponents.transform(position, new NavbarExternalLink(Model.of(
                "https://github.com/subes/invesdwin-nowicket/tree/master/invesdwin-nowicket-examples/invesdwin-nowicket-examples-guide/src/main/java/de/invesdwin/nowicket/examples/guide/page")) {

            @Override
            protected Component newLabel(final String markupId) {
                final Label label = (Label) super.newLabel(markupId);
                label.setEscapeModelStrings(false);
                return label;
            }

        }.setTarget(Target.blank).setLabel(new ResourceModel("menu.source.code"))));
    }

    private void addWicketExamplesNav(final Navbar navbar, final ComponentPosition position) {
        navbar.addComponents(NavbarComponents.transform(position,
                new NavbarDropDownButton(new ResourceModel("menu.wicket.examples")) {

                    @Override
                    protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                        final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();

                        subMenu.add(new MenuBookmarkablePageLink<Void>(HelloWorldPage.class,
                                new ResourceModel("menu.helloworld").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.comment));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(FormInputPage.class,
                                new ResourceModel("menu.forminput").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.listalt));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(AjaxChoicePage.class,
                                new ResourceModel("menu.ajaxchoice").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.alignjustify));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(AjaxDataTablePage.class,
                                new ResourceModel("menu.ajaxdatatable").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.th));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(ModalWindowStartPage.class,
                                new ResourceModel("menu.modal").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.newwindow));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(WizardStartPage.class,
                                new ResourceModel("menu.wizard").wrapOnAssignment(navbar))
                                        .setIconType(FontAwesomeIconType.magic));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(TabbedPanelPage.class,
                                new ResourceModel("menu.tabbedpanel").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.creditcard));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(FileUploadPage.class,
                                new ResourceModel("menu.fileupload").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.upload));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(DynamicEditorFieldsPage.class,
                                new ResourceModel("menu.dynamiceditorfields").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.edit));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(AjaxTimerPage.class,
                                new ResourceModel("menu.ajaxtimer").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.refresh));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(WebSocketPage.class,
                                new ResourceModel("menu.websocket").wrapOnAssignment(navbar))
                                        .setIconType(FontAwesomeIconType.plug));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(GuestbookExamplePage.class,
                                new ResourceModel("menu.guestbook").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.book));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(WicketSecurePage.class,
                                new ResourceModel("menu.wicketsecure").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.lock));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(SpringSecurePage.class,
                                new ResourceModel("menu.springsecure").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.lock));

                        if (Roles.isAuthenticated()) {
                            subMenu.add(new MenuBookmarkablePageLink<Void>(ABaseWebApplication.get().getSignOutPage(),
                                    new ResourceModel("menu.sign.out").wrapOnAssignment(navbar))
                                            .setIconType(GlyphIconType.logout));
                        } else {
                            subMenu.add(new MenuBookmarkablePageLink<Void>(ABaseWebApplication.get().getSignInPage(),
                                    new ResourceModel("menu.sign.in").wrapOnAssignment(navbar))
                                            .setIconType(GlyphIconType.login));
                        }

                        return subMenu;
                    }

                    @Override
                    protected Component newButtonLabel(final String markupId, final IModel<?> labelModel) {
                        final Label label = (Label) super.newButtonLabel(markupId, labelModel);
                        label.setEscapeModelStrings(false);
                        return label;
                    }

                }));
    }

    private void addDocumentationNav(final Navbar navbar, final ComponentPosition position) {
        navbar.addComponents(
                NavbarComponents.transform(position, new NavbarDropDownButton(new ResourceModel("menu.documentation")) {

                    @Override
                    protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                        final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();

                        subMenu.add(new MenuBookmarkablePageLink<Void>(IntroductionPage.class,
                                new ResourceModel("menu.introduction").wrapOnAssignment(navbar))
                                        .setIconType(GlyphIconType.home));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(ConceptPage.class,
                                new ResourceModel("menu.concept").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(WicketIntegrationPage.class,
                                new ResourceModel("menu.wicketintegration").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(FrameworkHistoryPage.class,
                                new ResourceModel("menu.frameworkhistory").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(InstallationPage.class,
                                new ResourceModel("menu.installation").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(TutorialStartPage.class,
                                new ResourceModel("menu.tutorialstart").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(DataTypesPage.class,
                                new ResourceModel("menu.datatypes").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(ValidationPage.class,
                                new ResourceModel("menu.validation").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(UserInterfaceFlowPage.class,
                                new ResourceModel("menu.userinterfaceflow").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(DynamicComponentsPage.class,
                                new ResourceModel("menu.dynamiccomponents").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(TagTransformationsPage.class,
                                new ResourceModel("menu.tagtransformations").wrapOnAssignment(navbar)));

                        subMenu.add(new MenuBookmarkablePageLink<Void>(ClosingWordsPage.class,
                                new ResourceModel("menu.closingwords").wrapOnAssignment(navbar))
                                        .setIconType(FontAwesomeIconType.microphone));

                        return subMenu;
                    }

                    @Override
                    protected Component newButtonLabel(final String markupId, final IModel<?> labelModel) {
                        final Label label = (Label) super.newButtonLabel(markupId, labelModel);
                        label.setEscapeModelStrings(false);
                        return label;
                    }

                }));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        handleThemePageParameter();
    }

    private void handleThemePageParameter() {
        final PageParameters pageParameters = getPageParameters();
        final String theme = Strings.asString(pageParameters.get(PAGE_PARAM_THEME));
        if (Strings.isNotBlank(theme)) {
            final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
            try {
                settings.getActiveThemeProvider().setActiveTheme(theme);
            } catch (final Throwable t) {
                LOG.catching(t);
            }
            pageParameters.remove(PAGE_PARAM_THEME);
            throw new RestartResponseException(getClass(), pageParameters); //remove page param
        }
    }

    @Override
    protected void addSignInOrSignOutPageToNavbar(final Navbar navbar) {
        //do not add the button
    }

    @Override
    protected AFooter newFooter(final String id) {
        return new FooterPanel(id);
    }

    @Override
    public IModel<String> getTitleModel() {
        return Model.of(super.getTitleModel().getObject() + " - " + TITLE);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        PrettifyHeaderContributor.INSTANCE.renderHead(response);
        response.render(
                CssHeaderItem.forCSS(".jumbotron .has-error .help-block { font-size: 14px }", "fixJumbotronHasError"));
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
