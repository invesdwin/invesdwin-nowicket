package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible.accordion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelTabUpdatingReuseIfModelsEqualStrategy;
import de.invesdwin.util.collections.iterable.ATransformingIterable;
import de.invesdwin.util.collections.iterable.ICloseableIterable;
import de.invesdwin.util.collections.iterable.ICloseableIterator;
import de.invesdwin.util.collections.iterable.WrapperCloseableIterator;

/**
 * Accordion is a panel group of collapsibles where only one is allowed to be open.
 *
 */
@NotThreadSafe
public class ModelAccordion extends Panel {

    private static final String COLLAPSIBLE_ID = "tab";
    private Integer activeIndex;
    private final RefreshingView<ITab> refreshingView;
    private transient ICloseableIterable<Collapsible> collapsibles;

    public ModelAccordion(final ITabbedHtmlElement<?, ?> element) {
        this(element.getWicketId(), element.getTabModel());
    }

    public ModelAccordion(final String id, final IModel<? extends Collection<? extends ITab>> tabs) {
        super(id);

        this.refreshingView = new RefreshingView<ITab>("tabs") {

            {
                setRenderBodyOnly(true);
                setItemReuseStrategy(ModelTabUpdatingReuseIfModelsEqualStrategy.getInstance());
            }

            @Override
            protected Iterator<IModel<ITab>> getItemModels() {
                final List<IModel<ITab>> models = new ArrayList<IModel<ITab>>();
                for (final ITab tab : tabs.getObject()) {
                    if (tab.isVisible()) {
                        models.add(Model.of(tab));
                    }
                }
                return models.iterator();
            }

            @Override
            protected void populateItem(final Item<ITab> item) {
                final ITab tab = item.getModelObject();
                final int index = item.getIndex();
                final Collapsible collapsible = newAccordionCollapsible(COLLAPSIBLE_ID, tab, index);
                item.add(collapsible);
                item.setRenderBodyOnly(true);
            }

        };
        add(refreshingView);
    }

    private ICloseableIterable<Collapsible> newCollapsibles() {
        final ICloseableIterable<Item<ITab>> items = new ICloseableIterable<Item<ITab>>() {
            @SuppressWarnings("deprecation")
            @Override
            public ICloseableIterator<Item<ITab>> iterator() {
                return WrapperCloseableIterator.maybeWrap(refreshingView.getItems());
            }
        };
        return new ATransformingIterable<Item<ITab>, Collapsible>(items) {
            @Override
            protected Collapsible transform(final Item<ITab> value) {
                final Collapsible tab = (Collapsible) value.get(COLLAPSIBLE_ID);
                if (tab == null) {
                    throw new IllegalStateException(COLLAPSIBLE_ID + " should not be null");
                }
                return tab;
            }
        };
    }

    protected Collapsible newAccordionCollapsible(final String componentId, final ITab tab, final int index) {
        return new Collapsible(componentId, tab, index);
    }

    @Override
    public boolean isEnabled() {
        //always true to keep modals from being disabled by hierarhcy
        return true;
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().remove("class");
        super.onComponentTag(tag);
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(final Integer activeIndex) {
        final Integer prevActiveIndex = this.activeIndex;
        this.activeIndex = activeIndex;
    }

    public Iterable<Collapsible> getCollapsibles() {
        if (collapsibles == null) {
            collapsibles = newCollapsibles();
        }
        return collapsibles;
    }

    public class Collapsible extends ModelAccordionItem {

        private final int index;

        public Collapsible(final String id, final ITab tab, final int index) {
            super(id, tab);
            this.index = index;
            setRenderBodyOnly(true);
        }

        @Override
        public boolean isActive() {
            final Integer activeIndex = getActiveIndex();
            return activeIndex != null && activeIndex == index;
        }

        @Override
        public boolean isVisible() {
            /*
             * need to make this always visible to prevent rendering exceptions; the getItemModels will be responsible
             * for hiding collapsibles
             */
            return true;
        }

        @Override
        public void setActive(final boolean active) {
            final boolean prevActive = isActive();
            super.setActive(active);
            if (!prevActive && active) {
                setActiveIndex(index);
            } else if (prevActive && !active) {
                setActiveIndex(null);
            }
        }
    }

}
