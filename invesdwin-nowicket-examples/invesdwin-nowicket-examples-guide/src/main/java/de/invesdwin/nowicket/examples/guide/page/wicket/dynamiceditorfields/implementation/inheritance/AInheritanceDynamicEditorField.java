package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.IDynamicEditorField;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.DynamicEditorFieldImplementation;

@NotThreadSafe
public abstract class AInheritanceDynamicEditorField implements IDynamicEditorField, Serializable {

    @Hidden(skip = true)
    @Override
    public DynamicEditorFieldImplementation getImplementation() {
        return DynamicEditorFieldImplementation.Inheritance;
    }

}
