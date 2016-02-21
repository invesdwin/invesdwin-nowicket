package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class ModelAjaxFormSubmitBehavior extends AjaxFormSubmitBehavior {

    public ModelAjaxFormSubmitBehavior(final String event) {
        super(event);
    }

    @Override
    protected final void onSubmit(final AjaxRequestTarget target) {
        handleEvent(target);
    }

    protected void innerOnSubmit(final AjaxRequestTarget target) {}

    @Override
    protected void onError(final AjaxRequestTarget target) {
        handleEvent(target);
    }

    private void handleEvent(final AjaxRequestTarget target) {
        try {
            //maybe a complex validator needed all inputs before it showed valid, thus revalidate now
            final boolean invalidFound = Components.updateValidModelsOnValidationError(target.getPage());
            if (!invalidFound) {
                innerOnSubmit(target);
            }
        } finally {
            GuiService.get().processRequestFinally(getComponent());
        }
    }

}
