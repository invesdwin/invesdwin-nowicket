package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column.hide;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;

@NotThreadSafe
public final class ModelHideColumnBehavior extends Behavior {

    private final ATableColumnHtmlElement<?, ?> element;

    private ModelHideColumnBehavior(final ATableColumnHtmlElement<?, ?> element) {
        this.element = element;
    }

    @Override
    public void onConfigure(final Component component) {
        component.setVisible(element.getModelElement().getBeanPathElement().isVisible());
    }

    public static void maybeAdd(final ATableColumnHtmlElement<?, ?> element, final Component component) {
        if (component.getBehaviors(ModelHideColumnBehavior.class).isEmpty()) {
            component.add(new ModelHideColumnBehavior(element));
        }
    }
}