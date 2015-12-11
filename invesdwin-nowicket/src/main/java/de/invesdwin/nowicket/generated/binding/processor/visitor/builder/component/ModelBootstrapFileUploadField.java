package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.BootstrapFileInputField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.FileInputConfig;
import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;

@NotThreadSafe
public class ModelBootstrapFileUploadField extends BootstrapFileInputField {

    public ModelBootstrapFileUploadField(final UploadButtonHtmlElement element) {
        this(element, newFileInputConfig());
    }

    public ModelBootstrapFileUploadField(final UploadButtonHtmlElement element, final FileInputConfig config) {
        super(element.getWicketId(), element.getModel(), config);
    }

    public static FileInputConfig newFileInputConfig() {
        return new FileInputConfig().showCaption(false).showRemove(false);
    }

    @Override
    protected ModelAjaxFormSubmitBehavior newAjaxFormSubmitBehavior(final String event) {
        return new ModelAjaxFormSubmitBehavior(event);
    }

}
