package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;

@NotThreadSafe
public class OnlyOnceTruePerRequestCycleModel implements IModel<Boolean> {

    private transient RequestCycle singleSelectionPrevRequestCycle;

    @Override
    public Boolean getObject() {
        final RequestCycle newRequestCycle = RequestCycle.get();
        if (singleSelectionPrevRequestCycle == null || singleSelectionPrevRequestCycle != newRequestCycle) {
            singleSelectionPrevRequestCycle = newRequestCycle;
            return true;
        } else {
            return false;
        }
    }

}
