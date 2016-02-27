package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.PanelFactory;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

@NotThreadSafe
public class ModelTab implements ITab {

    private static final MetaDataKey<Boolean> KEY_DISABLED_BEHAVIOR_ADDED = new MetaDataKey<Boolean>() {
    };
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
        final Panel panel = PanelFactory.get().getPanel(containerId, element.getModel().getObject());
        final Boolean behaviorAdded = panel.getMetaData(KEY_DISABLED_BEHAVIOR_ADDED);
        if (behaviorAdded == null || !behaviorAdded) {
            panel.setMetaData(KEY_DISABLED_BEHAVIOR_ADDED, true);
            panel.add(new Behavior() {
                @Override
                public void onConfigure(final Component component) {
                    super.onConfigure(component);
                    component.setEnabled(ModelTab.this.isEnabled());
                }
            });
        } else {
            throw new IllegalStateException(
                    "KEY_DISABLED_BEHAVIOR_ADDED not expected, since panels are normally not cached!");
        }
        return panel;
    }

    @Override
    public boolean isVisible() {
        return element.isVisible(element.getTargetObjectModel());
    }

    public boolean isEnabled() {
        return element.isEnabled(element.getTargetObjectModel());
    }

}
