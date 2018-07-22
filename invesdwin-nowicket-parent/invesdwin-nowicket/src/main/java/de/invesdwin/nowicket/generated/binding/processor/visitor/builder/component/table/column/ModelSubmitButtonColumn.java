package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxFallbackButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.invesdwin.nowicket.generated.binding.processor.element.ITableButtonColumn;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSubmitButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelSubmitButtonColumn extends PropertyColumn<Object, String> {

    private final ITableButtonColumn<?, ?> element;

    public ModelSubmitButtonColumn(final TableSubmitButtonColumnHtmlElement element) {
        this((ITableButtonColumn<?, ?>) element);
    }

    protected ModelSubmitButtonColumn(final ITableButtonColumn<?, ?> element) {
        super(element.getTitleModel(null), element.getColumnId());
        this.element = element;
    }

    public ITableButtonColumn<?, ?> getElement() {
        return element;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        final BootstrapAjaxFallbackButton button = newButton(componentId, rowModel);
        setIconCssClass(button);
        button.add(new ModelComponentBehavior(element, button, rowModel));
        item.add(button);
    }

    protected void setIconCssClass(final BootstrapAjaxFallbackButton button) {
        final String iconCssClass = element.getIconCssClassModel().getObject();
        if (Strings.isNotBlank(iconCssClass)) {
            button.setIconType(new IconType(iconCssClass) {
                @Override
                public String cssClassName() {
                    return getCssClassName();
                }
            });
        }
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
            protected void onSubmit(final Optional<AjaxRequestTarget> target) {
                element.getButtonCallback(rowModel).onSubmit(getForm());
            }

            @Override
            protected void onError(final Optional<AjaxRequestTarget> target) {
                element.getButtonCallback(rowModel).onError(getForm());
            }

        };
    }
}
