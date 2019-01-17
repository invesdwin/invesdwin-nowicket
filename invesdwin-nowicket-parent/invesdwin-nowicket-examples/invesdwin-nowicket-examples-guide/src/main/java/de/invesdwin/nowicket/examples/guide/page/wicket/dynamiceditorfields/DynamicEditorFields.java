package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.DynamicEditorFieldImplementation;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.simple.SimpleDynamicEditorField;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.error.UnknownArgumentException;

@NotThreadSafe
@GeneratedMarkup
public class DynamicEditorFields extends AValueObject {

    @NotNull
    private DynamicEditorFieldType chooseType;
    @NotNull
    private DynamicEditorFieldImplementation chooseImplementation;
    private final List<IDynamicEditorField> editorFields = new ArrayList<>();

    public DynamicEditorFieldType getChooseType() {
        return chooseType;
    }

    public void setChooseType(final DynamicEditorFieldType chooseType) {
        this.chooseType = chooseType;
    }

    public DynamicEditorFieldImplementation getChooseImplementation() {
        return chooseImplementation;
    }

    public void setChooseImplementation(final DynamicEditorFieldImplementation chooseImplementation) {
        this.chooseImplementation = chooseImplementation;
    }

    public List<IDynamicEditorField> getEditorFields() {
        return editorFields;
    }

    @Forced
    public void addField() {
        final IDynamicEditorField field;
        switch (chooseImplementation) {
        case Simple:
            field = new SimpleDynamicEditorField(chooseType);
            break;
        case Inheritance:
            field = chooseType.newInheritanceEditorField();
            break;
        default:
            throw UnknownArgumentException.newInstance(DynamicEditorFieldImplementation.class, chooseImplementation);
        }
        editorFields.add(field);
    }

    @Forced
    public void resetFields() {
        editorFields.clear();
    }

    public void summarizeUnforced() throws Exception {
        summarize();
    }

    @Forced
    public void summarize() throws Exception {
        if (editorFields.isEmpty()) {
            throw new Exception("Please add some fields first");
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < editorFields.size(); i++) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            final IDynamicEditorField field = editorFields.get(i);
            sb.append(i + 1).append(": ");
            sb.append(field.getImplementation()).append(": ");
            sb.append(field.getType()).append(": ");
            sb.append(field.getValue());
        }
        GuiService.get().showStatusMessage("Dynamic Editor Fields Summary", sb.toString());
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof DynamicEditorFields;
    }

    @Override
    public int hashCode() {
        return DynamicEditorFields.class.hashCode();
    }

}
