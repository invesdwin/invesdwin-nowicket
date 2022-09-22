package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible;

import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.jquery.util.Strings2;
import de.invesdwin.nowicket.component.pnotify.PNotifyBehavior;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.DefaultSubmitButtonCallback;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.AModelAjaxFallbackLink;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class ModelCollapsible extends Panel {

    public static final String COLLAPSIBLE_WICKET_ID = "collapsible";

    protected ITab tab;
    protected IModel<Object> panelModel;

    private final PNotifyBehavior validationErrorNotificationBehavior;
    private final Component collapsible;
    private boolean active;
    private boolean renderedActive;

    public ModelCollapsible(final String id, final ITab tab) {
        super(id);
        this.tab = tab;
        this.validationErrorNotificationBehavior = createValidationErrorNotificationBehavior();

        final Component title = newTitle("title", tab);
        this.collapsible = newCollapsible(COLLAPSIBLE_WICKET_ID, tab);

        add(title);
        add(collapsible);

        setOutputMarkupId(true);
    }

    @Override
    public boolean isEnabled() {
        //always true to keep modals from being disabled by hierarhcy
        return true;
    }

    public ITab getTab() {
        return tab;
    }

    @Override
    public boolean isVisible() {
        return tab.isVisible();
    }

    protected PNotifyBehavior createValidationErrorNotificationBehavior() {
        return DefaultSubmitButtonCallback.newValidationErrorNotificationBehavior();
    }

    protected Component newCollapsible(final String markupId, final ITab tab) {
        final WebMarkupContainer container = new WebMarkupContainer(markupId) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                if (renderedActive) {
                    Attributes.addClass(tag, "show");
                }
                renderedActive = isActive();
            }

        };
        container.setOutputMarkupId(true);
        final WebMarkupContainer panel = tab.getPanel("content");
        this.panelModel = (IModel<Object>) panel.getDefaultModel();
        container.add(panel);
        return container;
    }

    public IModel<Object> getPanelModel() {
        return panelModel;
    }

    protected Component newTitle(final String markupId, final ITab tab) {
        final TabSubmitAjaxFallbackLink link = new TabSubmitAjaxFallbackLink(markupId, tab);
        return link;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        if (!isActive() && hasInvalidChildren()) {
            //always show if this is the cause of the validation error
            setActive(true);
        }
        if (!renderedActive && isActive()) {
            response.render(OnDomReadyHeaderItem.forScript(createShowScript()));
        } else if (renderedActive && !isActive()) {
            response.render(OnDomReadyHeaderItem.forScript(createHideScript()));
        }
    }

    private boolean hasInvalidChildren() {
        return Components.hasInvalidChildren(this);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().remove("class");
        super.onComponentTag(tag);
    }

    public class TabSubmitAjaxFallbackLink extends AModelAjaxFallbackLink {

        public TabSubmitAjaxFallbackLink(final String id, final ITab tab) {
            super(id);
            setBody(tab.getTitle());
            setEscapeModelStrings(false);
            add(AttributeModifier.append("class", new IModel<String>() {
                @Override
                public String getObject() {
                    if (!isActive()) {
                        return "collapsed";
                    } else {
                        return null;
                    }
                }
            }));
        }

        @Override
        protected AjaxEventBehavior newAjaxEventBehavior(final String event) {
            return new AjaxFormSubmitBehavior(event) {

                @Override
                protected void onSubmit(final AjaxRequestTarget target) {
                    try {
                        super.onSubmit(target);
                        onClick(Optional.ofNullable(target));
                    } finally {
                        GuiService.get().processRequestFinally(ModelCollapsible.this);
                    }
                }

                @Override
                protected void onError(final AjaxRequestTarget target) {
                    try {
                        super.onError(target);
                        if (!isActive() && hasInvalidChildren()) {
                            //always show if this is the cause of the validation error
                            setActive(true);
                        } else {
                            //allow toggling if this is not the cause of the validation error
                            onClick(Optional.ofNullable(target));
                        }
                        if (validationErrorNotificationBehavior != null) {
                            ModelCollapsible.this.add(validationErrorNotificationBehavior);
                        }
                    } finally {
                        GuiService.get().processRequestFinally(ModelCollapsible.this);
                    }
                }

                @Override
                protected void onComponentTag(final ComponentTag tag) {
                    // only render handler if link is enabled
                    if (isEnabledInHierarchy()) {
                        super.onComponentTag(tag);
                    }
                }

                @Override
                protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
                    super.updateAjaxAttributes(attributes);
                    attributes.setPreventDefault(true);
                    TabSubmitAjaxFallbackLink.this.updateAjaxAttributes(attributes);
                }
            };
        }

        @Override
        public void onClick(final Optional<AjaxRequestTarget> target) {
            setActive(!isActive());
        }

    };

    private CharSequence createShowScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("$('#");
        sb.append(Strings2.getMarkupId(collapsible));
        sb.append("').collapse('show')");
        return sb;
    }

    private CharSequence createHideScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("$('#");
        sb.append(Strings2.getMarkupId(collapsible));
        sb.append("').collapse('hide')");
        return sb;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
