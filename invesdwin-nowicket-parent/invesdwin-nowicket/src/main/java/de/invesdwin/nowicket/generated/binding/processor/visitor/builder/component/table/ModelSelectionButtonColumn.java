package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxFallbackButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.invesdwin.norva.beanpath.spi.element.simple.modifier.SelectionBeanPathPropertyModifier;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSelectionButtonColumnHtmlElement;

@NotThreadSafe
public class ModelSelectionButtonColumn extends ModelSubmitButtonColumn {

    public static final String CSS_CLASS = "table-selection-column";

    public ModelSelectionButtonColumn(final TableSelectionButtonColumnHtmlElement element) {
        super(element);
    }

    @Override
    public Component getHeader(final String componentId) {
        //no header per default to keep column size as small as possible
        return new WebMarkupContainer(componentId).setVisible(false);
    }

    @Override
    public TableSelectionButtonColumnHtmlElement getElement() {
        return (TableSelectionButtonColumnHtmlElement) super.getElement();
    }

    @Override
    protected void setIconCssClass(final BootstrapAjaxFallbackButton button) {
        //no-op
    }

    @Override
    protected BootstrapAjaxFallbackButton newButton(final String componentId,
            final org.apache.wicket.model.IModel<Object> rowModel) {
        final BootstrapAjaxFallbackButton button = super.newButton(componentId, rowModel);
        button.add(new Behavior() {
            @Override
            public void onConfigure(final Component component) {
                //dynamic icon
                final Object row = rowModel.getObject();
                final SelectionBeanPathPropertyModifier selectionModifier = getElement().getModelElement()
                        .getBeanPathElement()
                        .getSelectionModifier();
                if (selectionModifier.isSelected(row)) {
                    button.setIconType(getSelectedIcon());
                } else {
                    button.setIconType(getUnselectedIcon());
                }
            }
        });
        //remove border around the checked button
        button.setType(Type.Link);
        button.add(CssClassNameModifier.append("class", getCssClass()));
        return button;
    }

    protected IconType getUnselectedIcon() {
        return GlyphIconType.unchecked;
    }

    protected IconType getSelectedIcon() {
        return GlyphIconType.check;
    }

    @Override
    public String getCssClass() {
        return CSS_CLASS;
    }

}
