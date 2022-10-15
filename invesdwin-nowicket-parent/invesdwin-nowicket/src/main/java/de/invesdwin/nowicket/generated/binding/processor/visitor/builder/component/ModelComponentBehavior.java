package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.AbstractSingleSelectChoice;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.ModelUtilityValidator;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class ModelComponentBehavior extends Behavior {

    private final IHtmlElement<?, ?> element;
    private final Component component;
    private final IModel<Object> rootObjectModel;
    private final IModel<Object> targetObjectModel;

    public ModelComponentBehavior(final IHtmlElement<?, ?> element, final Component component,
            final IModel<Object> targetObjectModel) {
        this.element = element;
        this.component = component;
        this.rootObjectModel = element.getRootObjectModel();
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
                private final IModel<Object> rootObjectModel = selectElement.getRootObjectModel();

                @Override
                public void onConfigure(final Component component) {
                    super.onConfigure(component);
                    boolean nullValidChoice = false;
                    //use modifier instead of model, since model removes null so that isNullValid properly handles null selection
                    final List<?> choices = selectElement.getModelElement()
                            .getBeanPathElement()
                            .getChoiceModifier()
                            .getValueFromRoot(rootObjectModel.getObject());
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
        if (EagerBehavior.isAllowed(element, component)) {
            component.add(new EagerBehavior(element, component));
        }
    }

    @Override
    public void onConfigure(final Component component) {
        super.onConfigure(component);
        /*
         * disable either if the element itself is disabled or a modal panel currently creates an overlay over this
         * component. the modal check is needed so that the default button keybinding does not detect the wrong button
         */
        component.setEnabled(element.isEnabledFromTarget(rootObjectModel, targetObjectModel));
        component.setVisible(element.isVisibleFromTarget(rootObjectModel, targetObjectModel));
    }

    private void addTooltipBehavior() {
        component.add(new Behavior() {
            @Override
            public void onComponentTag(final Component component, final ComponentTag tag) {
                final String title = element.getTooltipModelFromTarget(rootObjectModel, targetObjectModel).getObject();
                if (Strings.isNotBlank(title)) {
                    //on img title is also the tooltip, not alt
                    tag.put("title", title);
                    tag.put("data-bs-toggle", "tooltip");
                    tag.put("data-bs-html", "true");
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
