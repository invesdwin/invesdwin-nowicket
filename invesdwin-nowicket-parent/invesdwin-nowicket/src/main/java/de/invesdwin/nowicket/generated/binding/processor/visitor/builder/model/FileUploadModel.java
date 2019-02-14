package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;

@NotThreadSafe
public class FileUploadModel implements IModel<List<FileUpload>> {

    private final UploadButtonHtmlElement element;

    public FileUploadModel(final UploadButtonHtmlElement element) {
        this.element = element;
    }

    @Override
    public void detach() {
        //ignore
    }

    @Override
    public List<FileUpload> getObject() {
        //ignore
        return null;
    }

    @Override
    public void setObject(final List<FileUpload> object) {
        if (object != null) {
            //convert uploads to files
            final List<File> files = new ArrayList<File>();
            for (final FileUpload upload : object) {
                final File file = newFile(upload);
                FileUtils.deleteQuietly(file);
                try {
                    FileUtils.forceMkdir(file.getParentFile());
                    upload.writeTo(file);
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
                files.add(file);
            }
            showSuccessMessage(object, files);
        }
    }

    public static File newFile(final FileUpload upload) {
        return newFile(upload.getClientFileName());
    }

    public static File newFile(final String fileName) {
        return new File(GuiService.get().getSessionFolder(), FileUploadModel.class.getSimpleName() + "/" + fileName);
    }

    protected void showSuccessMessage(final List<FileUpload> object, final List<File> files) {
        if (object.size() > 0) {
            final StringBuilder statusMessage = new StringBuilder();
            statusMessage.append("<ul>");
            final int maxFilesToBeRendered = 3;
            for (int i = 0; i < maxFilesToBeRendered && i < object.size(); i++) {
                statusMessage.append("<li>");
                statusMessage.append(object.get(i).getClientFileName());
                statusMessage.append("</li>");
            }
            if (object.size() > maxFilesToBeRendered) {
                statusMessage.append("<li>&hellip;</li>");
            }
            statusMessage.append("</ul>");
            GuiService.get()
                    .showStatusMessage(new StatusMessageConfig().withTitle(element.getTitleModel().getObject())
                            .withMessage(statusMessage.toString())
                            .withType(StatusMessageType.success));
            element.getModelElement().getBeanPathElement().setUploadedFiles(files);
        }
    }
}
