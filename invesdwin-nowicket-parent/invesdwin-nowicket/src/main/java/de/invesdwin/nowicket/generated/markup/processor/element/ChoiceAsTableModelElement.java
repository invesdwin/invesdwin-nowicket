package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.ChoiceAsTableBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.util.collections.Collections;

@NotThreadSafe
public final class ChoiceAsTableModelElement extends ATableModelElement {

    private final TableContainerColumnModelElement containerColumn;

    private ChoiceAsTableModelElement(final AChoiceModelElement<?> choiceModelElement) {
        super(choiceModelElement.getContext(),
                ChoiceAsTableBeanPathElement.maybeWrap(choiceModelElement.getBeanPathElement()));
        this.containerColumn = new TableContainerColumnModelElement(choiceModelElement.getContext(),
                getBeanPathElement().getContainerColumn());
    }

    @Override
    protected void innerAccept(final IModelVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TableAnchorColumnModelElement> getAnchorColumns() {
        //only used in real tables
        return Collections.emptyList();
    }

    @Override
    public List<TableSubmitButtonColumnModelElement> getButtonColumns() {
        //only used in real tables
        return Collections.emptyList();
    }

    @Override
    public List<TableNumberColumnModelElement> getNumberColumns() {
        //only used in real tables
        return Collections.emptyList();
    }

    @Override
    public List<TableDateColumnModelElement> getDateColumns() {
        //only used in real tables
        return Collections.emptyList();
    }

    @Override
    public List<TableTextColumnModelElement> getTextColumns() {
        //only used in real tables
        return Collections.emptyList();
    }

    @Override
    public TableContainerColumnModelElement getContainerColumn() {
        return containerColumn;
    }

    public static ATableModelElement maybeWrap(final AChoiceModelElement<?> originalElement) {
        if (originalElement instanceof ATableModelElement) {
            return (ATableModelElement) originalElement;
        } else {
            return new ChoiceAsTableModelElement(originalElement);
        }
    }

}
