package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback;

import java.lang.reflect.UndeclaredThrowableException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import de.invesdwin.norva.beanpath.spi.element.simple.invoker.IBeanPathActionInvoker;
import de.invesdwin.nowicket.component.pnotify.PNotifyBehavior;
import de.invesdwin.nowicket.component.pnotify.PNotifyPosition;
import de.invesdwin.nowicket.component.pnotify.PNotifyType;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.time.date.FTimeUnit;
import de.invesdwin.util.time.duration.Duration;

@NotThreadSafe
public class DefaultSubmitButtonCallback implements ISubmitButtonCallback {

    private final IHtmlElement<?, ?> element;
    private final IModel<Object> targetObjectModel;
    private final IBeanPathActionInvoker invoker;
    private final PNotifyBehavior validationErrorNotificationBehavior;

    public DefaultSubmitButtonCallback(final IHtmlElement<?, ?> element, final IModel<Object> targetObjectModel,
            final IBeanPathActionInvoker invoker, final PNotifyBehavior validationErrorNotificationBehavior) {
        this.element = element;
        this.targetObjectModel = targetObjectModel;
        this.invoker = invoker;
        this.validationErrorNotificationBehavior = validationErrorNotificationBehavior;
    }

    public DefaultSubmitButtonCallback(final IHtmlElement<?, ?> element, final IModel<Object> targetObjectModel,
            final IBeanPathActionInvoker invoker) {
        this(element, targetObjectModel, invoker, newValidationErrorNotificationBehavior());
    }

    @Override
    public void onSubmit(final Component component) {
        handleEvent(component);
    }

    protected void invokeButtonMethod(final Component component) {
        try {
            final Object result = invoker.invokeFromTarget(targetObjectModel.getObject());
            processResult(result);
        } catch (final UndeclaredThrowableException e) {
            handleButtonException(component, e.getUndeclaredThrowable());
        } catch (final Throwable t) {
            handleButtonException(component, t);
        }
    }

    protected void processResult(final Object result) {
        if (shouldHideModalPanel()) {
            GuiService.get().hideModalPanel();
        }
        if (result != null) {
            if (element.isModalOpener()) {
                GuiService.get().showModalPanel(result);
            } else {
                GuiService.get().showPage(result);
            }
        }
    }

    protected void handleButtonException(final Component component, final Throwable t) {
        element.getContext().getSubmitButtonExceptionHandler().handleSubmitButtonException(element, component, t);
    }

    protected boolean shouldHideModalPanel() {
        return GuiService.get().isModalPanelShowing() && element.isModalCloser();
    }

    @Override
    public void onError(final Component component) {
        handleEvent(component);
    }

    private void handleEvent(final Component component) {
        try {
            //maybe a complex validator needed all inputs before it showed valid, thus revalidate now
            final boolean invalidFound = Components.updateValidModelsOnValidationError(component);
            if (invalidFound) {
                if (element.isForced()) {
                    invokeForcedButtonOnValidationError(component);
                } else {
                    showButtonNotExecutedBecauseOfValidationErrorWarning(component);
                }
            } else {
                invokeButtonMethod(component);
            }
        } finally {
            processRequestFinally(component);
        }
    }

    protected void processRequestFinally(final Component component) {
        GuiService.get().processRequestFinally(component);
    }

    protected void showButtonNotExecutedBecauseOfValidationErrorWarning(final Component component) {
        if (validationErrorNotificationBehavior != null) {
            component.add(validationErrorNotificationBehavior);
        }
    }

    protected void invokeForcedButtonOnValidationError(final Component component) {
        /*
         * On forced button method we need to synchronize all valid properties into the model and reset all invalid ones
         * with the values of the model.
         */
        final Component root = Components.findRoot(component);
        final Form<?> form = Components.findForm(root);
        FormComponent.visitFormComponentsPostOrder(form, new IVisitor<FormComponent<?>, Void>() {
            @Override
            public void component(final FormComponent<?> object, final IVisit<Void> visit) {
                if (object.isEnabledInHierarchy() && object.isVisibleInHierarchy() && !object.isValid()) {
                    /*
                     * need to clear individual components besides the form to make GridColumnBorder not display error
                     * even though there should be none because form was cleared
                     */
                    object.clearInput();
                    object.getFeedbackMessages().clear();
                }
            }
        });
        //clear the form itself
        form.clearInput();
        form.getSession().getFeedbackMessages().clear();
        invokeButtonMethod(component);
    }

    public static PNotifyBehavior newValidationErrorNotificationBehavior() {
        return new PNotifyBehavior().withIconOnly()
                .withButtons(false)
                .withPosition(PNotifyPosition.top_right)
                .withType(PNotifyType.error)
                .withDuration(new Duration(1, FTimeUnit.SECONDS))
                .withStack(false);
    }

}
