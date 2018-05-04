package de.invesdwin.nowicket.generated.binding.processor.visitor;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.CheckBoxInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ContainerOpenHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FeedbackHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FormHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.HiddenInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IUnknownHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ImageHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.LabelHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.LegendHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ModalHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.NumberInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.PasswordInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.RadioInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.RootHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SubmitButtonHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextAreaHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UnorderedListHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;

public interface IHtmlVisitor {

    HtmlContext getContext();

    void visitRoot(RootHtmlElement e);

    void visitContainerOpen(ContainerOpenHtmlElement e);

    void visitGridColumn(GridColumnHtmlElement e);

    void visitLegend(LegendHtmlElement e);

    void visitForm(FormHtmlElement e);

    void visitModal(ModalHtmlElement e);

    void visitFeedback(FeedbackHtmlElement e);

    void visitTable(TableHtmlElement e);

    void visitTabbed(TabbedHtmlElement e);

    void visitUnorderedList(UnorderedListHtmlElement e);

    void visitSubmitButton(SubmitButtonHtmlElement e);

    void visitFileUploadButton(UploadButtonHtmlElement e);

    void visitCheckBoxInput(CheckBoxInputHtmlElement e);

    void visitSelect(SelectHtmlElement e);

    void visitTextInput(TextInputHtmlElement e);

    void visitAnchor(AnchorHtmlElement e);

    void visitImage(ImageHtmlElement e);

    void visitDateInput(DateInputHtmlElement e);

    void visitNumberInput(NumberInputHtmlElement e);

    void visitHiddenInput(HiddenInputHtmlElement e);

    void visitTextArea(TextAreaHtmlElement e);

    void visitRadioInput(RadioInputHtmlElement e);

    void visitPasswordInput(PasswordInputHtmlElement e);

    void visitLabel(LabelHtmlElement e);

    void visitUnknown(IUnknownHtmlElement<?> e);

    void visitContainerClose();

    void finish();

}
