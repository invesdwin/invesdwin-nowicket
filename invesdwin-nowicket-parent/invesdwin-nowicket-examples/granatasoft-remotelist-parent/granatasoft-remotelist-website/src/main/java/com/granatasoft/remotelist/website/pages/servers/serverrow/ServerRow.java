package com.granatasoft.remotelist.website.pages.servers.serverrow;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.ApplicationInstanceDao;
import com.granatasoft.remotelist.website.pages.servers.instancerow.ApplicationInstanceRow;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@Configurable
@GeneratedMarkup
public class ServerRow extends AValueObject implements InitializingBean {

    private final com.granatasoft.remotelist.persistence.Server server;
    @Inject
    private transient ApplicationInstanceDao applicationInstanceDao;
    private List<ApplicationInstance> applicationInstances;

    public ServerRow(final com.granatasoft.remotelist.persistence.Server server) {
        this.server = server;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationInstances = applicationInstanceDao.findByServer(server);
    }

    @Hidden
    public com.granatasoft.remotelist.persistence.Server getServer() {
        return server;
    }

    @Hidden
    public String getName() {
        return server.getName();
    }

    @Hidden
    public void connect() {}

    public List<ApplicationInstanceRow> getApplications() {
        return applicationInstances.stream().map(a -> new ApplicationInstanceRow(a)).collect(Collectors.toList());
    }
}
