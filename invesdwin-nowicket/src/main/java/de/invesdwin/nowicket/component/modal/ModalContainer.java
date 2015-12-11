package de.invesdwin.nowicket.component.modal;

import java.awt.Dimension;
import java.util.Stack;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
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

@NotThreadSafe
public class ModalContainer extends Panel {

    public static final String PANEL_MARKUP_ID = "panel";

    private boolean showing;
    private boolean renderedShowing;
    private final WebMarkupContainer modalContent;

    private final Label titleLabel;
    private Dimension dimension;
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
        add(new CssClassNameAppender("fade"));
        add(new AttributeModifier("data-keyboard", "false"));
        add(new AttributeModifier("data-backdrop", "static"));
    }

    public void show(final IModel<String> title, final Panel panel, final Dimension dimension) {
        Assertions.assertThat(this.showing).isFalse();
        Assertions.assertThat(panel.getId()).isEqualTo(PANEL_MARKUP_ID);
        modalContent.addOrReplace(panel);
        this.titleLabel.setDefaultModel(title);
        this.dimension = dimension;
        this.showing = true;
    }

    public void hide() {
        Assertions.assertThat(this.showing).isTrue();
        this.showing = false;
    }

    public boolean isShowing() {
        return showing;
    }

    @Override
    public boolean isVisible() {
        //component always visible, so render is called for root in order to handle children properly
        return true;
    }

    @Override
    protected void onConfigure() {
        alreadyRendered = false;
        if (dimension != null) {
            add(new AttributeModifier("data-width", dimension.width) {
                @Override
                public boolean isTemporary(final Component component) {
                    return true;
                }
            });
            add(new AttributeModifier("data-max-height", dimension.height) {
                @Override
                public boolean isTemporary(final Component component) {
                    return true;
                }
            });
        }
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
            //scroll gets enabled again by registered hidden.bs.modal event from bootstrapModalConfig.js
            script.append("$(window).disablescroll();");
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
            if (headerItem != null) {
                response.render(headerItem);
            }
            while (!rootRenderHeadQueue.isEmpty()) {
                response.render(rootRenderHeadQueue.pop());
            }
        } else if (headerItem != null) {
            //otherwise put the children into the queue to handle page refreshes in the correct order
            rootModalContainer.rootRenderHeadQueue.push(headerItem);
        }
    }

    private CharSequence createRefreshScript() {
        final StringBuilder sb = new StringBuilder();

        sb.append("$('[id=");
        sb.append(Strings2.getMarkupId(this));
        sb.append("]').removeClass('fade');\n");
        //need to reinitialize the modal on page refresh, which throws away modal state in browser :/
        sb.append(createHideScript());
        sb.append("\n");
        sb.append(createShowScript());
        sb.append("\n");
        sb.append("$('[id=");
        sb.append(Strings2.getMarkupId(this));
        sb.append("]').addClass('fade');\n");
        sb.append("$('.modal-backdrop:visible').addClass('fade');\n");

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
        return "$('[id=" + Strings2.getMarkupId(this) + "]').modal('hide');";
    }

}
