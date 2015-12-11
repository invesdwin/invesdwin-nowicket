package de.invesdwin.nowicket.generated.binding.processor.visitor.internal;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.CheckBoxInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ContainerOpenHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FeedbackHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FormHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
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
import de.invesdwin.nowicket.generated.binding.processor.visitor.AHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class BindingVisitor extends AHtmlVisitor {

    private final IBindingBuilder bindingBuilder;

    private final Deque<MarkupContainer> containerStack;
    private ContainerOpenHtmlElement nextContainer;

    public BindingVisitor(final HtmlContext context) {
        super(context);
        this.bindingBuilder = context.getBindingBuilder();
        this.containerStack = new ArrayDeque<MarkupContainer>();
        this.containerStack.addLast(context.getMarkupContainer());
    }

    private void addContainer(final IHtmlElement<?, ?> e, final MarkupContainer c) {
        containerStack.getLast().add(c);
        if (nextContainer != null) {
            containerStack.addLast(c);
            nextContainer = null;
        }
    }

    private void addComponent(final Component c) {
        Assertions.assertThat(nextContainer).isNull();
        containerStack.getLast().add(c);
    }

    protected void add(final IHtmlElement<?, ?> e, final Component c) {
        if (c == null) {
            // unknown elements might be returned as null
            return;
        }
        if (c instanceof MarkupContainer) {
            addContainer(e, (MarkupContainer) c);
        } else {
            addComponent(c);
        }
        //configure after adding to container, so that the root can be identified
        Assertions.assertThat(bindingBuilder.configure(e, c)).isTrue();
        getContext().getComponentRegistry().addComponent(c);
    }

    @Override
    public void visitContainerOpen(final ContainerOpenHtmlElement e) {
        Assertions.assertThat(nextContainer).isNull();
        nextContainer = e;
    }

    @Override
    public void visitContainerClose() {
        if (nextContainer != null) {
            nextContainer = null;
        } else {
            Assertions.assertThat(containerStack.removeLast()).isNotNull();
        }
    }

    @Override
    public void visitRoot(final RootHtmlElement e) {
        bindingBuilder.configureRoot(e, getContext().getMarkupContainer());
    }

    @Override
    public void visitForm(final FormHtmlElement e) {
        final Component c = bindingBuilder.createForm(e);
        add(e, c);
    }

    @Override
    public void visitModal(final ModalHtmlElement e) {
        final Component c = bindingBuilder.createModal(e);
        add(e, c);
    }

    @Override
    public void visitFeedback(final FeedbackHtmlElement e) {
        final Component c = bindingBuilder.createFeedback(e);
        add(e, c);
    }

    @Override
    public void visitTable(final TableHtmlElement e) {
        final Component c = bindingBuilder.createTable(e);
        add(e, c);
    }

    @Override
    public void visitTabbed(final TabbedHtmlElement e) {
        final Component c = bindingBuilder.createTabbed(e);
        add(e, c);
    }

    @Override
    public void visitUnorderedList(final UnorderedListHtmlElement e) {
        final Component c = bindingBuilder.createUnorderedList(e);
        add(e, c);
    }

    @Override
    public void visitSubmitButton(final SubmitButtonHtmlElement e) {
        final Component c = bindingBuilder.createSubmitButton(e);
        add(e, c);
    }

    @Override
    public void visitFileUploadButton(final UploadButtonHtmlElement e) {
        final Component c = bindingBuilder.createUploadButton(e);
        add(e, c);
    }

    @Override
    public void visitGridColumn(final GridColumnHtmlElement e) {
        final Component c = bindingBuilder.createGridColumn(e);
        add(e, c);
    }

    @Override
    public void visitCheckBoxInput(final CheckBoxInputHtmlElement e) {
        final Component c = bindingBuilder.createCheckBoxInput(e);
        add(e, c);
    }

    @Override
    public void visitSelect(final SelectHtmlElement e) {
        final Component c = bindingBuilder.createSelect(e);
        add(e, c);
    }

    @Override
    public void visitTextInput(final TextInputHtmlElement e) {
        final Component c = bindingBuilder.createTextInput(e);
        add(e, c);
    }

    @Override
    public void visitAnchor(final AnchorHtmlElement e) {
        final Component c = bindingBuilder.createAnchor(e);
        add(e, c);
    }

    @Override
    public void visitImage(final ImageHtmlElement e) {
        final Component c = bindingBuilder.createImage(e);
        add(e, c);
    }

    @Override
    public void visitDateInput(final DateInputHtmlElement e) {
        final Component c = bindingBuilder.createDateInput(e);
        add(e, c);
    }

    @Override
    public void visitNumberInput(final NumberInputHtmlElement e) {
        final Component c = bindingBuilder.createNumberInput(e);
        add(e, c);
    }

    @Override
    public void visitTextArea(final TextAreaHtmlElement e) {
        final Component c = bindingBuilder.createTextArea(e);
        add(e, c);
    }

    @Override
    public void visitRadioInput(final RadioInputHtmlElement e) {
        final Component c = bindingBuilder.createRadioInput(e);
        add(e, c);
    }

    @Override
    public void visitPasswordInput(final PasswordInputHtmlElement e) {
        final Component c = bindingBuilder.createPasswordInput(e);
        add(e, c);
    }

    @Override
    public void visitLabel(final LabelHtmlElement e) {
        final Component c = bindingBuilder.createLabel(e);
        add(e, c);
    }

    @Override
    public void visitLegend(final LegendHtmlElement e) {
        final Component c = bindingBuilder.createLegend(e);
        add(e, c);
    }

    @Override
    public void visitUnknown(final IUnknownHtmlElement<?> e) {
        final Component c = bindingBuilder.createUnknown(e);
        add(e, c);
    }

    @Override
    public void finish() {}

}
