package de.invesdwin.nowicket.examples.guide.pages.wicket.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class FileUpload extends AValueObject {

    private File fileUpload;
    private List<File> multiFileUpload;

    public void fileUpload(final File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getUploadedFileName() {
        if (fileUpload != null) {
            return fileUpload.getName();
        } else {
            return null;
        }
    }

    public File uploadedFileDownload() {
        return fileUpload;
    }

    public String disableUploadedFileDownload() {
        if (fileUpload == null) {
            return "Please upload a file";
        } else {
            return null;
        }
    }

    public void multiFileUpload(final List<File> multiFileUpload) {
        this.multiFileUpload = multiFileUpload;
    }

    public String getMultiUploadedFileNames() {
        final StringBuilder sb = new StringBuilder();
        if (multiFileUpload != null) {
            for (final File f : multiFileUpload) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(f.getName());
            }
        }
        return sb.toString();
    }

    public File multiUploadedFileDownload() throws IOException {
        return OfferDownloadConfig.zipFiles(multiFileUpload, "multiFileUpload");
    }

    public String disableMultiUploadedFileDownload() {
        if (multiFileUpload == null) {
            return "Please upload multi files";
        }
        return null;
    }

    public void upload() {}

    public void uploadAndDownload() throws IOException {
        final List<File> files = new ArrayList<File>();
        if (fileUpload != null) {
            files.add(fileUpload);
        }
        if (multiFileUpload != null) {
            files.addAll(multiFileUpload);
        }
        if (!files.isEmpty()) {
            GuiService.get().offerDownload(new OfferDownloadConfig(files, "uploadAndDownload"));
        }
    }

    public void reset() {
        fileUpload = null;
        multiFileUpload = null;
    }

}
