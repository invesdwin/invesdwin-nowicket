package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackHeadersToolbar;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxNavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.orderby.IconOrderByBorder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.UpdatingReuseIfModelsEqualStrategy;

/**
 * Please use DynamicModelDataTablePanel as a wrapper around this component for full support of validation and
 * columnOrder.
 */
@NotThreadSafe
public class ModelDataTable extends DataTable<Object, String> {

    private final TableHtmlElement element;

    public ModelDataTable(final String wicketId, final TableHtmlElement element,
            final List<? extends IColumn<Object, String>> columns,
            final ISortableDataProvider<Object, String> sortableDataProvider, final long rowsPerPage) {
        super(wicketId, columns, sortableDataProvider, rowsPerPage);
        this.element = element;
        setOutputMarkupId(true);
        setItemReuseStrategy(UpdatingReuseIfModelsEqualStrategy.getInstance());
        final AbstractToolbar headersToolbar = newHeadersToolbar();
        if (headersToolbar != null) {
            addTopToolbar(headersToolbar);
        }
        final NoRecordsToolbar noRecordsToolbar = newNoRecordsToolbar();
        if (noRecordsToolbar != null) {
            addBottomToolbar(noRecordsToolbar);
        }
        final NavigationToolbar navigationToolbar = newNavigationToolbar();
        if (navigationToolbar != null) {
            addBottomToolbar(navigationToolbar);
        }
    }

    public static ModelSortableDataProvider newSortableDataProvider(final TableHtmlElement element) {
        return new ModelSortableDataProvider(element);
    }

    public TableHtmlElement getElement() {
        return element;
    }

    public ISortableDataProvider<Object, String> getSortableDataProvider() {
        return (ISortableDataProvider<Object, String>) getDataProvider();
    }

    protected AbstractToolbar newHeadersToolbar() {
        return new AjaxFallbackHeadersToolbar<String>(this, (ISortableDataProvider<Object, String>) getDataProvider()) {

            @Override
            public MarkupContainer add(final Component... children) {
                for (int i = 0; i < children.length; i++) {
                    final Component child = children[i];
                    if (child instanceof RefreshingView<?> && "headers".equals(child.getId())) {
                        final RefreshingView<?> cChild = (RefreshingView<?>) child;
                        cChild.setItemReuseStrategy(UpdatingReuseIfModelsEqualStrategy.getInstance());
                    }
                }
                return super.add(children);
            }

            @Override
            protected WebMarkupContainer newSortableHeader(final String headerId, final String property,
                    final ISortStateLocator<String> locator) {
                return ModelDataTable.this.newSortableHeader(headerId, property, locator, getTable());
            }
        };
    }

    protected WebMarkupContainer newSortableHeader(final String headerId, final String property,
            final ISortStateLocator<String> locator, final DataTable<?, ?> table) {
        return new IconOrderByBorder<String>(headerId, property, locator, table);
    }

    protected NoRecordsToolbar newNoRecordsToolbar() {
        return new NoRecordsToolbar(this);
    }

    protected NavigationToolbar newNavigationToolbar() {
        return new AjaxNavigationToolbar(this) {
            @Override
            protected PagingNavigator newPagingNavigator(final String navigatorId, final DataTable<?, ?> table) {
                return new BootstrapAjaxPagingNavigator(navigatorId, table) {
                    @Override
                    protected void onComponentTag(final ComponentTag tag) {
                        //fix complaints about the tag being a div and not an ul
                        tag.setName("ul");
                        super.onComponentTag(tag);
                    }
                };
            }

            @Override
            protected Component newNavigatorLabel(final String navigatorId, final DataTable<?, ?> table) {
                //hide label
                final Component component = super.newNavigatorLabel(navigatorId, table);
                component.setVisible(showNavigatorLabel());
                return component;
            }

        };
    }

    /**
     * Override to enable navigator label. Disabled per default to keep the table less cluttered
     */
    protected boolean showNavigatorLabel() {
        return false;
    }

    @Override
    protected Item<Object> newRowItem(final String id, final int index, final IModel<Object> model) {
        return new OddEvenItem<Object>(id, index, model);
    }

    @Override
    protected DataGridView<Object> newDataGridView(final String id,
            final List<? extends IColumn<Object, String>> columns, final IDataProvider<Object> dataProvider) {
        return super.newDataGridView(id, columns, dataProvider);
    }

}
