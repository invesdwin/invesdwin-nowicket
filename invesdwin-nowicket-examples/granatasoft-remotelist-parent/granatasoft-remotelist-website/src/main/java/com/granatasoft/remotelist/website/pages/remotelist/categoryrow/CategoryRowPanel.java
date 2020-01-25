package com.granatasoft.remotelist.website.pages.remotelist.categoryrow;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.granatasoft.remotelist.website.components.login.button.LoginButtonsPanel;
import com.granatasoft.remotelist.website.pages.remotelist.applicationinstancerow.ApplicationInstanceRow;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.DynamicModelDataTablePanel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelDataTable;

@NotThreadSafe
public class CategoryRowPanel extends Panel {

    public CategoryRowPanel(final String id, final IModel<CategoryRow> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected IColumn<Object, String> createTableColumn(final ATableColumnHtmlElement<?, ?> e) {
                if (e.getWicketId().equals(CategoryRowConstants.applicationInstances_login)) {
                    return new IColumn<Object, String>() {

                        @Override
                        public void populateItem(final Item<ICellPopulator<Object>> cellItem, final String componentId,
                                final IModel<Object> rowModel) {
                            final ApplicationInstanceRow appRow = (ApplicationInstanceRow) rowModel.getObject();
                            cellItem.add(new LoginButtonsPanel(componentId, Model.of(appRow.getLogin())));
                        }

                        @Override
                        public void detach() {}

                        @Override
                        public Component getHeader(final String componentId) {
                            return null;
                        }

                        @Override
                        public String getSortProperty() {
                            return e.getColumnId();
                        }

                        @Override
                        public boolean isSortable() {
                            return true;
                        }

                    };
                } else if (e.getWicketId().equals(CategoryRowConstants.applicationInstances_applicationLogo)) {
                    return new IColumn<Object, String>() {

                        @Override
                        public void populateItem(final Item<ICellPopulator<Object>> cellItem, final String componentId,
                                final IModel<Object> rowModel) {
                            final ApplicationInstanceRow appRow = (ApplicationInstanceRow) rowModel.getObject();

                            cellItem.add(new Image(componentId, appRow.getApplicationLogo()) {
                                @Override
                                protected void onComponentTag(final ComponentTag tag) {
                                    tag.setName("img");
                                    super.onComponentTag(tag);
                                }

                                @Override
                                public boolean isVisible() {
                                    return appRow.getApplicationLogo() != null;
                                }
                            });
                        }

                        @Override
                        public void detach() {}

                        @Override
                        public Component getHeader(final String componentId) {
                            return null;
                        }

                        @Override
                        public String getSortProperty() {
                            return e.getColumnId();
                        }

                        @Override
                        public boolean isSortable() {
                            return true;
                        }
                    };
                }

                return super.createTableColumn(e);
            }

            @Override
            public Component createTable(final TableHtmlElement e) {
                if (e.getWicketId().equals(CategoryRowConstants.applicationInstances)) {
                    return new DynamicModelDataTablePanel(e) {
                        @Override
                        protected DataTable<?, ?> newDataTable(final String wicketId, final TableHtmlElement element,
                                final ISortableDataProvider<Object, String> sortableDataProvider,
                                final long rowsPerPage) {
                            return new ModelDataTable(wicketId, element, sortableDataProvider, rowsPerPage) {
                                @Override
                                protected AbstractToolbar newHeadersToolbar() {
                                    return null;
                                }

                                @Override
                                protected Item<IColumn<Object, String>> newCellItem(final String id, final int index,
                                        final IModel<IColumn<Object, String>> model) {
                                    final Item<IColumn<Object, String>> cell = super.newCellItem(id, index, model);
                                    if (index == 1) {
                                        cell.add(AttributeModifier.append("width", "100%"));
                                    }
                                    return cell;
                                }
                            };
                        }
                    };
                }
                return super.createTable(e);
            }
        }).bind();
    }
}
