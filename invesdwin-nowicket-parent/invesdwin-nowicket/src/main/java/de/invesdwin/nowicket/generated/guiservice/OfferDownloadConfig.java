package de.invesdwin.nowicket.generated.guiservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

import de.invesdwin.util.lang.Files;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.lang.string.UniqueNameGenerator;

@NotThreadSafe
public class OfferDownloadConfig implements Serializable {

    private final IResourceStream resourceStream;
    private final String fileName;

    public OfferDownloadConfig(final IResourceStream resourceStream, final String fileName) {
        this.resourceStream = resourceStream;
        this.fileName = fileName;
    }

    public OfferDownloadConfig(final File file) {
        this(new FileResourceStream(file), file.getName());
    }

    /**
     * If it is more than one file, it will package it into a zip. If it is just one file, it will directly offer that
     * one.
     */
    public OfferDownloadConfig(final List<File> files, final String packagedFileName) throws IOException {
        if (files.size() > 1) {
            final File tempFile = zipFiles(files, packagedFileName);
            this.fileName = tempFile.getName();
            this.resourceStream = new FileResourceStream(tempFile);
        } else if (files.size() == 1) {
            final File file = files.get(0);
            this.fileName = file.getName();
            this.resourceStream = new FileResourceStream(file);
        } else {
            throw new IllegalArgumentException("Please provide something to download");
        }
    }

    public static File zipFiles(final List<File> files, final String packagedFileName)
            throws IOException, FileNotFoundException {
        String modifiedFileName = packagedFileName;
        if (!Strings.endsWithIgnoreCase(modifiedFileName, ".zip")) {
            modifiedFileName += ".zip";
        }
        final File tempFile = new File(GuiService.get().getSessionFolder(),
                OfferDownloadConfig.class.getSimpleName() + "/" + modifiedFileName);
        Files.forceMkdir(tempFile.getParentFile());
        final ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(tempFile));
        final UniqueNameGenerator uniqueNames = new UniqueNameGenerator();
        for (final File file : files) {
            final ZipEntry entry = new ZipEntry(uniqueNames.get(file.getName()));
            zip.putNextEntry(entry);
            IOUtils.copy(new FileInputStream(file), zip);
            zip.closeEntry();
        }
        zip.close();
        return tempFile;
    }

    public IResourceStream getResourceStream() {
        return resourceStream;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

}
