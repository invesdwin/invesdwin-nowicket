package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.persistence.jpa.api.dao.entity.AEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

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
