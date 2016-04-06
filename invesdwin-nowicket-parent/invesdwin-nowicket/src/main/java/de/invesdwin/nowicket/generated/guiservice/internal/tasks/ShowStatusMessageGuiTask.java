package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;

import de.invesdwin.nowicket.component.pnotify.PNotifyBehavior;
import de.invesdwin.nowicket.component.pnotify.PNotifyType;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class ShowStatusMessageGuiTask implements IGuiTask {

    private final StatusMessageConfig config;

    public ShowStatusMessageGuiTask(final StatusMessageConfig config) {
        this.config = config;
    }

    @Override
    public void process(final Component component) {
        final Component root = Components.findForm(component).getRootForm();
        final PNotifyBehavior behavior = new PNotifyBehavior();
        if (config.getTitle() != null) {
            behavior.withTitle(config.getTitle());
        }
        if (config.getMessage() != null) {
            behavior.withText(config.getMessage());
        }
        if (config.getDuration() != null) {
            behavior.withDuration(config.getDuration());
        }
        if (config.getIconCssClass() != null) {
            behavior.withIconCssClass(config.getIconCssClass());
        }
        if (config.getType() != null) {
            behavior.withType(PNotifyType.valueOf(config.getType().name()));
        }
        root.add(behavior);
    }
}
