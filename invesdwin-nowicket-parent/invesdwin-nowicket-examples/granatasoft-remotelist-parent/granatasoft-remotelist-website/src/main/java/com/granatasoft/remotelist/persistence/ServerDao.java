package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import de.invesdwin.context.persistence.jpa.api.dao.ADao;

@ThreadSafe
@Named
public class ServerDao extends ADao<Server> {

}
