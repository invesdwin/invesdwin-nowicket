package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.ThreadSafe;

import de.invesdwin.context.persistence.jpa.api.dao.ADao;
import jakarta.inject.Named;

@Named
@ThreadSafe
public class ServerConnectionDao extends ADao<ServerConnection> {

}
