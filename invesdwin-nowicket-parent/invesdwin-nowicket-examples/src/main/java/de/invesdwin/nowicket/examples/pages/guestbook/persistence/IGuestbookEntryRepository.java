package de.invesdwin.nowicket.examples.pages.guestbook.persistence;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
@ThreadSafe
public interface IGuestbookEntryRepository extends JpaRepository<GuestbookEntryEntity, Long> {

    @Query("select e from GuestbookEntryEntity e order by created")
    List<GuestbookEntryEntity> findAllOrderedByDate();

}
