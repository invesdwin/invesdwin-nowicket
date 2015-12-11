package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.upload.FileUploadField;

import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;

@NotThreadSafe
public class ModelFileUploadField extends FileUploadField {

    public ModelFileUploadField(final UploadButtonHtmlElement element) {
        super(element.getWicketId(), element.getModel());
    }

}
