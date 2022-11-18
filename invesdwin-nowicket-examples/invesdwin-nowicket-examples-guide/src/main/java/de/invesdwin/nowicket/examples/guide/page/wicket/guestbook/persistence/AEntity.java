package de.invesdwin.nowicket.examples.guide.page.wicket.guestbook.persistence;

import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.time.date.FDate;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

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
