package com.granatasoft.remotelist.website.pages.remotelist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.granatasoft.remotelist.website.components.badges.ACustomTitlePanel;
import com.granatasoft.remotelist.website.pages.ARemotelistPage;
import com.granatasoft.remotelist.website.pages.remotelist.categoryrow.CategoryRow;
import com.granatasoft.remotelist.website.pages.remotelist.categoryrow.CategoryRowPanel;

import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelCollapsibleList;

@NotThreadSafe
@AuthorizeInstantiation(Roles.USER)
public class ShowCategoriesPage extends ARemotelistPage {

    public ShowCategoriesPage() {
        this(Model.of(new ShowCategories()));
    }

    public ShowCategoriesPage(final IModel<ShowCategories> model) {
        super(model);

        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (e.getWicketId().equals(ShowCategoriesConstants.categories)) {
                    final IModel<? extends Collection<? extends ITab>> iModel = new AbstractReadOnlyModel<Collection<? extends ITab>>() {

                        @Override
                        public Collection<? extends ITab> getObject() {
                            final List<ITab> tabs = new ArrayList<ITab>();
                            for (final CategoryRow category : model.getObject().getCategories()) {
                                tabs.add(new ITab() {

                                    @Override
                                    public boolean isVisible() {
                                        return true;
                                    }

                                    @Override
                                    public IModel<String> getTitle() {
                                        return Model.of(category.getCategoryName());
                                    }

                                    @Override
                                    public WebMarkupContainer getPanel(final String containerId) {
                                        return new CategoryRowPanel(containerId, Model.of(category));
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
                                this.getCollapsibles().get(0).setActive(true);
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
                                            final CategoryRow modelObject = (CategoryRow) getPanelModel().getObject();
                                            return modelObject.getApplicationInstances().size() + "";
                                        }
                                    };
                                    final CategoryRow categoryRow = (CategoryRow) tab.getPanel("foo")
                                            .getDefaultModelObject();
                                    return new ACustomTitlePanel(markupId, categoryRow.getCategoryLogo(), badgeModel) {

                                        @Override
                                        protected Component newLink(final String id) {
                                            return superNewTitle(id, tab);
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
