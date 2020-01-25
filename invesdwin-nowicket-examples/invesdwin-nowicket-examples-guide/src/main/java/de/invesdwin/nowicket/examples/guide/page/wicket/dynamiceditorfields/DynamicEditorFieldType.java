package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance.AInheritanceDynamicEditorField;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance.BooleanInheritanceDynamicEditorField;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance.IntegerInheritanceDynamicEditorField;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance.StringInheritanceDynamicEditorField;

@Immutable
public enum DynamicEditorFieldType {
    String {
        @Override
        public AInheritanceDynamicEditorField newInheritanceEditorField() {
            return new StringInheritanceDynamicEditorField();
        }
    },
    Integer {
        @Override
        public AInheritanceDynamicEditorField newInheritanceEditorField() {
            return new IntegerInheritanceDynamicEditorField();
        }
    },
    Boolean {
        @Override
        public AInheritanceDynamicEditorField newInheritanceEditorField() {
            return new BooleanInheritanceDynamicEditorField();
        }
    };

    public abstract AInheritanceDynamicEditorField newInheritanceEditorField();
}
