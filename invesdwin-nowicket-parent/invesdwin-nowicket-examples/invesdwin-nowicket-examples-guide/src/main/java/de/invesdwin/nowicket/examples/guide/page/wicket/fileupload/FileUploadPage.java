package de.invesdwin.nowicket.examples.guide.page.wicket.fileupload;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.FormHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.DefaultBindingBuilder;

@NotThreadSafe
@MountPath("fileupload")
public class FileUploadPage extends AExampleWebPage {

    public FileUploadPage() {
        this(Model.of(new FileUpload()));
    }

    public FileUploadPage(final IModel<FileUpload> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createForm(final FormHtmlElement e) {
                final Form<?> form = (Form<?>) new DefaultBindingBuilder().createForm(e);
                // Set maximum size per file to 90K for demo purposes
                form.setFileMaxSize(Bytes.kilobytes(90));
                // Set maximum size to 100K for demo purposes
                form.setMaxSize(Bytes.kilobytes(100));
                return form;

            }
        }).bind();
    }

}
