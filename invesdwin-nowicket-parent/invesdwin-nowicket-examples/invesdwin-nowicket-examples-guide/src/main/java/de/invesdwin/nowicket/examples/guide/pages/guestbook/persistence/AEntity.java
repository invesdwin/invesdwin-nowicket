package de.invesdwin.nowicket.examples.guide.pages.guestbook.persistence;

import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.time.fdate.FDate;

@NotThreadSafe
@MappedSuperclass
public abstract class AEntity extends AValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date created;
    @Column(nullable = false)
    private Date updated;
    @Version
    @Column(nullable = false)
    private Long version;

    /**
     * This is the version number for Optimistic Locking. It may be null when the Entity has not been written to the DB.
     */
    @Hidden(skip = true)
    public Long getVersion() {
        return version;
    }

    /**
     * Returns the time when the Entity was created in the DB.
     */
    @Hidden(skip = true)
    public FDate getCreated() {
        return FDate.valueOf(created);
    }

    /**
     * Returns the time when the Entity was last updated in the DB.
     */
    @Hidden(skip = true)
    public FDate getUpdated() {
        return FDate.valueOf(updated);
    }

    /**************************** Entity Lifecycle Hooks **********************/

    @PrePersist
    protected void prePersist() {
        //CHECKSTYLE:OFF
        final Date date = new Date();
        //CHECKSTYLE:ON
        this.created = date;
        this.updated = date;
    }

    @PreUpdate
    protected void preUpdate() {
        //CHECKSTYLE:OFF
        this.updated = new Date();
        //CHECKSTYLE:ON
    }

    @Hidden(skip = true)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        Assertions.assertThat(id).as("Id cannot be reset.").isNotNull();
        this.id = id;
    }

}
