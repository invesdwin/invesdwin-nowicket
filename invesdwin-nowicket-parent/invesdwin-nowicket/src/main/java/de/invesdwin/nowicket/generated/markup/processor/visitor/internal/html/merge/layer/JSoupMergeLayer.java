package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.layer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.Element;

@NotThreadSafe
public class JSoupMergeLayer extends AMergeLayer<org.jsoup.nodes.Element> {

    public JSoupMergeLayer(final org.jsoup.nodes.Element container) {
        super(container);
    }

    @Override
    protected void innerAdd(final Element element) {
        getContainer().append(element.toString());
    }

}
