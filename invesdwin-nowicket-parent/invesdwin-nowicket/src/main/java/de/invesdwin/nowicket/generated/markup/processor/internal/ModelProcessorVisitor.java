package de.invesdwin.nowicket.generated.markup.processor.internal;

import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorType;
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
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.context.ABeanPathContext;
import de.invesdwin.norva.beanpath.spi.element.ButtonBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.CheckBoxBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.ComboBoxBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.ContainerOpenBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.HiddenBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.RootBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TabbedBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TextFieldBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.UploadButtonBeanPathElement;
import de.invesdwin.norva.beanpath.spi.visitor.ABeanPathVisitor;

@NotThreadSafe
public class ModelProcessorVisitor extends ABeanPathVisitor {

    private final AModelContext context;
    private final List<IModelVisitor> visitors;

    public ModelProcessorVisitor(final ABeanPathContext beanPathContext, final AModelContext context,
            final IModelVisitor... visitors) {
        this(beanPathContext, context, Arrays.asList(visitors));
    }

    public ModelProcessorVisitor(final ABeanPathContext beanPathContext, final AModelContext context,
            final List<IModelVisitor> visitors) {
        super(beanPathContext);
        this.context = context;
        this.visitors = visitors;
    }

    @Override
    public void visitRoot(final RootBeanPathElement e) {
        new RootModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitContainerOpen(final ContainerOpenBeanPathElement e) {
        new FieldSetOpenModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitButton(final ButtonBeanPathElement e) {
        if (AnchorType.valueOf(e) != null) {
            new AnchorModelElement(context, e).accept(visitors);
        } else {
            new SubmitButtonModelElement(context, e).accept(visitors);
        }
    }

    @Override
    public void visitUploadButton(final UploadButtonBeanPathElement e) {
        new UploadButtonModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitTextField(final TextFieldBeanPathElement e) {
        if (e.getAccessor().getRawType().isDate()) {
            new DateInputModelElement(context, e).accept(visitors);
        } else if (e.getAccessor().getRawType().isNumber()) {
            new NumberInputModelElement(context, e).accept(visitors);
        } else {
            new TextInputModelElement(context, e).accept(visitors);
        }
    }

    @Override
    public void visitCheckBox(final CheckBoxBeanPathElement e) {
        new CheckBoxInputModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitComboBox(final ComboBoxBeanPathElement e) {
        new SelectModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitTable(final TableBeanPathElement e) {
        new TableModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitTabbed(final TabbedBeanPathElement e) {
        new TabbedModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitHidden(final HiddenBeanPathElement e) {
        new HiddenModelElement(context, e).accept(visitors);
    }

    @Override
    public void visitContainerClose() {
        for (final IModelVisitor visitor : visitors) {
            visitor.visitFieldSetClose();
        }
    }

    @Override
    public void finish() {
        for (final IModelVisitor visitor : visitors) {
            visitor.finish();
        }
    }

}
