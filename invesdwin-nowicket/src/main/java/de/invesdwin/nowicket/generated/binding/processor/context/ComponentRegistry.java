package de.invesdwin.nowicket.generated.binding.processor.context;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;

import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class ComponentRegistry implements Serializable {

    private final Map<String, Component> wicket_component = new HashMap<String, Component>();

    public void addComponent(final Component e) {
        Assertions.assertThat(wicket_component.put(e.getId(), e)).isNull();
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(final String wicketId) {
        return (T) wicket_component.get(wicketId);
    }

    public Collection<Component> getComponents() {
        return wicket_component.values();
    }

}
