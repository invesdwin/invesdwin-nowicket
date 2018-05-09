package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

@NotThreadSafe
public final class ModelDelegateTab extends AModelTab {

    private final ITab delegate;

    private ModelDelegateTab(final ITab delegate) {
        this.delegate = delegate;
    }

    @Override
    public IModel<String> getTitle() {
        return delegate.getTitle();
    }

    @Override
    protected WebMarkupContainer newPanel(final String containerId) {
        return delegate.getPanel(containerId);
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @Override
    protected IModel<?> getPanelModel() {
        return getPanel(DUMMY_CONTAINER_ID).getDefaultModel();
    }

    public static AModelTab valueOf(final ITab tab) {
        if (tab instanceof AModelTab) {
            return (AModelTab) tab;
        } else {
            return new ModelDelegateTab(tab);
        }
    }

}
