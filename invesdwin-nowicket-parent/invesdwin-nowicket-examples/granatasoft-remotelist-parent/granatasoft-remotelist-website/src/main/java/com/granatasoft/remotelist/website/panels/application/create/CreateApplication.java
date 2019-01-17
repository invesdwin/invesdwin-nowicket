package com.granatasoft.remotelist.website.panels.application.create;

import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.apache.wicket.util.file.Files;
import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Application;
import com.granatasoft.remotelist.persistence.ApplicationDao;
import com.granatasoft.remotelist.website.pages.remotelist.create.category.create.CreateCategory;

import de.invesdwin.context.integration.img.BufferedImages;
import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class CreateApplication extends AValueObject {

    @Inject
    private transient ApplicationDao applicationRepository;
    @NotNull
    private String name;
    private File logo;
    private Application createdApplication;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void logoUpload(final File logo) throws IOException {
        if (logo != null) {
            BufferedImages.resize(logo, CreateCategory.LOGO_DIMENSION);
        }
        this.logo = logo;
    }

    public String validateLogoUpload(final File newValue) {
        if (newValue != null && !BufferedImages.isSupportedFormatName(newValue)) {
            return "needs to be one of these image types: " + BufferedImages.SUPPORTED_FORMAT_NAMES;
        }
        return null;
    }

    @Hidden
    public Application getCreatedApplication() {
        return createdApplication;
    }

    @ModalCloser
    public void create() throws IOException {

        if (logo != null) {
            createdApplication = new Application(this.name, Files.readBytes(this.logo), logo.getName());
        } else {
            createdApplication = new Application(this.name);
        }

        this.applicationRepository.save(createdApplication);
    }

    @Forced
    @ModalCloser
    public void close() {}
}
