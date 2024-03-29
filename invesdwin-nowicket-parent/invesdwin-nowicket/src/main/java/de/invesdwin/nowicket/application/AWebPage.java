package de.invesdwin.nowicket.application;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxNewWindowNotifyingBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.init.WebApplicationInitializer;
import de.invesdwin.nowicket.application.filter.internal.ModelCacheUsingPageFactory;
import de.invesdwin.nowicket.component.footer.AFooter;
import de.invesdwin.nowicket.component.header.ModernizrMinJavaScriptReference;
import de.invesdwin.nowicket.page.auth.SignIn;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public abstract class AWebPage extends org.apache.wicket.markup.html.WebPage
        implements IAjaxIndicatorAware, IPageFactoryHook {

    public static final String DEFAULT_CONTAINER_CLASS = "container";

    public static final ResourceReference DEFAULT_LOGO = new PackageResourceReference(AWebPage.class, "logo.png");

    private IModel<String> titleModel;
    private AjaxIndicatorAppender ajaxIndicatorAppender;

    public AWebPage(final IModel<?> model) {
        this(model, null);
    }

    public AWebPage(final IModel<?> model, final PageParameters params) {
        super(model);
        if (params != null) {
            getPageParameters().mergeWith(params);
        }
        /*
         * prevent endless loop when a gui task is added in the page constructor regarding gui tasks holder access
         */
        RequestCycles.setPage(this);
        if (ABaseWebApplication.get().getDelegate().getAuthenticationService() != null) {
            //check remember me before anything else
            final SignIn signIn = new SignIn();
            signIn.setComponent(this);
            signIn.checkRememberMe();
        }

        final HtmlTag htmlTag = newHtmlTag("html");
        add(htmlTag);
        final TransparentWebMarkupContainer bodyTag = newBodyTag("body");
        add(bodyTag);
        //default title is the classname, though this can be changed by calling the setter afterwards
        titleModel = Model.of(
                Objects.toVisibleName(Strings.removeTrailing(getClass().getSimpleName(), Page.class.getSimpleName())));
        final Label title = new Label("title", new IModel<String>() {
            @Override
            public String getObject() {
                return getTitleModel().getObject();
            }
        });
        add(title);
        //need a separate container inside body to prevent it from rendering outside of the html tag
        final WebMarkupContainer ajaxIndicatorAppenderContainer = new WebMarkupContainer(
                "ajaxIndicatorAppenderContainer");
        add(ajaxIndicatorAppenderContainer);
        ajaxIndicatorAppender = newAjaxIndicatorAppender();
        if (ajaxIndicatorAppender != null) {
            ajaxIndicatorAppenderContainer.add(ajaxIndicatorAppender);
        }

        final Navbar navbar = newNavbar("navbar");
        if (navbar != null) {
            add(navbar);
        } else {
            add(new EmptyPanel("navbar").setVisible(false));
        }

        DebugBar debugBar = null;
        if (getApplication().getDebugSettings().isDevelopmentUtilitiesEnabled()) {
            debugBar = newDebugBar("debugBar");
        }
        if (debugBar != null) {
            add(debugBar);
        } else {
            add(new EmptyPanel("debugBar").setVisible(false));
        }

        add(newContainer("bodyContainer"));
        final AFooter footer = newFooter("footer");
        if (footer != null) {
            footer.setContainerClass(getContainerClass());
            add(footer);
        } else {
            add(new WebMarkupContainer("footer").setVisible(false));
        }

        final AjaxNewWindowNotifyingBehavior newWindowBehavior = newAjaxNewWindowNotifyingBehavior();
        if (newWindowBehavior != null) {
            add(newWindowBehavior);
        }

        add(new HeaderResponseContainer("footerContainer", WebApplicationInitializer.JS_FOOTER_BUCKET));
    }

    /**
     * https://stackoverflow.com/questions/29508123/wicket-multi-tab-ajax-page-serialization
     */
    protected AjaxNewWindowNotifyingBehavior newAjaxNewWindowNotifyingBehavior() {
        return new AjaxNewWindowNotifyingBehavior() {
            @Override
            protected void onNewWindow(final AjaxRequestTarget target) {
                ModelCacheUsingPageFactory.onNewWindow(getComponent(), target.getPage());
            }

            @Override
            protected String findIndicatorId() {
                //disable the ajax indicator for these updates
                return null;
            }
        };
    }

    protected HtmlTag newHtmlTag(final String id) {
        return new HtmlTag(id, getLocale(), true) {
            @Override
            protected HeaderItem newModernizrHeaderItem() {
                //fix 404 because only minified modernizr exists
                return JavaScriptHeaderItem.forReference(ModernizrMinJavaScriptReference.instance());
            }
        };
    }

    protected TransparentWebMarkupContainer newBodyTag(final String id) {
        return new TransparentWebMarkupContainer(id);
    }

    /**
     * See:
     * http://stackoverflow.com/questions/5068142/can-a-wicketchild-tag-be-nested-under-another-component-on-the-page
     */
    private TransparentWebMarkupContainer newContainer(final String id) {
        final TransparentWebMarkupContainer container = new TransparentWebMarkupContainer(id);
        container.add(AttributeModifier.replace("class", getContainerClass()));
        return container;
    }

    /**
     * With this you are able to change from container-fluid to container. Return null here to disable bootstrap
     * container class and to provide your own css for this
     */
    protected String getContainerClass() {
        return DEFAULT_CONTAINER_CLASS;
    }

    protected Navbar newNavbar(final String id) {
        final AWebPageNavbar navbar = newWebPageNavbar(id);
        navbar.setBrandName(Model.of("invesdwin-NoWicket"));
        navbar.setBrandImage(DEFAULT_LOGO, Model.of("logo"));

        addSignInOrSignOutPageToNavbar(navbar);

        return navbar;
    }

    protected AWebPageNavbar newWebPageNavbar(final String id) {
        return new AWebPageNavbar(id) {
            @Override
            protected AWebPage getWebPageParent() {
                return AWebPage.this;
            }
        };
    }

    protected void addSignInOrSignOutPageToNavbar(final Navbar navbar) {
        final ABaseWebApplication webApplication = ABaseWebApplication.get();
        if (webApplication.getDelegate().getAuthenticationService() != null) {
            if (AWebSession.get().isSignedIn()) {
                addSignOutNavbarComponent(navbar, webApplication.getSignOutPage());
            } else {
                addSignInNavbarComponent(navbar, webApplication.getSignInPage());
            }
        }
    }

    protected void addSignInNavbarComponent(final Navbar navbar, final Class<? extends WebPage> signInPage) {
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.RIGHT,
                new NavbarButton<Void>(signInPage, new ResourceModel("menu.sign.in"))
                        .setIconType(FontAwesome6IconType.right_to_bracket_s)));
    }

    protected void addSignOutNavbarComponent(final Navbar navbar, final Class<? extends WebPage> signOutPage) {
        navbar.addComponents(NavbarComponents.transform(ComponentPosition.RIGHT,
                new NavbarButton<Void>(signOutPage, new ResourceModel("menu.sign.out"))
                        .setIconType(FontAwesome6IconType.right_from_bracket_s)));
    }

    protected Class<? extends Page> getNavbarHomePage() {
        return ABaseWebApplication.get().getHomePage();
    }

    protected DebugBar newDebugBar(final String id) {
        return new DebugBar(id);
    }

    protected AjaxIndicatorAppender newAjaxIndicatorAppender() {
        return new AjaxIndicatorAppender();
    }

    public IModel<String> getTitleModel() {
        return titleModel;
    }

    public final void setTitleModel(final IModel<String> titleModel) {
        this.titleModel = titleModel;
    }

    @Override
    public String getAjaxIndicatorMarkupId() {
        if (ajaxIndicatorAppender != null) {
            return ajaxIndicatorAppender.getMarkupId();
        } else {
            return null;
        }
    }

    public void setResponsePage(final Object modelObject) {
        super.setResponsePage(PageFactory.get().getPage(modelObject));
    }

    public void setResponsePage(final Object modelObject, final PageParameters params) {
        super.setResponsePage(PageFactory.get().findPageClass(modelObject), params);
    }

    /**
     * With this you can provide a footer panel with links and a disclaimer.
     */
    protected AFooter newFooter(final String id) {
        return null;
    }

    @Override
    public void onPageModelRefresh(final Object newModelObject) {}
}
