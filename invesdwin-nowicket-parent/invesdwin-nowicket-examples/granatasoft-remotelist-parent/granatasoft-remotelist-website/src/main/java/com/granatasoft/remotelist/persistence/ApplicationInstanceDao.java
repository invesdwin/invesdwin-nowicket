package com.granatasoft.remotelist.persistence;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import com.querydsl.jpa.impl.JPAQuery;

import de.invesdwin.context.persistence.jpa.api.dao.ADao;

@ThreadSafe
@Named
public class ApplicationInstanceDao extends ADao<ApplicationInstance> {

    public List<ApplicationInstance> findByCategory(final Category category) {
        final ApplicationInstance exampleInstance = new ApplicationInstance();
        exampleInstance.setCategory(category);
        return findAll(exampleInstance);
    }

    public List<ApplicationInstance> findByServer(final Server server) {
        final QApplicationInstance qApplicationInstance = QApplicationInstance.applicationInstance;
        return new JPAQuery<ApplicationInstance>(getEntityManager()).from(qApplicationInstance)
                .where(qApplicationInstance.servers.contains(server))
                .fetch();
    }
}
