package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.MarkupType;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.ADelegateModelVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.create.HtmlCreateVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.HtmlMergeVisitor;

@NotThreadSafe
public class HtmlGeneratorVisitor extends ADelegateModelVisitor {

    private final MarkupType markupType;

    public HtmlGeneratorVisitor(final ModelClassContext context, final MarkupType markupType) {
        super(context);
        this.markupType = markupType;
    }

    @Override
    protected IModelVisitor createDelegate() {
        if (!getContext().getHtmlFile(markupType).exists()) {
            return new HtmlCreateVisitor(getContext(), markupType);
        } else {
            return new HtmlMergeVisitor(getContext(), markupType);
        }
    }

}
