package com.granatasoft.remotelist.website.panels.servers.edit;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.persistence.ServerDao;

import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@Configurable
@NotThreadSafe
@GeneratedMarkup
public class EditServers extends AValueObject {

    @Inject
    private transient ServerDao serverDao;

    public List<Server> getServers() {
        return serverDao.findAll();
    }

    @ModalCloser
    public void save() {}

    @ModalCloser
    public void close() {}
}
