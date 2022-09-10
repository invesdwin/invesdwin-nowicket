package de.invesdwin.nowicket.component.modal;

import java.util.Stack;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.jquery.util.Strings2;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModalContainer extends Panel {

    public static final String PANEL_MARKUP_ID = "panel";

    private boolean showing;
    private boolean renderedShowing;
    private final WebMarkupContainer modalContent;

    private final Label titleLabel;
    private ModalConfig config;
    private boolean alreadyRendered;

    private final Stack<HeaderItem> rootRenderHeadQueue = new Stack<HeaderItem>();

    public ModalContainer(final String id) {
        super(id);
        modalContent = new WebMarkupContainer("modalContent");
        modalContent.setOutputMarkupId(true);
        add(modalContent);
        /*
         * initialially put empty panel, later keep the ones as invisible that have been replaced to be able to show
         * replaced stacked modals properly
         */
        modalContent.add(new WebMarkupContainer(PANEL_MARKUP_ID));
        titleLabel = new Label("title");
        titleLabel.setEscapeModelStrings(false);
        modalContent.add(titleLabel);
        setOutputMarkupId(true);
        add(new CssClassNameAppender("modal"));
        add(new AttributeModifier("data-keyboard", "false"));
        add(new AttributeModifier("data-backdrop", "static"));
    }

    public void show(final IModel<String> title, final Panel panel, final ModalConfig config) {
        Assertions.assertThat(this.showing).isFalse();
        Assertions.assertThat(panel.getId()).isEqualTo(PANEL_MARKUP_ID);
        modalContent.addOrReplace(panel);
        this.titleLabel.setDefaultModel(title);
        this.config = config;
        this.showing = true;
    }

    public void hide() {
        Assertions.assertThat(this.showing).isTrue();
        this.showing = false;
    }

    public boolean isShowing() {
        return showing;
    }

    public boolean isRenderedShowing() {
        return renderedShowing;
    }

    @Override
    public boolean isVisible() {
        //component always visible, so render is called for root in order to handle children properly
        return true;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        alreadyRendered = false;
        if (config != null) {
            if (Strings.isNotBlank(config.getWidth())) {
                add(new AttributeModifier("data-width", config.getWidth()) {
                    @Override
                    public boolean isTemporary(final Component component) {
                        return true;
                    }
                });
            }
            if (Strings.isNotBlank(config.getHeight())) {
                add(new AttributeModifier("data-max-height", config.getHeight()) {
                    @Override
                    public boolean isTemporary(final Component component) {
                        return true;
                    }
                });
            }
        }
        add(new AttributeAppender("style", "max-height: 98%") {
            @Override
            public boolean isTemporary(final Component component) {
                return true;
            }
        });
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        HeaderItem headerItem = null;
        if (renderedShowing && showing) {
            /*
             * workaround for modal tags being copied by bootstrap-modal right into body and wicket updating the wrong
             * tags in their previous position
             */
            headerItem = OnDomReadyHeaderItem.forScript(createRefreshScript());
        } else if (!renderedShowing && showing) {
            headerItem = OnDomReadyHeaderItem.forScript(createShowScript());
            renderedShowing = true;
        } else if (renderedShowing && !showing) {
            final StringBuilder script = new StringBuilder();
            script.append(createRefreshScript());
            script.append(createHideScript());
            headerItem = OnDomReadyHeaderItem.forScript(script);
            renderedShowing = false;
        }
        renderDirectlyOrDelegateToRoot(response, headerItem);
        alreadyRendered = true;
    }

    /**
     * fix order of refresh to keep the hierarchy during show(); also only the root container should handle the render
     * in inverse order
     */
    private void renderDirectlyOrDelegateToRoot(final IHeaderResponse response, final HeaderItem headerItem) {
        final ModalContainer rootModalContainer = Components.findComponent(ModalContainer.class, this.getPage());
        //render directly if this is not a child of the root and thus the root was already rendered
        if (rootModalContainer == this || rootModalContainer.alreadyRendered) {
            while (!rootRenderHeadQueue.isEmpty()) {
                response.render(rootRenderHeadQueue.pop());
            }
            if (headerItem != null) {
                response.render(headerItem);
            }
        } else if (headerItem != null) {
            //otherwise put the children into the queue to handle page refreshes in the correct order
            rootModalContainer.rootRenderHeadQueue.push(headerItem);
        }
    }

    private CharSequence createRefreshScript() {
        final StringBuilder sb = new StringBuilder();
        //need to reinitialize the modal on page refresh, which throws away modal state in browser :/
        sb.append(createHideScript());
        sb.append("\n");
        sb.append(createShowScript());
        sb.append("\n");
        return sb;
    }

    private CharSequence createShowScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("$('#");
        sb.append(Strings2.getMarkupId(this));
        sb.append("').modal('show');");
        return sb;
    }

    private CharSequence createHideScript() {
        //see http://stackoverflow.com/questions/4223141/using-jquery-to-delete-all-elements-with-a-given-id
        //https://stackoverflow.com/questions/4036857/how-can-i-remove-a-style-added-with-css-function
        final StringBuilder sb = new StringBuilder();
        sb.append("$('[id=");
        sb.append(Strings2.getMarkupId(this));
        sb.append("]').modal('hide');");
        return sb;
    }

}
