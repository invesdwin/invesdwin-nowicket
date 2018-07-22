package de.invesdwin.nowicket.generated.markup.processor.visitor;

import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.CheckBoxInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.DateInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.FieldSetOpenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.HiddenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NumberInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.RootModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SelectModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SubmitButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.UploadButtonModelElement;

public interface IModelVisitor {

    ModelClassContext getContext();

    void visitRoot(RootModelElement e);

    void visitFieldSetOpen(FieldSetOpenModelElement e);

    void visitTable(TableModelElement e);

    void visitTabbed(TabbedModelElement e);

    void visitSubmitButton(SubmitButtonModelElement e);

    void visitAnchor(AnchorModelElement e);

    void visitUploadButton(UploadButtonModelElement e);

    void visitCheckBoxInput(CheckBoxInputModelElement e);

    void visitSelect(SelectModelElement e);

    void visitTextInput(TextInputModelElement e);

    void visitDateInput(DateInputModelElement e);

    void visitNumberInput(NumberInputModelElement e);

    void visitHidden(HiddenModelElement e);

    void visitFieldSetClose();

    void finish();

}
