package de.invesdwin.nowicket.util;

import javax.annotation.concurrent.Immutable;

import org.apache.ecs.ConcreteElement;
import org.apache.wicket.markup.ComponentTag;

import de.invesdwin.util.lang.string.Strings;

@Immutable
public final class Attributes {

    private Attributes() {}

    public static void merge(final ConcreteElement element, final String attribute, final String value) {
        String newValue = element.getAttribute(attribute);
        if (Strings.isBlank(newValue)) {
            newValue = value;
        } else {
            newValue += " " + value;
        }
        element.addAttribute(attribute, newValue);
    }

    public static void merge(final ComponentTag tag, final String attribute, final String value) {
        String newValue = tag.getAttribute(attribute);
        if (Strings.isBlank(newValue)) {
            newValue = value;
        } else {
            newValue += " " + value;
        }
        tag.put(attribute, newValue);
    }

}
