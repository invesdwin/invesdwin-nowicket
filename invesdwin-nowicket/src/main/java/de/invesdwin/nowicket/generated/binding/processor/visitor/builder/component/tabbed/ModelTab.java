package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.PanelFactory;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

@NotThreadSafe
public class ModelTab implements ITab {

    private final IHtmlElement<?, ?> element;

    public ModelTab(final IHtmlElement<?, ?> element) {
        this.element = element;
    }

    @Override
    public IModel<String> getTitle() {
        return element.getTitleModel();
    }

    @Override
    public WebMarkupContainer getPanel(final String containerId) {
        return PanelFactory.get().getPanel(containerId, element.getModel().getObject());
    }

    @Override
    public boolean isVisible() {
        return element.isVisible(element.getTargetObjectModel());
    }

}
