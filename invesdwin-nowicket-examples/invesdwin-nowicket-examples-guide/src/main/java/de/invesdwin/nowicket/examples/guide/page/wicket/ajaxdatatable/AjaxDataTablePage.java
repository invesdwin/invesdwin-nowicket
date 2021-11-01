package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxdatatable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.DynamicModelDataTablePanel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelDataTable;

@MountPath("ajaxdatatable")
@NotThreadSafe
public class AjaxDataTablePage extends AExampleWebPage {

    private static final int MAX_ROWS_PER_PAGE = 8;

    public AjaxDataTablePage() {
        this(Model.of(new AjaxDataTable()));
    }

    public AjaxDataTablePage(final IModel<AjaxDataTable> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createTable(final TableHtmlElement e) {
                if (AjaxDataTableConstants.rows.equals(e.getWicketId())) {
                    return new DynamicModelDataTablePanel(e, MAX_ROWS_PER_PAGE) {
                        @Override
                        protected DataTable<?, ?> newDataTable(final String wicketId, final TableHtmlElement element,
                                final ISortableDataProvider<Object, String> sortableDataProvider,
                                final long rowsPerPage) {
                            return new ModelDataTable(wicketId, e, sortableDataProvider, rowsPerPage) {
                                @Override
                                protected boolean showNavigatorLabel() {
                                    return true;
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
