package de.invesdwin.nowicket.examples.guide.pages.wicket.ajaxdatatable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxFallbackButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSubmitButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelDataTable;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.TableModelButtonColumn;

@MountPath("ajaxdatatable")
@NotThreadSafe
public class AjaxDataTablePage extends AExampleWebPage {

    private static final int MAX_ROWS_PER_PAGE = 8;

    public AjaxDataTablePage() {
        this(Model.of(new AjaxDataTable()));
    }

    public AjaxDataTablePage(final IModel<AjaxDataTable> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createTable(final TableHtmlElement e) {
                if (AjaxDataTableConstants.rows.equals(e.getWicketId())) {
                    return new ModelDataTable(e, MAX_ROWS_PER_PAGE) {
                        @Override
                        protected boolean showNavigatorLabel() {
                            return true;
                        }
                    };
                }
                return super.createTable(e);
            }

            @Override
            public IColumn<Object, String> createSubmitButtonColumn(final TableSubmitButtonColumnHtmlElement e) {
                if (e.getColumnId().equals(AjaxDataTableRowConstants.select)) {
                    return new TableModelButtonColumn(e) {
                        @Override
                        protected BootstrapAjaxFallbackButton newButton(final String componentId,
                                final org.apache.wicket.model.IModel<Object> rowModel) {
                            final BootstrapAjaxFallbackButton button = super.newButton(componentId, rowModel);
                            button.add(new Behavior() {
                                @Override
                                public void onConfigure(final Component component) {
                                    //dynamic icon
                                    final AjaxDataTableRow row = (AjaxDataTableRow) rowModel.getObject();
                                    if (row.isSelected()) {
                                        button.setIconType(GlyphIconType.check);
                                    } else {
                                        button.setIconType(GlyphIconType.unchecked);
                                    }
                                }
                            });
                            //remove border around the checked button
                            button.setType(Type.Link);
                            return button;
                        };
                    };
                }
                return super.createSubmitButtonColumn(e);
            }
        }).bind();
    }

}
