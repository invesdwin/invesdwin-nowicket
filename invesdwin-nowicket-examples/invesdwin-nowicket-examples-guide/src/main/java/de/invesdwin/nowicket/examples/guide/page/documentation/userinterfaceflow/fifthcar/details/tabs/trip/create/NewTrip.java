package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.create;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@NotThreadSafe
@GeneratedMarkup
public class NewTrip extends AValueObject {

    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    @Min(1)
    private Integer distanceInKM;

    public String getTo() {
        return to;
    }

    public Integer getDistanceInKM() {
        return distanceInKM;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public void setDistanceInKM(final Integer distanceInKM) {
        this.distanceInKM = distanceInKM;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    @ModalCloser
    public void ok() {}

    @Forced
    @ModalCloser
    public void cancel() {}
}
