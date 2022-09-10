package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.palette;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ILabelProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;

import de.invesdwin.nowicket.component.palette.BootstrapPalette;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.IFormComponentAware;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.lang.reflection.Reflections;

@NotThreadSafe
public class ModelPalette extends BootstrapPalette<Object> implements IFormComponentAware, ILabelProvider<String> {

    public static class ModelPaletteOptions {

        private Integer maxRows;
        private Boolean allowOrder;
        private Boolean allowMoveAll;

        public ModelPaletteOptions() {}

        public ModelPaletteOptions setMaxRows(final Integer maxRows) {
            this.maxRows = maxRows;
            return this;
        }

        public ModelPaletteOptions setAllowOrder(final Boolean allowOrder) {
            this.allowOrder = allowOrder;
            return this;
        }

        public ModelPaletteOptions setAllowMoveAll(final Boolean allowMoveAll) {
            this.allowMoveAll = allowMoveAll;
            return this;
        }

        public int getMaxRows(final SelectHtmlElement e) {
            if (maxRows == null) {
                return e.getMaxRows();
            } else {
                return maxRows;
            }
        }

        public boolean getAllowOrder(final SelectHtmlElement e) {
            if (allowOrder == null) {
                return false;
            } else {
                return allowOrder;
            }
        }

        public boolean getAllowMoveAll(final SelectHtmlElement e) {
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
