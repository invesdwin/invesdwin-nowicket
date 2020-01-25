package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields;

import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.DynamicEditorFieldImplementation;

public interface IDynamicEditorField {

    Object getValue();

    DynamicEditorFieldType getType();

    DynamicEditorFieldImplementation getImplementation();

}
