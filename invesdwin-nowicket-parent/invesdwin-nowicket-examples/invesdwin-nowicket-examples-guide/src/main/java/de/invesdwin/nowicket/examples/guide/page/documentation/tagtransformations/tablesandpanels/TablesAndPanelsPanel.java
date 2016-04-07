package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandpanels;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.collapsibles.CollapsiblesConstants;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelAccordion;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.ModelCollapsibleList;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.ModelTabbedPanel;

@NotThreadSafe
public class TablesAndPanelsPanel extends Panel {

    public TablesAndPanelsPanel(final String id, final IModel<TablesAndPanels> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createTable(final TableHtmlElement e) {
                if (CollapsiblesConstants.asCollapsible.equals(e.getWicketId())) {
                    return new ModelCollapsibleList(e);
                }
                if (CollapsiblesConstants.asAccordion.equals(e.getWicketId())) {
                    return new ModelAccordion(e);
                }
                if (CollapsiblesConstants.asTabbed.equals(e.getWicketId())) {
                    return new ModelTabbedPanel(e);
                }
                return super.createTable(e);
            }

        }).bind();
    }

}
