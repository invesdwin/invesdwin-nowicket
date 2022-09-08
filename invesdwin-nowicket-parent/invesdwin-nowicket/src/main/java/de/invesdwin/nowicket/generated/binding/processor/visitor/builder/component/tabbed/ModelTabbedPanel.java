package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
import org.apache.wicket.markup.repeater.IItemFactory;
import org.apache.wicket.markup.repeater.IItemReuseStrategy;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.invesdwin.nowicket.component.toastr.ToastrBehavior;
import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.DefaultSubmitButtonCallback;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.AModelAjaxFallbackLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.AModelTab;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelDelegateTab;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelTab;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelTabUpdatingReuseIfModelsEqualStrategy;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.collections.delegate.DelegateList;
import de.invesdwin.util.collections.iterable.ATransformingIterator;
import de.invesdwin.util.collections.iterable.EmptyCloseableIterator;
import de.invesdwin.util.collections.iterable.WrapperCloseableIterable;
import de.invesdwin.util.lang.reflection.Reflections;

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

    private final ToastrBehavior validationErrorNotificationBehavior;
    private final RefreshingDelegateList refreshingTabs;

    public ModelTabbedPanel(final ITabbedHtmlElement<?, ?> element) {
        this(element.getWicketId(), element.createWicketTabs(), Model.of(0));
    }

    public ModelTabbedPanel(final String id, final List<ITab> tabs, final Model<Integer> model) {
        super(id, new RefreshingDelegateList(tabs), model);
        this.refreshingTabs = (RefreshingDelegateList) getTabs();
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

    protected ToastrBehavior createValidationErrorNotificationBehavior() {
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
        final SubmitAjaxFallbackLink link = new SubmitAjaxFallbackLink(linkId, index);
        addDisabledTabBehavior(link, index);
        return addSelectedTabBehavior(link, index);
    }

    @Override
    protected LoopItem newTabContainer(final int tabIndex) {
        final LoopItem item = super.newTabContainer(tabIndex);
        return addDisabledTabBehavior(item, tabIndex);
    }

    private <T extends WebMarkupContainer> T addDisabledTabBehavior(final T link, final int tabIndex) {
        link.add(new Behavior() {
            @Override
            public void onComponentTag(final Component component, final ComponentTag tag) {
                super.onComponentTag(component, tag);
                if (!isSuperEnabled() || !isTabEnabled(tabIndex)) {
                    tag.put("class", tag.getAttribute("class") + " disabled");
                }
            }
        });
        return link;
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
                        onClick(Optional.ofNullable(target));
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
        public void onClick(final Optional<AjaxRequestTarget> target) {
            setSelectedTab(index);
            if (target.isPresent()) {
                target.get().add(ModelTabbedPanel.this);
            }
            onAjaxUpdate(target);
        }

    };

    private static class RefreshingDelegateList extends DelegateList<ITab> {

        private static final IItemFactory<ITab> ITEM_FACTORY = new IItemFactory<ITab>() {
            @Override
            public Item<ITab> newItem(final int index, final IModel<ITab> model) {
                return new Item<ITab>(AModelTab.DUMMY_CONTAINER_ID, index, model);
            }
        };
        private final List<ITab> delegate;
        private List<ITab> delegateCopy;
        private final IItemReuseStrategy reuseStrategy = ModelTabUpdatingReuseIfModelsEqualStrategy.getInstance();

        RefreshingDelegateList(final List<ITab> delegate) {
            super(null);
            this.delegate = delegate;
            refresh();
        }

        public boolean isVisible() {
            return !delegate.isEmpty();
        }

        @Override
        public List<ITab> getDelegate() {
            return delegateCopy;
        }

        private void refresh() {
            final Iterator<IModel<ITab>> newModels = newModels(delegate);
            final Iterator<Item<ITab>> existingItems = existingItems(delegateCopy);
            final Iterator<Item<ITab>> items = reuseStrategy.getItems(ITEM_FACTORY, newModels, existingItems);
            delegateCopy = new ArrayList<>(delegate.size());
            while (items.hasNext()) {
                final Item<ITab> item = items.next();
                delegateCopy.add(item.getModelObject());
            }
        }

        private Iterator<Item<ITab>> existingItems(final List<ITab> tabs) {
            if (tabs == null || tabs.isEmpty()) {
                return EmptyCloseableIterator.getInstance();
            }
            return new ATransformingIterator<ITab, Item<ITab>>(WrapperCloseableIterable.maybeWrap(tabs).iterator()) {
                private int index = 0;

                @Override
                protected Item<ITab> transform(final ITab value) {
                    return ITEM_FACTORY.newItem(index++, newModel(value));
                }
            };
        }

        private Iterator<IModel<ITab>> newModels(final List<ITab> tabs) {
            return new ATransformingIterator<ITab, IModel<ITab>>(WrapperCloseableIterable.maybeWrap(tabs).iterator()) {
                @Override
                protected IModel<ITab> transform(final ITab value) {
                    return newModel(value);
                }
            };
        }

        private IModel<ITab> newModel(final ITab value) {
            final ITab tab = ModelDelegateTab.valueOf(value);
            return Model.of(tab);
        }
    }

}
