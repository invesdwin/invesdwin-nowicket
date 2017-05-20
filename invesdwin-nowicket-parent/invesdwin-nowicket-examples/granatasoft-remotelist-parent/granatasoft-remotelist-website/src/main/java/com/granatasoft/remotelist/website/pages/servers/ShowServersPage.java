package com.granatasoft.remotelist.website.pages.servers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.granatasoft.remotelist.website.components.login.button.LoginButtons;
import com.granatasoft.remotelist.website.components.login.button.LoginButtonsPanel;
import com.granatasoft.remotelist.website.components.login.button.child.LoginButtonsChild;
import com.granatasoft.remotelist.website.components.login.button.child.LoginButtonsChildPanel;
import com.granatasoft.remotelist.website.pages.ARemotelistPage;
import com.granatasoft.remotelist.website.pages.servers.serverpaneltitle.AServerTitlePanel;
import com.granatasoft.remotelist.website.pages.servers.serverpaneltitle.Crud;
import com.granatasoft.remotelist.website.pages.servers.serverpaneltitle.CrudPanel;
import com.granatasoft.remotelist.website.pages.servers.serverrow.ServerRow;
import com.granatasoft.remotelist.website.pages.servers.serverrow.ServerRowPanel;

import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelCollapsibleList;

@NotThreadSafe
@MountPath("/servers")
@AuthorizeInstantiation(Roles.USER)
public class ShowServersPage extends ARemotelistPage {

    public ShowServersPage() {
        this(Model.of(new ShowServers()));
    }

    public ShowServersPage(final IModel<ShowServers> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (e.getWicketId().equals(ShowServersConstants.servers)) {
                    final IModel<? extends Collection<? extends ITab>> iModel = new AbstractReadOnlyModel<Collection<? extends ITab>>() {

                        @Override
                        public Collection<? extends ITab> getObject() {
                            final List<ITab> tabs = new ArrayList<ITab>();
                            for (final ServerRow server : model.getObject().getServers()) {
                                tabs.add(new ITab() {

                                    @Override
                                    public boolean isVisible() {
                                        return true;
                                    }

                                    @Override
                                    public IModel<String> getTitle() {
                                        return Model.of(server.getName());
                                    }

                                    @Override
                                    public WebMarkupContainer getPanel(final String containerId) {
                                        return new ServerRowPanel(containerId, Model.of(server));
                                    }
                                });
                            }
                            return tabs;
                        }
                    };

                    return new ModelCollapsibleList(e.getWicketId(), iModel) {
                        private boolean isFirstRender = true;

                        @Override
                        protected void onBeforeRender() {
                            super.onBeforeRender();
                            if (isFirstRender && this.getCollapsibles().size() > 0) {
                                isFirstRender = false;
                                for (final AccordionCollapsible collapsible : this.getCollapsibles()) {
                                    if (((ServerRow) collapsible.getPanelModel().getObject()).getApplications()
                                            .size() > 0) {
                                        collapsible.setActive(true);
                                        return;
                                    }
                                }
                            }
                        }

                        @Override
                        protected AccordionCollapsible newAccordionCollapsible(final String componentId,
                                final ITab tab) {
                            return new AccordionCollapsible(componentId, tab) {
                                @Override
                                protected Component newTitle(final String markupId, final ITab tab) {
                                    final IModel<String> badgeModel = new AbstractReadOnlyModel<String>() {
                                        @Override
                                        public String getObject() {
                                            final ServerRow serverRowRowModelObject = (ServerRow) getPanelModel()
                                                    .getObject();
                                            return serverRowRowModelObject.getApplications().size() + "";
                                        }
                                    };

                                    //TODO: this hard reference does not play well when things are edited
                                    final ServerRow serverRowRow = (ServerRow) tab.getPanel("foo")
                                            .getDefaultModelObject();

                                    return new AServerTitlePanel(markupId, null, badgeModel) {

                                        @Override
                                        protected Component newCrudPanel(final String id) {

                                            return new CrudPanel(id, Model.of(new Crud(serverRowRow)));
                                        }

                                        @Override
                                        protected Component newButton(final String id) {

                                            final LoginButtons buttonModel = new LoginButtons(serverRowRow.getServer());
                                            return new LoginButtonsPanel(id, Model.of(buttonModel)) {
                                                @Override
                                                public Component newMasterButtonToggle(final String id) {
                                                    return new WebMarkupContainer(id)
                                                            .add(AttributeAppender.append("class", "btn-sm"));
                                                }

                                                @Override
                                                protected Component newToolbar(final String id) {
                                                    return super.newToolbar(id)
                                                            .add(AttributeModifier.append("class", "pull-right"));
                                                }

                                                @Override
                                                public LoginButtonsChildPanel newLoginButtonsChildPanel(final String id,
                                                        final IModel<LoginButtonsChild> model,
                                                        final boolean addBtnClasses) {
                                                    return new LoginButtonsChildPanel(id, model, addBtnClasses) {
                                                        @Override
                                                        public String getBtnClass() {
                                                            return "btn btn-default btn-sm";
                                                        }
                                                    };

                                                }
                                            };

                                        }

                                        @Override
                                        protected Component newLink(final String id) {
                                            final Component component = superNewTitle(id, tab);
                                            if (serverRowRow.getApplications().size() == 0) {
                                                component.setEnabled(false);
                                            }

                                            return component;

                                        }
                                    };
                                }

                                protected Component superNewTitle(final String markupId, final ITab tab) {
                                    return super.newTitle(markupId, tab);
                                }
                            };
                        }
                    };
                }

                return super.create(e);
            }
        }).bind();
    }
}
