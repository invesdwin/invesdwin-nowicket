package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.apache.ecs.Element;
import org.apache.ecs.html.FieldSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import de.invesdwin.nowicket.generated.markup.processor.context.MarkupType;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.CheckBoxInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.DateInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.FieldSetOpenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.HiddenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
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
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.layer.AMergeLayer;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.layer.EcsMergeLayer;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.layer.JSoupMergeLayer;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class HtmlMergeVisitor extends AModelVisitor {

    private final MarkupType markupType;
    private final Deque<AMergeLayer<?>> mergeLayerStack = new ArrayDeque<AMergeLayer<?>>();
    private final HtmlComponentBuilder componentBuilder;
    private final Document document;
    private String originalHtml;

    public HtmlMergeVisitor(final ModelClassContext context, final MarkupType markupType) {
        super(context);
        this.markupType = markupType;
        this.componentBuilder = new HtmlComponentBuilder();
        try {
            final String html = FileUtils.readFileToString(context.getHtmlFile(markupType), Charset.defaultCharset());
            this.document = Jsoup.parse(html);
            // need to have original html for comparison later
            this.originalHtml = Documents.toString(document);
            final org.jsoup.nodes.Element rootElement = determineRootElement();
            mergeLayerStack.add(new JSoupMergeLayer(rootElement));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private org.jsoup.nodes.Element determineRootElement() {
        final Elements forms = document.getElementsByTag("form");
        if (forms.size() > 0) {
            return forms.get(forms.size() - 1);
        }
        final Elements wicketExtends = document.getElementsByTag("wicket:extend");
        if (wicketExtends.size() > 0) {
            return wicketExtends.get(wicketExtends.size() - 1);
        }
        final Elements wicketPanels = document.getElementsByTag("wicket:panel");
        if (wicketPanels.size() > 0) {
            return wicketPanels.get(wicketPanels.size() - 1);
        }
        final Elements bodies = document.getElementsByTag("body");
        if (bodies.size() > 0) {
            return bodies.get(bodies.size() - 1);
        }
        return document;
    }

    private void maybeAdd(final IModelElement<?> e, final Element component) {
        if (document.getElementsByAttributeValue("wicket:id", e.getWicketId()).isEmpty()
                //maybe commented out
                && !originalHtml.contains("wicket:id=\"" + e.getWicketId() + "\"")) {
            mergeLayerStack.getLast().add(component);
        }
    }

    @Override
    public void visitRoot(final RootModelElement e) {
        //ignore
    }

    @Override
    public void visitTextInput(final TextInputModelElement e) {
        final Element textInput = componentBuilder.createTextInput(e);
        maybeAdd(e, textInput);
    }

    @Override
    public void visitPanel(final PanelModelElement e) {
        final Element panel = componentBuilder.createPanel(e);
        maybeAdd(e, panel);
    }

    @Override
    public void visitDateInput(final DateInputModelElement e) {
        final Element dateInput = componentBuilder.createDateInput(e);
        maybeAdd(e, dateInput);
    }

    @Override
    public void visitNumberInput(final NumberInputModelElement e) {
        final Element numberInput = componentBuilder.createNumberInput(e);
        maybeAdd(e, numberInput);
    }

    @Override
    public void visitSelect(final SelectModelElement e) {
        final Element select = componentBuilder.createSelect(e);
        maybeAdd(e, select);
    }

    @Override
    public void visitCheckBoxInput(final CheckBoxInputModelElement e) {
        final Element checkBoxInput = componentBuilder.createCheckBoxInput(e);
        maybeAdd(e, checkBoxInput);
    }

    @Override
    public void visitSubmitButton(final SubmitButtonModelElement e) {
        final Element button = componentBuilder.createSubmitButton(e);
        maybeAdd(e, button);
    }

    @Override
    public void visitAnchor(final AnchorModelElement e) {
        final Element button = componentBuilder.createAnchor(e);
        maybeAdd(e, button);
    }

    @Override
    public void visitUploadButton(final UploadButtonModelElement e) {
        final Element fileInput = componentBuilder.createFileInput(e);
        maybeAdd(e, fileInput);
    }

    @Override
    public void visitFieldSetOpen(final FieldSetOpenModelElement e) {
        final FieldSet fieldSet = componentBuilder.createFieldSet(e);
        mergeLayerStack.add(new EcsMergeLayer(fieldSet));
    }

    @Override
    public void visitTable(final TableModelElement e) {
        final Element table = componentBuilder.createTable(e);
        maybeAdd(e, table);
    }

    @Override
    public void visitTabbed(final TabbedModelElement e) {
        final Element tabbed = componentBuilder.createTabbed(e);
        maybeAdd(e, tabbed);
    }

    @Override
    public void visitFieldSetClose() {
        final EcsMergeLayer fieldSet = (EcsMergeLayer) mergeLayerStack.removeLast();
        Assertions.assertThat(fieldSet).isNotNull();
        if (fieldSet.getSize() > 0) {
            // since this fieldset has elements, we need to keep it
            final AMergeLayer<?> previousFieldset = mergeLayerStack.getLast();
            previousFieldset.add(fieldSet.getContainer());
        }
    }

    @Override
    public void visitHidden(final HiddenModelElement e) {
        // ignore
    }

    @Override
    public void finish() {
        final String s = Documents.toString(document);
        final File htmlFile = getContext().getHtmlFile(markupType);

        // skip files without changes
        if (Strings.equals(s, originalHtml)) {
            return;
        }

        try {
            FileUtils.forceMkdir(htmlFile.getParentFile());
            FileUtils.writeStringToFile(htmlFile, s, Charset.defaultCharset());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
