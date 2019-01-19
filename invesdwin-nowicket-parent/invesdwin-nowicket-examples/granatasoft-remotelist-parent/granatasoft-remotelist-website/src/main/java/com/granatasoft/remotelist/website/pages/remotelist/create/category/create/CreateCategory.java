package com.granatasoft.remotelist.website.pages.remotelist.create.category.create;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Category;
import com.granatasoft.remotelist.persistence.CategoryDao;

import de.invesdwin.context.integration.img.BufferedImages;
import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class CreateCategory extends AValueObject {

    public static final Dimension LOGO_DIMENSION = new Dimension(32, 32);

    @Inject
    private transient CategoryDao categoryDao;
    @NotNull
    private String name;
    private File logo;
    private Category createdCategory;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void logoUpload(final File logo) throws IOException {
        if (logo != null) {
            BufferedImages.resize(logo, LOGO_DIMENSION);
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
    public Category getCreatedCategory() {
        return createdCategory;
    }

    @ModalCloser
    public void create() {
        createdCategory = new Category();
        createdCategory.setName(name);
        if (logo != null) {
            try {
                createdCategory.setLogo(FileUtils.readFileToByteArray(logo));
                createdCategory.setLogoFileName(logo.getName());
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        categoryDao.save(createdCategory);
    }

    @ModalCloser
    @Forced
    public void close() {}
}
