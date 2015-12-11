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
        try {
            innerOnSubmit(target);
        } finally {
            GuiService.get().processRequestFinally(getComponent());
        }
    }

    protected void innerOnSubmit(final AjaxRequestTarget target) {}

    @Override
    protected void onError(final AjaxRequestTarget target) {
        try {
            Components.updateValidModelsOnError(target.getPage());
        } finally {
            GuiService.get().processRequestFinally(getComponent());
        }
    }

}
