package de.invesdwin.nowicket.examples.pages.guestbook;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;

import de.invesdwin.nowicket.examples.pages.guestbook.persistence.GuestbookEntryEntity;
import de.invesdwin.nowicket.examples.pages.guestbook.persistence.IGuestbookEntryRepository;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class GuestbookExample extends AValueObject {
    private String message;
    private String email;

    @Inject
    private transient IGuestbookEntryRepository dao;

    @NotBlank
    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getHeader() {
        return "Guestbook Example";
    }

    @NotBlank
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void submit() {
        final GuestbookEntryEntity gbookEntry = new GuestbookEntryEntity();

        gbookEntry.setMessage(message);
        gbookEntry.setEmail(email);

        dao.save(gbookEntry);

        setMessage(null);
        setEmail(null);
    }

    public List<GuestbookEntryEntity> getEntries() {
        return dao.findAllOrderedByDate();
    }

    public String validateEmail(final String newValue) {
        if (newValue != null) {
            return newValue.matches(
                    "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
                            ? null : "is not valid";
        }

        return null;
    }

}
