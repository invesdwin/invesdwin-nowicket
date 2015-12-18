package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.html.Input;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.FileUploadModel;
import de.invesdwin.nowicket.generated.markup.processor.element.UploadButtonModelElement;

@NotThreadSafe
public class UploadButtonHtmlElement extends AModelHtmlElement<UploadButtonModelElement, List<FileUpload>> {

    public static final String INPUT_TYPE_FILE = Input.file;

    public UploadButtonHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public IModel<List<FileUpload>> getModel() {
        return new FileUploadModel(this);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitFileUploadButton(this);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

}
