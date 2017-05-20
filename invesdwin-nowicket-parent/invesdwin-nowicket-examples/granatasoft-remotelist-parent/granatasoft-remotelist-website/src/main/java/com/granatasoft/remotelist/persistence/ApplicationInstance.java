package com.granatasoft.remotelist.persistence;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import de.invesdwin.context.persistence.jpa.api.dao.entity.AEntity;

@NotThreadSafe
@Entity
public class ApplicationInstance extends AEntity {
    private String url;
    @Column(nullable = false)
    private String appVersion;
    @Column(nullable = false)
    private Environment environment;
    @ManyToOne(optional = false)
    private Application application;
    @ManyToOne(optional = false)
    private Category category;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Server> servers;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(final Application application) {
        this.application = application;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category catebory) {
        this.category = catebory;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(final List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return this.getApplication().getName() + "." + this.getEnvironment().name() + "( v." + this.getVersion() + ")";
    }
}
