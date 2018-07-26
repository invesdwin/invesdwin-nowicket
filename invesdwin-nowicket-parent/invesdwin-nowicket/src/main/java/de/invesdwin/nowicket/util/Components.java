package de.invesdwin.nowicket.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.Validatable;
import org.apache.wicket.validation.ValidatorAdapter;

import de.invesdwin.nowicket.component.modal.ModalContainer;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.IFormComponentAware;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.ModelUtilityValidator;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.error.Throwables;

@Immutable
public final class Components {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(Components.class);

    private static final MetaDataKey<Map<String, List<FeedbackMessage>>> KEY_PREVIOUS_MESSAGES = new MetaDataKey<Map<String, List<FeedbackMessage>>>() {
    };

    //CHECKSTYLE:OFF
    private static final String FROZEN_COMPONENTS_LOG_MESSAGE = "Ignoring exception cause for frozen components (maybe the update was requested too late in the request lifecycle): {}";
    //CHECKSTYLE:ON

    private Components() {}

    public static Component findRoot(final Component component) {
        Component parent = component;
        while (parent.getParent() != null) {
            parent = parent.getParent();
        }
        return parent;
    }

    public static Form<?> findForm(final Component component) {
        final FormComponent<?> formComponent = asFormComponent(component);
        if (formComponent != null) {
            return formComponent.getForm();
        }
        return findComponent(Form.class, component);
    }

    /**
     * Also checks IFormComponentAware and should be used instead of simple instanceof check
     */
    public static FormComponent<?> asFormComponent(final Component component) {
        if (component instanceof FormComponent) {
            return (FormComponent<?>) component;
        } else if (component instanceof IFormComponentAware) {
            final IFormComponentAware formComponentAware = (IFormComponentAware) component;
            return formComponentAware.getFormComponent();
        } else {
            return null;
        }
    }

    public static Object getValidatableValue(final Component component, final IValidatable<Object> validatable) {
        if (component instanceof IFormComponentAware) {
            final IFormComponentAware formComponentAware = (IFormComponentAware) component;
            return formComponentAware.getFormComponentValidatableValue(validatable);
        } else {
            return validatable.getValue();
        }
    }

    public static <T extends Component> T findComponent(final Class<T> componentType, final Component fromComponent) {
        if (componentType.isAssignableFrom(fromComponent.getClass())) {
            return (T) fromComponent;
        }
        final T parentComponent = fromComponent.findParent(componentType);
        if (parentComponent != null) {
            return parentComponent;
        }
        if (fromComponent instanceof MarkupContainer) {
            final MarkupContainer markupContainer = (MarkupContainer) fromComponent;
            final T childComponent = findChildComponent(componentType, markupContainer);
            if (childComponent != null) {
                return childComponent;
            }
        }
        return (T) null;
    }

    public static <T extends Component> T findChildComponent(final Class<T> componentType,
            final MarkupContainer fromComponent) {
        return fromComponent.visitChildren(componentType, new IVisitor<T, T>() {
            @Override
            public void component(final T object, final IVisit<T> visit) {
                visit.stop(object);
            }
        });
    }

    /**
     * Synchronize valid model values so that validator utilities have something to work with. This is useful in onError
     * calls in submit behaviors
     */
    public static boolean updateValidModelsOnValidationError(final Component component) {
        final Component root = Components.findRoot(component);
        final Form<?> form = Components.findForm(root);
        final AtomicBoolean invalidFound = new AtomicBoolean(false);
        FormComponent.visitFormComponentsPostOrder(form, new IVisitor<FormComponent<?>, Void>() {
            @Override
            public void component(final FormComponent<?> object, final IVisit<Void> visit) {
                if (object.isEnabledInHierarchy() && object.isVisibleInHierarchy()
                //fileUploadField chokes on FileNotFound if synchronized twice in a call!
                        && !(object instanceof FileUploadField)) {
                    try {
                        object.updateModel(); //fill in values regardless of valid state, since complex validation rules might require all inputs
                    } catch (final Throwable t) {
                        //ignore
                    }
                    object.validate();
                    if (!object.isValid()) {
                        invalidFound.set(true);
                    }
                }
            }
        });
        return invalidFound.get();
    }

    /**
     * Can be used to manually validate all component models again via their utility validators, thus maybe fetching
     * more errors after first synchronization was done
     */
    public static void validateModelUtilityValidators(final Component component) {
        final Component root = Components.findRoot(component);
        final Form<?> form = Components.findForm(root);
        FormComponent.visitFormComponentsPostOrder(form, new IVisitor<FormComponent<?>, Void>() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            @Override
            public void component(final FormComponent<?> object, final IVisit<Void> visit) {
                if (object.isEnabledInHierarchy() && object.isVisibleInHierarchy() && object.isValid()) {
                    final HtmlContext context = HtmlContext.get(object);
                    if (context != null) {
                        final IHtmlElement<?, ?> element = context.getElementRegistry().getElement(object.getId());
                        if (element != null) {
                            for (final IValidator<?> validator : object.getValidators()) {
                                final IValidator<?> unwrappedValidator;
                                if (validator instanceof ValidatorAdapter) {
                                    final ValidatorAdapter<?> cValidator = (ValidatorAdapter<?>) validator;
                                    unwrappedValidator = cValidator.getValidator();
                                } else {
                                    unwrappedValidator = validator;
                                }
                                if (unwrappedValidator instanceof ModelUtilityValidator) {
                                    /*
                                     * Need to use current model value instead of the normal validate value, since this
                                     * can be sometimes the wrong value internally inside wicket
                                     */
                                    final IModel<Object> model = (IModel<Object>) object.getModel();
                                    final Validatable<Object> validatable = new Validatable<Object>(model.getObject());
                                    validatable.setModel(model);
                                    unwrappedValidator.validate((IValidatable) validatable);
                                    for (final IValidationError error : validatable.getErrors()) {
                                        object.error(error);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public static boolean hasInvalidChildren(final Component component) {
        final Boolean invalid = FormComponent.visitFormComponentsPostOrder(component,
                new IVisitor<FormComponent<?>, Boolean>() {
                    @Override
                    public void component(final FormComponent<?> object, final IVisit<Boolean> visit) {
                        if (object.isEnabledInHierarchy() && object.isVisibleInHierarchy() && !object.isValid()) {
                            visit.stop(true);
                        }
                    }
                });
        return BooleanUtils.isTrue(invalid);
    }

    /**
     * This method is called on each page refresh to remember the previous state of feedback messages for rollback
     */
    public static void rememberAllFeedbackMessages(final Component component) {
        Assertions.assertThat(RequestCycle.get().getMetaData(KEY_PREVIOUS_MESSAGES)).isNull();
        //CHECKSTYLE:OFF
        final Map<String, List<FeedbackMessage>> markupId_previousMessages = new HashMap<String, List<FeedbackMessage>>();
        //CHECKSTYLE:ON
        component.getPage().visitChildren(new IVisitor<Component, Void>() {
            @Override
            public void component(final Component object, final IVisit<Void> visit) {
                if (object.hasFeedbackMessage()) {
                    final List<FeedbackMessage> previousMessages = new ArrayList<FeedbackMessage>(
                            object.getFeedbackMessages().toList());
                    markupId_previousMessages.put(object.getMarkupId(), previousMessages);
                }
            }
        });
        RequestCycle.get().setMetaData(KEY_PREVIOUS_MESSAGES, markupId_previousMessages);
    }

    public static void rollbackAllFeedbackMessages(final Component component) {
        final Map<String, List<FeedbackMessage>> markupId_previousMessages = RequestCycle.get()
                .getMetaData(KEY_PREVIOUS_MESSAGES);
        Assertions.assertThat(markupId_previousMessages).isNotNull();
        component.getPage().visitChildren(new IVisitor<Component, Void>() {
            @Override
            public void component(final Component object, final IVisit<Void> visit) {
                if (object != component) {
                    object.getFeedbackMessages().clear();
                    final List<FeedbackMessage> previousMessages = markupId_previousMessages.get(object.getMarkupId());
                    if (previousMessages != null) {
                        for (final FeedbackMessage previousMessage : previousMessages) {
                            object.getFeedbackMessages().add(previousMessage);
                        }
                    }
                }
            }
        });
        RequestCycle.get().setMetaData(KEY_PREVIOUS_MESSAGES, null);
    }

    /**
     * The root form is the most reliable way to do this, though you have to make sure to use Method.POST to not get
     * errors regarding too large requests via GET.
     */
    public static Component findComponentForDomReadyAjaxCall(final Component component) {
        return findForm(component).getRootForm();
    }

    public static void updateAllComponents(final IPartialPageRequestHandler handler, final Component component) {
        final Form<?> form = Components.findForm(component);
        if (handler != null && form != null) {
            final MarkupContainer root = (MarkupContainer) Components.findRoot(form);
            try {
                root.visitChildren(new IVisitor<Component, Void>() {
                    @Override
                    public void component(final Component object, final IVisit<Void> visit) {
                        if (object instanceof Form || object instanceof TabbedPanel
                                || object instanceof ModalContainer) {
                            handler.add(object);
                        }
                        if (!object.isVisible()) {
                            final List<ModelComponentBehavior> modelComponentBehaviors = object
                                    .getBehaviors(ModelComponentBehavior.class);
                            for (final ModelComponentBehavior behavior : modelComponentBehaviors) {
                                //update visibility manually since onConfigure() will be skipped
                                behavior.onConfigure(object);
                            }
                        }
                    }
                });
            } catch (final Throwable t) {
                handleFrozenComponentsException(t);
            }
        }
    }

    public static void updateComponents(final IPartialPageRequestHandler handler,
            final Collection<? extends Component> components) {
        if (handler != null) {
            try {
                for (final Component c : components) {
                    handler.add(c);
                }
            } catch (final Throwable t) {
                handleFrozenComponentsException(t);
            }
        }
    }

    private static void handleFrozenComponentsException(final Throwable t) {
        if (t.getMessage().contains("longer be added")) {
            LOG.warn(FROZEN_COMPONENTS_LOG_MESSAGE, Throwables.getFullStackTrace(t));
        } else {
            throw Throwables.propagate(t);
        }
    }

}
