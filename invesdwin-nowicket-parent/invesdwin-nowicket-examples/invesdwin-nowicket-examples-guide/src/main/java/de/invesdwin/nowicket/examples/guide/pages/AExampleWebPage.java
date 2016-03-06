package de.invesdwin.nowicket.examples.guide.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.component.footer.AFooter;
import de.invesdwin.nowicket.examples.guide.pages.footer.FooterPanel;
import de.invesdwin.nowicket.examples.guide.pages.mvp.RedirectToMvpBsgcoachPage;
import de.invesdwin.nowicket.examples.guide.pages.mvp.RedirectToMvpEvaPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.ajaxchoice.AjaxChoicePage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.ajaxdatatable.AjaxDataTablePage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.secure.SpringSecurePage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.secure.WicketSecurePage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.fileupload.FileUploadPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.forminput.FormInputPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.guestbook.GuestbookExamplePage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.helloworld.HelloWorldPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.modalwindow.ModalWindowStartPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.TabbedPanelPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.WizardStartPage;

@NotThreadSafe
public abstract class AExampleWebPage extends AWebPage {

    private static final String TITLE = "invesdwin-NoWicket";

    public AExampleWebPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected Navbar newNavbar(final String id) {

        final Navbar navbar = super.newNavbar(id);
        navbar.setBrandName(Model.of(TITLE));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarDropDownButton(new ResourceModel("menu.wicket.examples")) {

                    @Override
                    public boolean isActive(final Component item) {
                        return false;
                    }

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

                }.setIconType(FontAwesomeIconType.won)));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarDropDownButton(new ResourceModel("menu.mvp.examples")) {

                    @Override
                    public boolean isActive(final Component item) {
                        return false;
                    }

                    @Override
                    protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                        final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();

                        subMenu.add(new MenuBookmarkablePageLink<Void>(RedirectToMvpBsgcoachPage.class,
                                new ResourceModel("menu.bsgcoach").wrapOnAssignment(navbar))
                                        .setIconType(FontAwesomeIconType.link));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(RedirectToMvpEvaPage.class,
                                new ResourceModel("menu.eva").wrapOnAssignment(navbar))
                                        .setIconType(FontAwesomeIconType.link));
                        return subMenu;
                    }

                }.setIconType(FontAwesomeIconType.star)));

        return navbar;
    }

    @Override
    protected AFooter newFooter(final String id) {
        return new FooterPanel(id);
    }

    @Override
    public IModel<String> getTitleModel() {
        return Model.of(super.getTitleModel().getObject() + " - " + TITLE);
    }

}
