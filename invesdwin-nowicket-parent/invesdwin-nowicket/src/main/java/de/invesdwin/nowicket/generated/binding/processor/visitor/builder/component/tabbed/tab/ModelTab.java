package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.PanelFactory;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

@NotThreadSafe
public class ModelTab extends AModelTab {

    private static final MetaDataKey<Boolean> KEY_DISABLED_BEHAVIOR_ADDED = new MetaDataKey<Boolean>() {
    };
    private final IHtmlElement<?, ?> element;
    private final IModel<String> tabTitleModel;
    private final IModel<Object> panelModel;
    private final IModel<Object> rowObjectModel;
    private final IModel<Object> rootObjectModel;

    public ModelTab(final IHtmlElement<?, ?> element, final IModel<String> tabTitleModel,
            final IModel<Object> panelModel, final IModel<Object> rootObjectModel,
            final IModel<Object> rowObjectModel) {
        this.element = element;
        this.tabTitleModel = tabTitleModel;
        this.panelModel = panelModel;
        this.rootObjectModel = rootObjectModel;
        this.rowObjectModel = rowObjectModel;
    }

    @Override
    public IModel<String> getTitle() {
        return tabTitleModel;
    }

    @Override
    protected WebMarkupContainer newPanel(final String containerId) {
        final Object modelValue = panelModel.getObject();
        final Panel panel = PanelFactory.get().getPanel(containerId, modelValue);
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
        return element.isVisibleFromTarget(rootObjectModel, rowObjectModel);
    }

    public boolean isEnabled() {
        return element.isEnabledFromTarget(rootObjectModel, rowObjectModel);
    }

    @Override
    public IModel<Object> getPanelModel() {
        return panelModel;
    }

}
