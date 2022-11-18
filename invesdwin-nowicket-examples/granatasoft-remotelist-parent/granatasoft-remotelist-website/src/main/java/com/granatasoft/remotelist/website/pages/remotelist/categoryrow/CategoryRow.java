package com.granatasoft.remotelist.website.pages.remotelist.categoryrow;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.concurrent.NotThreadSafe;
import jakarta.inject.Inject;

import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.IResource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.ApplicationInstanceDao;
import com.granatasoft.remotelist.persistence.Category;
import com.granatasoft.remotelist.website.pages.remotelist.applicationinstancerow.ApplicationInstanceRow;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class CategoryRow extends AValueObject implements InitializingBean {

    private final Category category;
    private List<ApplicationInstanceRow> applicationInstances;
    @Inject
    private transient ApplicationInstanceDao applicationInstanceRepository;

    public CategoryRow(final Category c) {
        this.category = c;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.applicationInstances = applicationInstanceRepository.findByCategory(category)
                .stream()
                .map(a -> new ApplicationInstanceRow(a))
                .sorted()
                .collect(Collectors.toList());
    }

    @Hidden
    public String getCategoryName() {
        return this.category.getName();
    }

    public List<ApplicationInstanceRow> getApplicationInstances() {
        return applicationInstances;
    }

    @Hidden
    public IResource getCategoryLogo() {
        if (category.getLogo() != null) {
            return new ByteArrayResource(null, category.getLogo(), category.getLogoFileName());
        }

        return null;
    }

    @Override
    public int compareTo(final Object o) {
        if (o instanceof CategoryRow) {
            final CategoryRow cO = (CategoryRow) o;
            return getCategoryName().compareTo(cO.getCategoryName());
        } else {
            return 1;
        }
    }
}
