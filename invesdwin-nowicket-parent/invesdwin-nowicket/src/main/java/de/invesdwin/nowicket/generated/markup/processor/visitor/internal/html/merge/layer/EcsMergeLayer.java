package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.layer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.Element;
import org.apache.ecs.MultiPartElement;
import org.apache.ecs.html.FieldSet;

@NotThreadSafe
public class EcsMergeLayer extends AMergeLayer<MultiPartElement> {

    public EcsMergeLayer(final FieldSet container) {
        super(container);
    }

    @Override
    protected void innerAdd(final Element element) {
        getContainer().addElementToRegistry(element);
    }

}
