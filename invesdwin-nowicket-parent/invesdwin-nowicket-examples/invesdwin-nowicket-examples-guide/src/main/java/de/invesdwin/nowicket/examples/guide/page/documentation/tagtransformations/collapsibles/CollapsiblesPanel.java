package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.collapsibles;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelAccordion;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelCollapsibleList;

@NotThreadSafe
public class CollapsiblesPanel extends Panel {

    public CollapsiblesPanel(final String id, final IModel<Collapsibles> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (CollapsiblesConstants.asCollapsible.equals(e.getWicketId())) {
                    return new ModelCollapsibleList((ITabbedHtmlElement<?, ?>) e);
                }
                if (CollapsiblesConstants.asAccordion.equals(e.getWicketId())) {
                    return new ModelAccordion((ITabbedHtmlElement<?, ?>) e);
                }
                return super.create(e);
            }

        }).bind();
    }

}
