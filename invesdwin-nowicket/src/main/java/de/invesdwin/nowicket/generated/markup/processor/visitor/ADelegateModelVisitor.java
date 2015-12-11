package de.invesdwin.nowicket.generated.markup.processor.visitor;

import javax.annotation.concurrent.NotThreadSafe;

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

@NotThreadSafe
public abstract class ADelegateModelVisitor extends AModelVisitor {

    private IModelVisitor delegate;

    public ADelegateModelVisitor(final ModelClassContext context) {
        super(context);
    }

    protected abstract IModelVisitor createDelegate();

    public IModelVisitor getDelegate() {
        if (delegate == null) {
            delegate = createDelegate();
        }
        return delegate;
    }

    @Override
    public void visitRoot(final RootModelElement e) {
        getDelegate().visitRoot(e);
    }

    @Override
    public void visitFieldSetOpen(final FieldSetOpenModelElement e) {
        getDelegate().visitFieldSetOpen(e);
    }

    @Override
    public void visitTable(final TableModelElement e) {
        getDelegate().visitTable(e);
    }

    @Override
    public void visitTabbed(final TabbedModelElement e) {
        getDelegate().visitTabbed(e);
    }

    @Override
    public void visitSubmitButton(final SubmitButtonModelElement e) {
        getDelegate().visitSubmitButton(e);
    }

    @Override
    public void visitAnchor(final AnchorModelElement e) {
        getDelegate().visitAnchor(e);
    }

    @Override
    public void visitUploadButton(final UploadButtonModelElement e) {
        getDelegate().visitUploadButton(e);
    }

    @Override
    public void visitCheckBoxInput(final CheckBoxInputModelElement e) {
        getDelegate().visitCheckBoxInput(e);
    }

    @Override
    public void visitSelect(final SelectModelElement e) {
        getDelegate().visitSelect(e);
    }

    @Override
    public void visitTextInput(final TextInputModelElement e) {
        getDelegate().visitTextInput(e);
    }

    @Override
    public void visitDateInput(final DateInputModelElement e) {
        getDelegate().visitDateInput(e);
    }

    @Override
    public void visitNumberInput(final NumberInputModelElement e) {
        getDelegate().visitNumberInput(e);
    }

    @Override
    public void visitHidden(final HiddenModelElement e) {
        getDelegate().visitHidden(e);
    }

    @Override
    public void visitFieldSetClose() {
        getDelegate().visitFieldSetClose();
    }

    @Override
    public void finish() {
        getDelegate().finish();
    }

}
