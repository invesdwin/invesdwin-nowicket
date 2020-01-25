package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import de.invesdwin.context.persistence.jpa.api.dao.ADao;

@Named
@ThreadSafe
public class ServerConnectionDao extends ADao<ServerConnection> {

}
