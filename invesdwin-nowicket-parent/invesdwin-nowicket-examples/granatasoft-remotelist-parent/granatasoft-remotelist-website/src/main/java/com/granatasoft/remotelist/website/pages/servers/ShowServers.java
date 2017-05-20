package com.granatasoft.remotelist.website.pages.servers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.ServerDao;
import com.granatasoft.remotelist.website.pages.servers.serverrow.ServerRow;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.Lists;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class ShowServers extends AValueObject {

    @Inject
    private transient ServerDao serverDao;

    public List<ServerRow> getServers() {

        return Lists.newLinkedList(serverDao.findAll())
                .stream()
                .map(s -> new ServerRow(s))
                .collect(Collectors.toList());
    }
}
