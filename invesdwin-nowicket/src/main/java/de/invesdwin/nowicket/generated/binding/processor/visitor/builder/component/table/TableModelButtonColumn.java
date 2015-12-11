package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxFallbackButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSubmitButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class TableModelButtonColumn extends PropertyColumn<Object, String> {

    private final TableSubmitButtonColumnHtmlElement element;

    public TableModelButtonColumn(final TableSubmitButtonColumnHtmlElement element) {
        super(element.getTitleModel(null), element.getColumnId());
        this.element = element;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        final BootstrapAjaxFallbackButton button = newButton(componentId, rowModel);
        final String iconCssClass = element.getIconCssClassModel().getObject();
        if (Strings.isNotBlank(iconCssClass)) {
            button.setIconType(new IconType(iconCssClass) {
                @Override
                public String cssClassName() {
                    return getCssClassName();
                }
            });
        }
        button.add(new ModelComponentBehavior(element, button, rowModel));
        item.add(button);
    }

    protected BootstrapAjaxFallbackButton newButton(final String componentId, final IModel<Object> rowModel) {
        return new BootstrapAjaxFallbackButton(componentId, null, Type.Default) {

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                //fix wicket bootstrap complaining about wrong tag
                tag.setName("button");
                super.onComponentTag(tag);
            }

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                element.getButtonCallback(rowModel).onSubmit(form);
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                element.getButtonCallback(rowModel).onError(form);
            }

        };
    }
}
