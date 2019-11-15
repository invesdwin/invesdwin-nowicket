package com.granatasoft.remotelist.website.pages.remotelist;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.CategoryDao;
import com.granatasoft.remotelist.website.pages.remotelist.categoryrow.CategoryRow;
import com.granatasoft.remotelist.website.panels.applicationinstance.create.CreateApplicationInstance;

import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.list.Lists;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class ShowCategories extends AValueObject {

    @Inject
    private transient CategoryDao categoryDao;

    public List<CategoryRow> getCategories() {
        return Lists.newLinkedList(categoryDao.findCategoriesWithApplicationInstances())
                .stream()
                .map(c -> new CategoryRow(c))
                .collect(Collectors.toList());
    }

    public void createApplicationInstance() {
        GuiService.get().showModalPanel(new CreateApplicationInstance());
    }

    public boolean hideCreateApplicationInstance() {
        return !Roles.get().hasRole(Roles.ADMIN);
    }
}
