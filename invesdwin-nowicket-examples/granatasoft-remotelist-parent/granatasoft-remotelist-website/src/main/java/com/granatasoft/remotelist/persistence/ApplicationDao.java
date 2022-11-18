package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.ThreadSafe;

import de.invesdwin.context.persistence.jpa.api.dao.ADao;
import jakarta.inject.Named;

@ThreadSafe
@Named
public class ApplicationDao extends ADao<Application> {

}
