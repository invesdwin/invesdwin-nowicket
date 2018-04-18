package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.invesdwin.nowicket.component.pnotify.PNotifyBehavior;
import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.DefaultSubmitButtonCallback;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.AModelAjaxFallbackLink;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.collections.delegate.DelegateList;
import de.invesdwin.util.lang.Reflections;

@NotThreadSafe
public class ModelTabbedPanel extends AjaxBootstrapTabbedPanel<ITab> {

    private static final MethodHandle VISIBILITY_CACHE_FIELD_SETTER;

    static {
        final Field visibilityCacheField = Reflections.findField(TabbedPanel.class, "visibilityCache");
        Reflections.makeAccessible(visibilityCacheField);
        try {
            VISIBILITY_CACHE_FIELD_SETTER = MethodHandles.lookup().unreflectSetter(visibilityCacheField);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private final PNotifyBehavior validationErrorNotificationBehavior;
    private final RefreshingDelegateList<ITab> refreshingTabs;

    public ModelTabbedPanel(final ITabbedHtmlElement<?, ?> element) {
        this(element.getWicketId(), element.createWicketTabs(), Model.of(0));
    }

    public ModelTabbedPanel(final String id, final List<ITab> tabs, final Model<Integer> model) {
        super(id, new RefreshingDelegateList<ITab>(tabs), model);
        this.refreshingTabs = (RefreshingDelegateList<ITab>) getTabs();
        this.validationErrorNotificationBehavior = createValidationErrorNotificationBehavior();
    }

    @Override
    protected void onConfigure() {
        try {
            //refresh the tabs only here to prevent race conditions during render
            refreshingTabs.refresh();
            /*
             * reset visibility cache since tabs might have changed significantly since last render, we don't want to
             * have wrong visibility and we don't want to get IndexOutOfBoundsExceptions
             */
            VISIBILITY_CACHE_FIELD_SETTER.invoke(this, null);
            int selectedTab = getSelectedTab();
            if (selectedTab < 0) {
                selectedTab = 1;
            } else if (selectedTab >= getTabs().size()) {
                selectedTab = getTabs().size() - 1;
            }
            //update the visible panel, the tab could have been replaced
            setSelectedTab(selectedTab);
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
        super.onConfigure();
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
        //always true to prevent modals from being disabled by hierarchy
        return true;
    }

    @Override
    public TabbedPanel<ITab> setSelectedTab(final int index) {
        if (isTabVisible(index)) {
            //only switch if visible, this prevents an empty tab to be seen
            return super.setSelectedTab(index);
        } else {
            return super.setSelectedTab(getSelectedTab());
        }
    }

    /**
     * to disable the links when the super component got disabled
     */
    protected boolean isSuperEnabled() {
        return super.isEnabled();
    }

    @Override
    public boolean isVisible() {
        if (!refreshingTabs.isVisible()) {
            //fix ArrayIndexOutOfBoundsException when tabs are empty
            return false;
        } else {
            return super.isVisible();
        }
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
                if (!isSuperEnabled() || !isTabEnabled(tabIndex)) {
                    tag.put("class", tag.getAttribute("class") + " disabled");
                }
            }
        });
        return item;
    }

    private boolean isTabEnabled(final int index) {
        final ITab tab = getTabs().get(index);
        if (tab instanceof ModelTab) {
            final ModelTab modelTab = (ModelTab) tab;
            if (!modelTab.isEnabled()) {
                return false;
            }
        }
        //fallback to something
        return tab.isVisible();
    }

    private boolean isTabVisible(final int index) {
        final ITab tab = getTabs().get(index);
        return tab.isVisible();
    }

    private class SubmitAjaxFallbackLink extends AModelAjaxFallbackLink {

        private final int index;
        /**
         * If we are too dynamic with this, we might get permission denied response sometimes, thus only update in
         * onConfigure() once
         */
        private boolean isTabEnabledFromOnConfigure;

        SubmitAjaxFallbackLink(final String id, final int index) {
            super(id);
            this.index = index;
        }

        @Override
        protected void onConfigure() {
            super.onConfigure();
            this.isTabEnabledFromOnConfigure = ModelTabbedPanel.this.isTabEnabled(index);
        }

        @Override
        public boolean isEnabled() {
            return ModelTabbedPanel.this.isSuperEnabled() && isTabEnabledFromOnConfigure;
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
                    return super.isEnabled(component) && !isSelectedTab() && isTabEnabledFromOnConfigure;
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
            if (isSelectedTab() || !isEnabled()) {
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

    private static class RefreshingDelegateList<E> extends DelegateList<E> {

        private final List<E> delegate;
        private List<E> delegateCopy;

        RefreshingDelegateList(final List<E> delegate) {
            super(null);
            this.delegate = delegate;
            refresh();
        }

        public boolean isVisible() {
            return !delegate.isEmpty();
        }

        @Override
        public List<E> getDelegate() {
            return delegateCopy;
        }

        private void refresh() {
            delegateCopy = new ArrayList<>(delegate);
        }

    }

}
