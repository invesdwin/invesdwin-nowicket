package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.application.PanelFactory;
import de.invesdwin.nowicket.component.modal.ModalConfig;
import de.invesdwin.nowicket.component.modal.ModalContainer;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.ModalHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.RootHtmlElement;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.collections.Arrays;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class ShowModalPanelGuiTask implements IGuiTask {

    private final Object modelObject;
    private final ModalConfig config;
    private ModalContainer modal;

    public ShowModalPanelGuiTask(final Object modelObject, final ModalConfig config) {
        this.modelObject = modelObject;
        this.config = config;
    }

    @Override
    public Collection<? extends Component> process(final Component component) {
        Assertions.assertThat(isShowing()).isFalse();
        final Panel modalPanel = PanelFactory.get().getPanel(ModalContainer.PANEL_MARKUP_ID, modelObject);
        if (modal == null) {
            final HtmlContext parentContext = HtmlContext.get(component);
            if (parentContext != null) {
                modal = parentContext.getComponentRegistry().getComponent(ModalHtmlElement.WICKET_ID);
            }
        }
        if (modal == null) {
            modal = Components.findComponent(ModalContainer.class, component);
        }
        if (modal == null) {
            //use root modal
            modal = Components.findComponent(ModalContainer.class, component.getPage());
        }
        Assertions.assertThat(modal).as("No %s found in component hierarchy!", ModalContainer.class).isNotNull();
        final IModel<String> title = getTitle(modalPanel);
        modal.show(title, modalPanel, config);
        return Arrays.asList(modal);
    }

    private IModel<String> getTitle(final Panel modalPanel) {
        final HtmlContext modalContext = HtmlContext.get(modalPanel);
        if (modalContext != null) {
            final RootHtmlElement modalRootElement = modalContext.getElementRegistry()
                    .getElement(RootHtmlElement.WICKET_ID);
            return modalRootElement.getTitleModel();
        } else if (modalPanel.getDefaultModelObject() != null) {
            return Model.of(Objects.toVisibleName(modalPanel.getDefaultModelObject().getClass().getName()));
        } else {
            return Model.of(Objects.toVisibleName(modalPanel.getClass().getName()));
        }
    }

    public Collection<? extends Component> hide() {
        Assertions.assertThat(isShowing()).as("Can only hide modal panels if they are actually showing!").isTrue();
        modal.hide();
        return Arrays.asList(modal);
    }

    public Component getComponent() {
        return modal;
    }

    public boolean isShowing() {
        if (modal != null) {
            return modal.isShowing();
        } else {
            return false;
        }
    }

}
