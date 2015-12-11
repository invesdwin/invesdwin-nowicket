package de.invesdwin.nowicket.generated.binding.processor.visitor;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;

@NotThreadSafe
public abstract class AHtmlVisitor implements IHtmlVisitor {

    private final HtmlContext context;

    public AHtmlVisitor(final HtmlContext context) {
        this.context = context;
    }

    @Override
    public HtmlContext getContext() {
        return context;
    }

}
