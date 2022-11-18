package com.granatasoft.remotelist.website.pages.remotelist.create.category.update;

import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Category;
import com.granatasoft.remotelist.persistence.CategoryDao;
import com.granatasoft.remotelist.website.pages.remotelist.create.category.create.CreateCategory;

import de.invesdwin.context.integration.img.BufferedImages;
import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Files;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class UpdateCategory extends AValueObject {

    @Inject
    private transient CategoryDao categoryDao;

    private Category category;
    private File logo;

    public UpdateCategory(final Category category) {
        this.category = category;
    }

    @NotNull
    public String getName() {
        return category.getName();
    }

    public void setName(final String name) {
        category.setName(name);
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
    public Category getCreatedCategory() {
        return category;
    }

    @ModalCloser
    public void save() {
        if (logo != null) {
            try {
                category.setLogo(Files.readFileToByteArray(logo));
                category.setLogoFileName(logo.getName());
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        category = categoryDao.save(category);
    }

    @ModalCloser
    @Forced
    public void close() {}

}
