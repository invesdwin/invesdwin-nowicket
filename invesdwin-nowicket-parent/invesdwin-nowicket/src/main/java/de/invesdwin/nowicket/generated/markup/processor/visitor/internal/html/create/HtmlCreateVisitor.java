package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.create;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.apache.ecs.Element;
import org.apache.ecs.MultiPartElement;
import org.apache.ecs.html.Body;
import org.apache.ecs.html.FieldSet;
import org.apache.ecs.html.Form;
import org.apache.ecs.html.Html;
import org.apache.ecs.xml.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.invesdwin.nowicket.generated.binding.processor.element.FeedbackHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FormHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ModalHtmlElement;
import de.invesdwin.nowicket.generated.markup.processor.context.MarkupType;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.CheckBoxInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.DateInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.FieldSetOpenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.HiddenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NumberInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.PanelModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.RootModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SelectModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SubmitButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.UploadButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.visitor.AModelVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.Documents;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.HtmlComponentBuilder;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class HtmlCreateVisitor extends AModelVisitor {

    private final MarkupType markupType;
    private final Html html;
    private final Deque<MultiPartElement> panelStack = new ArrayDeque<MultiPartElement>();
    private final HtmlComponentBuilder componentBuilder;

    public HtmlCreateVisitor(final ModelClassContext context, final MarkupType markupType) {
        super(context);
        this.markupType = markupType;
        this.componentBuilder = new HtmlComponentBuilder();
        this.html = newHtml();
    }

    private Html newHtml() {
        final Html html = componentBuilder.createHtml();
        final Body body = new Body();
        html.addElement(body);
        final XML wicketContainer;
        if (markupType == MarkupType.PANEL) {
            wicketContainer = new XML("wicket:panel");
        } else {
            wicketContainer = new XML("wicket:extend");
        }
        body.addElement(wicketContainer);
        final Form form = componentBuilder.createForm(FormHtmlElement.WICKET_ID);
        wicketContainer.addElement(form);
        final Element feedback = componentBuilder.createFeedback(FeedbackHtmlElement.WICKET_ID);
        form.addElement(feedback);
        final Element modal = componentBuilder.createModal(ModalHtmlElement.WICKET_ID);
        form.addElement(modal);
        panelStack.add(form);
        return html;
    }

    private void maybeAdd(final Element element) {
        if (element != null) {
            panelStack.getLast().addElementToRegistry(element);
        }
    }

    @Override
    public void visitRoot(final RootModelElement e) {
        //ignore
    }

    @Override
    public void visitTextInput(final TextInputModelElement e) {
        final Element textInput = componentBuilder.createTextInput(e);
        maybeAdd(textInput);
    }

    @Override
    public void visitPanel(final PanelModelElement e) {
        final Element panel = componentBuilder.createPanel(e);
        maybeAdd(panel);
    }

    @Override
    public void visitDateInput(final DateInputModelElement e) {
        final Element dateInput = componentBuilder.createDateInput(e);
        maybeAdd(dateInput);
    }

    @Override
    public void visitNumberInput(final NumberInputModelElement e) {
        final Element numberInput = componentBuilder.createNumberInput(e);
        maybeAdd(numberInput);
    }

    @Override
    public void visitSelect(final SelectModelElement e) {
        final Element select = componentBuilder.createSelect(e);
        maybeAdd(select);
    }

    @Override
    public void visitCheckBoxInput(final CheckBoxInputModelElement e) {
        final Element checkBoxInput = componentBuilder.createCheckBoxInput(e);
        maybeAdd(checkBoxInput);
    }

    @Override
    public void visitSubmitButton(final SubmitButtonModelElement e) {
        final Element button = componentBuilder.createSubmitButton(e);
        maybeAdd(button);
    }

    @Override
    public void visitAnchor(final AnchorModelElement e) {
        final Element button = componentBuilder.createAnchor(e);
        maybeAdd(button);
    }

    @Override
    public void visitUploadButton(final UploadButtonModelElement e) {
        final Element fileInput = componentBuilder.createFileInput(e);
        maybeAdd(fileInput);
    }

    @Override
    public void visitFieldSetOpen(final FieldSetOpenModelElement e) {
        final FieldSet fieldset = componentBuilder.createFieldSet(e);
        maybeAdd(fieldset);
        panelStack.add(fieldset);
    }

    @Override
    public void visitTable(final TableModelElement e) {
        final Element table = componentBuilder.createTable(e);
        maybeAdd(table);
    }

    @Override
    public void visitTabbed(final TabbedModelElement e) {
        final Element tabbed = componentBuilder.createTabbed(e);
        maybeAdd(tabbed);
    }

    @Override
    public void visitHidden(final HiddenModelElement e) {
        // ignore
    }

    @Override
    public void visitFieldSetClose() {
        Assertions.assertThat(panelStack.removeLast()).isNotNull();
    }

    @Override
    public void finish() {
        String s = "<!DOCTYPE HTML>" + html.toString();
        final Document document = Jsoup.parse(s);
        s = Documents.toString(document);
        final File htmlFile = getContext().getHtmlFile(markupType);
        try {
            FileUtils.forceMkdir(htmlFile.getParentFile());
            FileUtils.writeStringToFile(htmlFile, s);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
