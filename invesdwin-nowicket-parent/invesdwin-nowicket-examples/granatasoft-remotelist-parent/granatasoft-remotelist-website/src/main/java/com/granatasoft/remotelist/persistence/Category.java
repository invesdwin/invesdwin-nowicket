package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import de.invesdwin.context.persistence.jpa.api.dao.entity.AEntity;

@NotThreadSafe
@Entity
public class Category extends AEntity {

    @Column(nullable = false)
    private String name;
    @Lob
    private byte[] logo;
    private String logoFileName;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(final byte[] logo) {
        this.logo = logo;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(final String logoFileName) {
        this.logoFileName = logoFileName;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
