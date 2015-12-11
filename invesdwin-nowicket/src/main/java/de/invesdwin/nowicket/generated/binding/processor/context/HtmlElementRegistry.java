package de.invesdwin.nowicket.generated.binding.processor.context;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class HtmlElementRegistry implements Serializable {

    private final Map<String, IHtmlElement<?, ?>> wicketId_element = new HashMap<String, IHtmlElement<?, ?>>();

    public void addElement(final IHtmlElement<?, ?> e) {
        Assertions.assertThat(wicketId_element.put(e.getWicketId(), e)).isNull();
    }

    @SuppressWarnings("unchecked")
    public <T extends IHtmlElement<?, ?>> T getElement(final String wicketId) {
        return (T) wicketId_element.get(wicketId);
    }

    public Collection<IHtmlElement<?, ?>> getElements() {
        return wicketId_element.values();
    }

}
