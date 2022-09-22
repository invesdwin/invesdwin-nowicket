package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tabbedandpanels;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelCollapsibleList;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.accordion.ModelAccordion;

@NotThreadSafe
public class TabbedAndPanelsPanel extends Panel {

    public TabbedAndPanelsPanel(final String id, final IModel<TabbedAndPanels> model) {
        super(id, model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createTabbed(final ITabbedHtmlElement<?, ?> e) {
                if (TabbedAndPanelsConstants.asCollapsible.equals(e.getWicketId())) {
                    return new ModelCollapsibleList(e);
                }
                if (TabbedAndPanelsConstants.asAccordion.equals(e.getWicketId())) {
                    return new ModelAccordion(e);
                }
                return super.createTabbed(e);
            }

        }).bind();
    }

}
