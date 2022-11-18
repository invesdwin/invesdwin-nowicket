package com.granatasoft.remotelist.website.panels.application;

import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import com.granatasoft.remotelist.persistence.Application;
import com.granatasoft.remotelist.persistence.ApplicationDao;
import com.granatasoft.remotelist.website.panels.application.create.CreateApplication;

import de.invesdwin.context.beans.validator.BeanValidator;
import de.invesdwin.context.test.ATest;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.FileUploadModel;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Files;

@NotThreadSafe
public class CreateApplicationTest extends ATest {

    @Inject
    private ApplicationDao applicationDao;

    @Test
    public void assertThatApplicationIsCreated() throws IOException {
        final CreateApplication createApplication = new CreateApplication();
        createApplication.setName("Test Application");
        final File srcFile = new File("src/main/java/com/granatasoft/remotelist/website/pages/logo.png");
        final File logoFile = FileUploadModel.newFile(srcFile.getName());
        Files.copyFile(srcFile, logoFile);
        createApplication.logoUpload(logoFile);
        Assertions.assertThat(BeanValidator.getInstance().validate(createApplication)).isNull();
        Assertions.assertThat(applicationDao.count()).isEqualTo(0);
        createApplication.create();
        Assertions.assertThat(applicationDao.count()).isEqualTo(1);
        final Application createdApp = applicationDao.findOneFast();
        Assertions.assertThat(createdApp.getName()).isEqualTo(createApplication.getName());
        Assertions.assertThat(createdApp.getLogo()).isNotNull();
        Assertions.assertThat(createdApp.getLogoFileName()).isNotNull();
    }
}
