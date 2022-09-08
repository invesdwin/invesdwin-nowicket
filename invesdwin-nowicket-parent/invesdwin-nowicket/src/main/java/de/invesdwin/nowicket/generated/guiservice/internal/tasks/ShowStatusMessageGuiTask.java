package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;

import de.invesdwin.nowicket.component.pnotify.PNotifyBehavior;
import de.invesdwin.nowicket.component.pnotify.PNotifyType;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.collections.Arrays;

@NotThreadSafe
public class ShowStatusMessageGuiTask implements IGuiTask {

    private final StatusMessageConfig config;

    public ShowStatusMessageGuiTask(final StatusMessageConfig config) {
        this.config = config;
    }

    @Override
    public Collection<? extends Component> process(final Component component) {
        final Form<?> form = Components.findForm(component);
        final Component root = form.getRootForm();
        final PNotifyBehavior behavior = new PNotifyBehavior();
        if (config.getTitle() != null) {
            behavior.setTitle(config.getTitle());
        }
        if (config.getMessage() != null) {
            behavior.setText(config.getMessage());
        }
        if (config.getDuration() != null) {
            behavior.setDuration(config.getDuration());
        }
        if (config.getIconCssClass() != null) {
            behavior.setIconCssClass(config.getIconCssClass());
        }
        if (config.getType() != null) {
            behavior.setType(PNotifyType.valueOf(config.getType().name()));
        }
        root.add(behavior);
        return Arrays.asList(root);
    }
}
