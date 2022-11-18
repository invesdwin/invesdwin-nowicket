package com.granatasoft.remotelist.run;

import javax.annotation.concurrent.NotThreadSafe;
import jakarta.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Application;
import com.granatasoft.remotelist.persistence.ApplicationDao;
import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.ApplicationInstanceDao;
import com.granatasoft.remotelist.persistence.Category;
import com.granatasoft.remotelist.persistence.CategoryDao;
import com.granatasoft.remotelist.persistence.Environment;
import com.granatasoft.remotelist.persistence.Protocol;
import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.persistence.ServerConnection;
import com.granatasoft.remotelist.persistence.ServerDao;

import de.invesdwin.context.persistence.jpa.test.APersistenceTestPreparer;
import de.invesdwin.util.collections.Arrays;

@NotThreadSafe
@Configurable
public class SampleDataPreparer extends APersistenceTestPreparer {

    @Inject
    private CategoryDao categoryDao;
    @Inject
    private ApplicationDao applicationDao;
    @Inject
    private ServerDao serverDao;
    @Inject
    private ApplicationInstanceDao applicationInstanceDato;

    @Override
    public void prepare() {
        for (int i = 1; i < 10; i++) {

            // Create some test categories.
            final Category category = new Category();
            category.setName("Category " + i);
            categoryDao.save(category);

            // Create some test applications.
            final Application app = new Application();
            app.setName("Application " + i);
            applicationDao.save(app);

            // Create some test servers.
            final Server srv = new Server();
            srv.setName("Server " + i);
            srv.setIpv4("192.168.1.10" + i);
            srv.setConnections(Arrays.asList(new ServerConnection(Protocol.RDP, 8080)));
            serverDao.save(srv);
        }

        for (int i = 0; i < 4; i++) {
            final ApplicationInstance appInstance = new ApplicationInstance();
            appInstance.setAppVersion("1.0");
            appInstance.setEnvironment(Environment.Prod);
            appInstance.setUrl("http://example.com");
            appInstance.setApplication(applicationDao.findOneRandom());
            appInstance.setCategory(categoryDao.findOneRandom());
            appInstance.setServers(Arrays.asList(serverDao.findOneRandom()));
            applicationInstanceDato.save(appInstance);
        }

    }
}
