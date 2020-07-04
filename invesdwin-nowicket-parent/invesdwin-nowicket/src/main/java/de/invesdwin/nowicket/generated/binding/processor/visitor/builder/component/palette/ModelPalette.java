package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.palette;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ILabelProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.validation.IValidatable;

import de.invesdwin.nowicket.component.palette.Palette;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.IFormComponentAware;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.lang.reflection.Reflections;

@NotThreadSafe
public class ModelPalette extends Palette<Object> implements IFormComponentAware, ILabelProvider<String> {

    public static class ModelPaletteOptions {

        private Integer maxRows;
        private Boolean allowOrder;
        private Boolean allowMoveAll;

        public ModelPaletteOptions() {
        }

        public ModelPaletteOptions withMaxRows(final Integer maxRows) {
            this.maxRows = maxRows;
            return this;
        }

        public ModelPaletteOptions withAllowOrder(final Boolean allowOrder) {
            this.allowOrder = allowOrder;
            return this;
        }

        public ModelPaletteOptions withAllowMoveAll(final Boolean allowMoveAll) {
            this.allowMoveAll = allowMoveAll;
            return this;
        }

        private int getMaxRows(final SelectHtmlElement e) {
            if (maxRows == null) {
                return e.getMaxRows();
            } else {
                return maxRows;
            }
        }

        private boolean getAllowOrder(final SelectHtmlElement e) {
            if (allowOrder == null) {
                return false;
            } else {
                return allowOrder;
            }
        }

        private boolean getAllowMoveAll(final SelectHtmlElement e) {
            if (allowMoveAll == null) {
                return getMaxRows(e) >= 5;
            } else {
                return allowMoveAll;
            }
        }

    }

    private final IModel<String> label;

    public ModelPalette(final SelectHtmlElement e) {
        this(e, new ModelPaletteOptions());
    }

    public ModelPalette(final SelectHtmlElement e, final ModelPaletteOptions options) {
        super(e.getWicketId(), e.getSelectionModel(), e.getChoiceModel(), e.getChoiceRenderer(), options.getMaxRows(e),
                options.getAllowOrder(e), options.getAllowMoveAll(e));
        setOutputMarkupId(true);
        label = e.getTitleModel();

        Assertions.assertThat(getFormComponent()).isNull();
        Reflections.method("initFactories").in(this).invoke();
        Assertions.assertThat(getFormComponent()).isNotNull();
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear(); //remove styling from it
        super.onComponentTag(tag);
    }

    @Override
    protected Component newAddAllComponent() {
        return addButtonCss(super.newAddAllComponent());
    }

    @Override
    protected Component newRemoveAllComponent() {
        return addButtonCss(super.newRemoveAllComponent());
    }

    @Override
    protected Component newAddComponent() {
        return addButtonCss(super.newAddComponent());
    }

    @Override
    protected Component newRemoveComponent() {
        return addButtonCss(super.newRemoveComponent());
    }

    @Override
    protected Component newUpComponent() {
        return addButtonCss(super.newUpComponent());
    }

    @Override
    protected Component newDownComponent() {
        return addButtonCss(super.newDownComponent());
    }

    protected Component addButtonCss(final Component button) {
        return button.add(AttributeModifier.append("class", "btn btn-default btn-xs"));
    }

    @Override
    protected ResourceReference getCSS() {
        return new CssResourceReference(getClass(), getClass().getSimpleName() + ".css");
    }

    /**
     * This allows model validators and feedback messages to work.
     */
    @Override
    public FormComponent<?> getFormComponent() {
        return getRecorderComponent();
    }

    /**
     * This allows to validate the value in validator utility method.
     */
    @Override
    public Object getFormComponentValidatableValue(final IValidatable<?> validatable) {
        final String newIds = (String) validatable.getValue();
        final List<Object> selected = new ArrayList<Object>();

        if (Strings.isNotBlank(newIds)) {
            final Map<String, Object> id_choice = new HashMap<String, Object>();
            for (final Object choice : getChoices()) {
                id_choice.put(getChoiceRenderer().getIdValue(choice, 0), choice);
            }
            for (final String newId : Strings.splitPreserveAllTokens(newIds, ",")) {
                final Object choice = id_choice.get(newId);
                if (choice != null) {
                    selected.add(choice);
                } else {
                    throw new IllegalArgumentException("No choice found for id [" + newId + "]");
                }
            }
        }

        return selected;
    }

    @Override
    public IModel<String> getLabel() {
        return label;
    }
}
