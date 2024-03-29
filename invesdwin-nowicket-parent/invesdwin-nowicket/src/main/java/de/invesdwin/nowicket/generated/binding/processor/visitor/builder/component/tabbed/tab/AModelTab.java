package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab;

import java.io.Serializable;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.util.lang.Objects;

@Immutable
public abstract class AModelTab implements ITab {

    public static final String DUMMY_CONTAINER_ID = "_DUMMY_";

    private WebMarkupContainer panel;

    public abstract IModel<Object> getPanelModel();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public IModel<Object> getPanelModelNonNull() {
        final IModel<Object> panelModel = getPanelModel();
        if (panelModel == null) {
            return (IModel) Model.of((Serializable) null);
        } else {
            return panelModel;
        }
    }

    @Override
    public final WebMarkupContainer getPanel(final String containerId) {
        if (panel == null) {
            panel = newPanel(containerId);
        }
        if (isNewContainerId(containerId)) {
            if (DUMMY_CONTAINER_ID.equals(panel.getId())) {
                panel = newPanel(containerId);
            } else {
                throw new IllegalStateException(
                        "Unable to change containerId to [" + containerId + "], existing: " + panel.getId());
            }
        }
        return panel;
    }

    private boolean isNewContainerId(final String containerId) {
        return containerId != null && !DUMMY_CONTAINER_ID.equals(containerId) && !panel.getId().equals(containerId);
    }

    protected abstract WebMarkupContainer newPanel(String containerId);

    @Override
    public int hashCode() {
        return Objects.hashCode(getPanelModelNonNull().getObject());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof ITab) {
            final AModelTab cObj = ModelDelegateTab.valueOf((ITab) obj);
            return Objects.equals(getPanelModelNonNull().getObject(), cObj.getPanelModelNonNull().getObject());
        } else {
            return false;
        }
    }

}
