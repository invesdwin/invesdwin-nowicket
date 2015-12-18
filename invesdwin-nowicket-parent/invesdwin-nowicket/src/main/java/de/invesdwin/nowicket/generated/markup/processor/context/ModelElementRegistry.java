package de.invesdwin.nowicket.generated.markup.processor.context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class ModelElementRegistry {

    private final Map<String, IModelElement<?>> wicketId_element = new HashMap<String, IModelElement<?>>();

    public void addElement(final IModelElement<?> e) {
        Assertions.assertThat(wicketId_element.put(e.getWicketId(), e)).isNull();
    }

    @SuppressWarnings("unchecked")
    public <T extends IModelElement<?>> T getElement(final String wicketId) {
        return (T) wicketId_element.get(wicketId);
    }

    public Collection<IModelElement<?>> getElements() {
        return wicketId_element.values();
    }

}
