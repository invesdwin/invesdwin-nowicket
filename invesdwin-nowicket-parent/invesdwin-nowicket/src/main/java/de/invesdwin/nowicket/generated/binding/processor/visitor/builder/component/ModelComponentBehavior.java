package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.AbstractSingleSelectChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.ModelUtilityValidator;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelComponentBehavior extends Behavior {

    private final IHtmlElement<?, ?> element;
    private final Component component;
    private final IModel<Object> targetObjectModel;

    public ModelComponentBehavior(final IHtmlElement<?, ?> element, final Component component,
            final IModel<Object> targetObjectModel) {
        this.element = element;
        this.component = component;
        this.targetObjectModel = targetObjectModel;
        addEagerBehavior();
        addNullValidBehavior();
        ModelUtilityValidator.maybeAddValidator(element, component);
        addTooltipBehavior();
    }

    private void addNullValidBehavior() {
        if (element instanceof SelectHtmlElement && component instanceof AbstractSingleSelectChoice<?>) {
            component.add(new Behavior() {
                private final AbstractSingleSelectChoice<?> choiceComponent = (AbstractSingleSelectChoice<?>) component;
                private final SelectHtmlElement selectElement = (SelectHtmlElement) element;

                @Override
                public void onConfigure(final Component component) {
                    boolean nullValidChoice = false;
                    //use modifier instead of model, since model removes null so that isNullValid properly handles null selection
                    final List<?> choices = selectElement.getModelElement()
                            .getBeanPathElement()
                            .getChoiceModifier()
                            .getValue();
                    for (final Object choice : choices) {
                        if (choice == null) {
                            nullValidChoice = true;
                            break;
                        }
                    }
                    choiceComponent.setNullValid(nullValidChoice);
                }
            });
        }
    }

    private void addEagerBehavior() {
        if (element.isEager()) {
            //support IFormComponentAware properly here
            final FormComponent<?> formComponent = Components.asFormComponent(component);
            if (formComponent == null) {
                //e.g. table
                return;
            }
            component.add(new EagerBehavior(element, component));
        }
    }

    @Override
    public void onConfigure(final Component component) {
        /*
         * disable either if the element itself is disabled or a modal panel currently creates an overlay over this
         * component. the modal check is needed so that the default button keybinding does not detect the wrong button
         */
        component.setEnabled(element.isEnabled(targetObjectModel));
        component.setVisible(element.isVisible(targetObjectModel));
    }

    private void addTooltipBehavior() {
        component.add(new Behavior() {
            @Override
            public void onComponentTag(final Component component, final ComponentTag tag) {
                final String title = element.getTooltipModel(targetObjectModel).getObject();
                if (Strings.isNotBlank(title)) {
                    //on img title is also the tooltip, not alt
                    tag.put("title", title);
                }
            }
        });
    }

    public static ModelComponentBehavior get(final Component component) {
        final List<ModelComponentBehavior> behaviors = component.getBehaviors(ModelComponentBehavior.class);
        if (behaviors.size() > 1) {
            throw new IllegalStateException("More than one " + ModelComponentBehavior.class.getSimpleName()
                    + " found on component: " + component);
        } else if (behaviors.isEmpty()) {
            return null;
        } else {
            return behaviors.get(0);
        }
    }

}
