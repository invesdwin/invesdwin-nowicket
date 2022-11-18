package com.granatasoft.remotelist.website.panels.applicationinstance.update;

import java.io.IOException;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Application;
import com.granatasoft.remotelist.persistence.ApplicationDao;
import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.ApplicationInstanceDao;
import com.granatasoft.remotelist.persistence.Category;
import com.granatasoft.remotelist.persistence.CategoryDao;
import com.granatasoft.remotelist.persistence.Environment;
import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.persistence.ServerDao;
import com.granatasoft.remotelist.website.pages.remotelist.create.category.create.CreateCategory;
import com.granatasoft.remotelist.website.pages.remotelist.create.server.CreateServer;
import com.granatasoft.remotelist.website.panels.application.create.CreateApplication;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.lang.uri.URIs;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class UpdateApplicationInstance extends AValueObject {

    private final ApplicationInstance applicationInstance;
    @Inject
    private transient ApplicationDao applicationDao;
    @Inject
    private transient CategoryDao categoryDao;
    @Inject
    private transient ServerDao serverDao;
    @Inject
    private transient ApplicationInstanceDao applicationInstanceDao;

    public UpdateApplicationInstance(final ApplicationInstance applicationInstance) {
        this.applicationInstance = applicationInstance;
    }

    @NotNull
    public String getUrl() {
        return applicationInstance.getUrl();
    }

    public void setUrl(final String url) {
        applicationInstance.setUrl(url);
    }

    public String validateUrl(final String newValue) {
        if (Strings.isBlank(newValue)) {
            return null;
        }

        try {
            URIs.asUrl(newValue);
        } catch (final Throwable t) {
            return "is not valid";
        }

        return null;
    }

    @NotNull
    public String getVersion() {
        return applicationInstance.getAppVersion();
    }

    public void setVersion(final String version) {
        applicationInstance.setAppVersion(version);
    }

    @NotNull
    public Environment getEnvironment() {
        return applicationInstance.getEnvironment();
    }

    public void setEnvironment(final Environment environment) {
        applicationInstance.setEnvironment(environment);
    }

    @NotNull
    public Application getApplication() {
        return applicationInstance.getApplication();
    }

    public void setApplication(final Application application) {
        applicationInstance.setApplication(application);
    }

    public Iterable<Application> getApplicationChoice() {
        return applicationDao.findAll();
    }

    @NotNull
    public Category getCategory() {
        return applicationInstance.getCategory();
    }

    public void setCategory(final Category category) {
        applicationInstance.setCategory(category);
    }

    public Iterable<Category> getCategoryChoice() {
        return categoryDao.findAll();
    }

    @NotNull
    public List<Server> getServer() {
        return applicationInstance.getServers();
    }

    public void setServer(final List<Server> server) {
        applicationInstance.setServers(server);
    }

    public Iterable<Server> getServerChoice() {
        return serverDao.findAll();
    }

    @ModalCloser
    public void update() {
        applicationInstanceDao.save(applicationInstance);
    }

    @ModalCloser
    @Forced
    public void cancel() {}

    @Forced
    public void createCategory() {
        GuiService.get().showModalPanel(new CreateCategory() {
            @Override
            @ModalCloser
            public void create() {
                super.create();
                setCategory(getCreatedCategory());
            }
        });
    }

    @Forced
    public void createApplication() {
        GuiService.get().showModalPanel(new CreateApplication() {
            @Override
            @ModalCloser
            public void create() throws IOException {
                super.create();
                setApplication(getCreatedApplication());
            }
        });
    }

    @Forced
    public void createServer() {
        GuiService.get().showModalPanel(new CreateServer() {
            @Override
            @ModalCloser
            public void create() {
                super.create();
                getServer().add(getCreatedServer());
            }
        });
    }
}
