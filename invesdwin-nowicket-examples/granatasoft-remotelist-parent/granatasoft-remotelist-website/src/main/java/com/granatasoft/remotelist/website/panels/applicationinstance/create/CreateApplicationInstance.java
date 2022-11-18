package com.granatasoft.remotelist.website.panels.applicationinstance.create;

import java.io.IOException;
import java.util.ArrayList;
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
import com.granatasoft.remotelist.website.pages.remotelist.create.category.update.UpdateCategory;
import com.granatasoft.remotelist.website.pages.remotelist.create.server.CreateServer;
import com.granatasoft.remotelist.website.panels.application.create.CreateApplication;
import com.granatasoft.remotelist.website.panels.application.update.UpdateApplication;
import com.granatasoft.remotelist.website.panels.servers.edit.EditServers;

import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.lang.uri.URIs;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class CreateApplicationInstance extends AValueObject {

    @Inject
    private transient ApplicationDao applicationDao;
    @Inject
    private transient CategoryDao categoryDao;
    @Inject
    private transient ServerDao serverDao;
    @Inject
    private transient ApplicationInstanceDao applicationInstanceDao;

    private String url;
    @NotNull
    private String version;
    @NotNull
    private Environment environment;
    @Eager
    @Forced
    @NotNull
    private Application application;
    @Eager
    @Forced
    @NotNull
    private Category category;
    @NotEmpty
    private List<Server> server = new ArrayList<Server>();

    public CreateApplicationInstance() {}

    public CreateApplicationInstance(final ApplicationInstance applicationInstance) {
        this.url = applicationInstance.getUrl();
        this.version = applicationInstance.getAppVersion();
        this.environment = applicationInstance.getEnvironment();
        this.application = applicationInstance.getApplication();
        this.category = applicationInstance.getCategory();
        this.server = applicationInstance.getServers();

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    //TODO Validate wird nicht immer aufgerufen bitte fixen!
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

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(final Application application) {
        this.application = application;
    }

    public Iterable<Application> getApplicationChoice() {
        return applicationDao.findAll();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Iterable<Category> getCategoryChoice() {
        return categoryDao.findAll();
    }

    public List<Server> getServer() {
        return server;
    }

    public void setServer(final List<Server> server) {
        this.server = server;
    }

    public Iterable<Server> getServerChoice() {
        return serverDao.findAll();
    }

    // CRUD Category
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
    public void updateCategory() {
        GuiService.get().showModalPanel(new UpdateCategory(category) {
            @Override
            @ModalCloser
            public void save() {
                super.save();
                setCategory(getCreatedCategory());
            }
        });
    }

    public String disableUpdateCategory() {
        if (category == null) {
            return "Please select a category.";
        }
        return null;
    }

    // CRUD Application
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
    public void updateApplication() {
        GuiService.get().showModalPanel(new UpdateApplication(application) {
            @Override
            @ModalCloser
            public void save() throws IOException {
                super.save();
                setApplication(getUpdatedApplication());
            }
        });
    }

    public String disableUpdateApplication() {
        if (application == null) {
            return "Please select an application.";
        }
        return null;
    }

    // CRUD Server
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

    @Forced
    public void updateServers() {
        GuiService.get().showModalPanel(new EditServers() {
            @Override
            @ModalCloser
            public void save() {

            }
        });
    }

    @ModalCloser
    public void create() {
        final ApplicationInstance applicationInstance = new ApplicationInstance();
        applicationInstance.setUrl(url);
        applicationInstance.setAppVersion(version);
        applicationInstance.setEnvironment(environment);
        applicationInstance.setCategory(category);
        applicationInstance.setApplication(application);
        applicationInstance.setServers(server);
        applicationInstanceDao.save(applicationInstance);
    }

    @ModalCloser
    @Forced
    public void cancel() {}
}
