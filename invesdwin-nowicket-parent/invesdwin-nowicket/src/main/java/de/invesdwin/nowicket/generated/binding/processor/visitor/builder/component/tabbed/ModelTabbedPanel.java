package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.invesdwin.nowicket.component.pnotify.PNotifyBehavior;
import de.invesdwin.nowicket.generated.binding.processor.element.TabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.DefaultSubmitButtonCallback;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.AModelAjaxFallbackLink;
import de.invesdwin.nowicket.generated.guiservice.GuiService;

@NotThreadSafe
public class ModelTabbedPanel extends AjaxBootstrapTabbedPanel<ITab> {

    private final PNotifyBehavior validationErrorNotificationBehavior;

    public ModelTabbedPanel(final TabbedHtmlElement element) {
        this(element.getWicketId(), element.createWicketTabs(), Model.of(0));
    }

    public ModelTabbedPanel(final String id, final List<ITab> tabs, final Model<Integer> model) {
        super(id, tabs, model);
        this.validationErrorNotificationBehavior = createValidationErrorNotificationBehavior();
    }

    protected PNotifyBehavior createValidationErrorNotificationBehavior() {
        return DefaultSubmitButtonCallback.newValidationErrorNotificationBehavior();
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        super.onComponentTag(tag);
    }

    @Override
    public boolean isEnabled() {
        //always true to keep modals from being disabled by hierarhcy
        return true;
    }

    /**
     * to disable the links when the super component got disabled
     */
    protected boolean isSuperEnabled() {
        return super.isEnabled();
    }

    /**
     * make click on tab submit form so no invalid tab can be left!
     */
    @Override
    protected WebMarkupContainer newLink(final String linkId, final int index) {
        return new SubmitAjaxFallbackLink(linkId, index);
    }

    @Override
    protected LoopItem newTabContainer(final int tabIndex) {
        final LoopItem item = super.newTabContainer(tabIndex);
        item.add(new Behavior() {
            @Override
            public void onComponentTag(final Component component, final ComponentTag tag) {
                super.onComponentTag(component, tag);
                if (!isSuperEnabled()) {
                    tag.put("class", tag.getAttribute("class") + " disabled");
                }
            }
        });
        return item;
    }

    private class SubmitAjaxFallbackLink extends AModelAjaxFallbackLink {

        private final int index;

        SubmitAjaxFallbackLink(final String id, final int index) {
            super(id);
            this.index = index;
        }

        @Override
        public boolean isEnabled() {
            return ModelTabbedPanel.this.isSuperEnabled();
        }

        @Override
        protected AjaxEventBehavior newAjaxEventBehavior(final String event) {
            return new AjaxFormSubmitBehavior(event) {

                @Override
                protected void onSubmit(final AjaxRequestTarget target) {
                    try {
                        super.onSubmit(target);
                        onClick(target);
                    } finally {
                        GuiService.get().processRequestFinally(ModelTabbedPanel.this);
                    }
                }

                @Override
                protected void onError(final AjaxRequestTarget target) {
                    try {
                        super.onError(target);
                        if (validationErrorNotificationBehavior != null) {
                            ModelTabbedPanel.this.add(validationErrorNotificationBehavior);
                        }
                    } finally {
                        GuiService.get().processRequestFinally(ModelTabbedPanel.this);
                    }
                }

                /**
                 * 
                 * @see org.apache.wicket.ajax.AjaxEventBehavior#onComponentTag(org.apache.wicket.markup.ComponentTag)
                 */
                @Override
                protected void onComponentTag(final ComponentTag tag) {
                    // only render handler if link is enabled
                    if (isEnabledInHierarchy()) {
                        super.onComponentTag(tag);
                    }
                }

                @Override
                public boolean isEnabled(final Component component) {
                    //active tab should not be clickable
                    return super.isEnabled(component) && !isSelectedTab();
                }

                @Override
                protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
                    super.updateAjaxAttributes(attributes);
                    attributes.setPreventDefault(true);
                    SubmitAjaxFallbackLink.this.updateAjaxAttributes(attributes);
                }
            };
        }

        private boolean isSelectedTab() {
            return getSelectedTab() == index;
        }

        @Override
        protected void onComponentTag(final ComponentTag tag) {
            super.onComponentTag(tag);
            if (isSelectedTab()) {
                //active tab should not be clickable
                tag.remove("href");
            }
        }

        @Override
        public void onClick(final AjaxRequestTarget target) {
            setSelectedTab(index);
            if (target != null) {
                target.add(ModelTabbedPanel.this);
            }
            onAjaxUpdate(target);
        }

    };

}
