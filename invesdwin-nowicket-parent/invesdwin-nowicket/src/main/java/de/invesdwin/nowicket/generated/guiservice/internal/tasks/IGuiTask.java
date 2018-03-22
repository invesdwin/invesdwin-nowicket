package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.io.Serializable;
import java.util.Collection;

import org.apache.wicket.Component;

public interface IGuiTask extends Serializable {

    Collection<? extends Component> process(Component component);

}
