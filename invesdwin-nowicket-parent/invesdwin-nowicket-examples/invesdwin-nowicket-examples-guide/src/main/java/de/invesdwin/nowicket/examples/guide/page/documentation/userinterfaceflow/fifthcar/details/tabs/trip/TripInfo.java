package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.create.NewTrip;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.binding.annotation.ModalOpener;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class TripInfo extends AValueObject {

    private Collection<TripRow> tripBook;

    public TripInfo() {
        this.tripBook = new ArrayList<TripRow>();
    }

    public Collection<TripRow> getTripBook() {
        return tripBook;
    }

    public void setTripBook(final Collection<TripRow> tripBook) {
        this.tripBook = tripBook;
    }

    public String validateTripBook(final Collection<TripRow> newValue) {
        if (newValue == null || newValue.isEmpty()) {
            return "is empty, please add a Trip!";
        } else {
            return null;
        }
    }

    @ModalOpener
    @Forced
    public NewTrip newTrip() {
        return new NewTrip() {
            @Override
            @ModalCloser
            public void ok() {
                super.ok();
                tripBook.add(new TripRow(this));
            }
        };
    }

    @Hidden
    public Integer getDistanceInKMSum() {
        int sum = 0;
        for (final TripRow trip : tripBook) {
            sum += trip.getDistanceInKM();
        }
        return sum;
    }

}
