package com.granatasoft.remotelist.website.panels.application.update;

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
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class UpdateApplication extends AValueObject {

    private File logo;
    private Application updateddApplication;
    @Inject
    private transient ApplicationDao applicationRepository;

    public UpdateApplication(final Application updateddApplication) {
        this.updateddApplication = updateddApplication;
    }

    @NotNull
    public String getName() {
        return updateddApplication.getName();
    }

    public void setName(final String name) {
        updateddApplication.setName(name);
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
    public Application getUpdatedApplication() {
        return updateddApplication;
    }

    @ModalCloser
    public void save() throws IOException {
        if (logo != null) {
            updateddApplication.setLogo(Files.readBytes(logo));
            updateddApplication.setLogoFileName(logo.getName());
        }

        updateddApplication = applicationRepository.save(updateddApplication);
    }

    @Forced
    @ModalCloser
    public void close() {}
}
