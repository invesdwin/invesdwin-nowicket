package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.io.File;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.component.DelegateModel;
import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;

@NotThreadSafe
public class FileAnchorModel extends DelegateModel<File> {

    public FileAnchorModel(final AnchorHtmlElement element) {
        super(new BeanPathModel<File>(element));
    }

    public FileAnchorModel(final IModel<?> rootObjectModel, final String beanPath) {
        super(new BeanPathModel<File>(rootObjectModel, beanPath));
    }

    @Override
    public File getObject() {
        final File file = super.getObject();
        if (file == null) {
            return null;
        } else if (file.isAbsolute()) {
            return file;
        } else {
            //make path absolute
            final String basePath = ABaseWebApplication.get().getServletContext().getRealPath(".");
            return new File(basePath, file.getPath());
        }
    }

}
