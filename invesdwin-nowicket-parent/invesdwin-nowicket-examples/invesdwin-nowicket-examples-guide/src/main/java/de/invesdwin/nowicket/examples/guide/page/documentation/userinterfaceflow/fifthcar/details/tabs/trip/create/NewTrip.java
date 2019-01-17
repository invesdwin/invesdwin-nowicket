package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.create;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

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
