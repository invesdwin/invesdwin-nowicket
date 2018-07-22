package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.collapsible;

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
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.AModelTab;
import de.invesdwin.util.lang.Objects;

/**
 * Accordion is a panel group of collapsibles where only one is allowed to be open.
 *
 */
@NotThreadSafe
public class ModelAccordion extends Panel {

    private Integer activeIndex;
    private final List<AccordionCollapsible> oldCollapsibles = new ArrayList<AccordionCollapsible>();
    private final List<AccordionCollapsible> collapsibles = new ArrayList<AccordionCollapsible>();

    public ModelAccordion(final ITabbedHtmlElement<?, ?> element) {
        this(element.getWicketId(), element.getTabModel());
    }

    public ModelAccordion(final String id, final IModel<? extends Collection<? extends ITab>> tabs) {
        super(id);

        add(new RefreshingView<ITab>("tabs") {

            {
                setRenderBodyOnly(true);
                //cannot use item reuse strategy here since we need to extract the model from the tab instance
            }

            @Override
            protected Iterator<IModel<ITab>> getItemModels() {
                final List<IModel<ITab>> models = new ArrayList<IModel<ITab>>();
                for (final ITab tab : tabs.getObject()) {
                    if (tab.isVisible()) {
                        models.add(Model.of(tab));
                    }
                }
                restoreActiveIndexOnChangingList(models);
                oldCollapsibles.clear();
                oldCollapsibles.addAll(collapsibles);
                collapsibles.clear();
                return models.iterator();
            }

            private void restoreActiveIndexOnChangingList(final List<IModel<ITab>> models) {
                if (activeIndex != null && !collapsibles.isEmpty()) {
                    Integer newActiveIndex = null;
                    int curIndex = 0;
                    final Object activeIndexObject = collapsibles.get(activeIndex)
                            .getTab()
                            .getPanel(AModelTab.DUMMY_CONTAINER_ID)
                            .getDefaultModelObject();
                    for (final IModel<ITab> model : models) {
                        final Object newActiveIndexObject = model.getObject()
                                .getPanel(AModelTab.DUMMY_CONTAINER_ID)
                                .getDefaultModelObject();
                        if (Objects.equals(activeIndexObject, newActiveIndexObject)) {
                            newActiveIndex = curIndex;
                            break;
                        }
                        curIndex++;
                    }
                    activeIndex = newActiveIndex;
                }
            }

            @Override
            protected void populateItem(final Item<ITab> item) {
                final ITab tab = item.getModelObject();
                final int index = item.getIndex();
                final AccordionCollapsible collapsible = newCollapsible(tab, index);
                collapsible.updateActiveState();
                collapsibles.add(collapsible);
                item.add(collapsible);
                item.setRenderBodyOnly(true);
            }

            private AccordionCollapsible newCollapsible(final ITab tab, final int index) {
                for (final AccordionCollapsible oldCollapsible : oldCollapsibles) {
                    final Object oldModelObject = oldCollapsible.getTab()
                            .getPanel(AModelTab.DUMMY_CONTAINER_ID)
                            .getDefaultModelObject();
                    final IModel<?> newModel = tab.getPanel(AModelTab.DUMMY_CONTAINER_ID).getDefaultModel();
                    final Object newModelObject = newModel.getObject();
                    if (Objects.equals(oldModelObject, newModelObject)) {
                        //update model object to detect changes
                        oldCollapsible.updateModel(index, tab, newModelObject);
                        return oldCollapsible;
                    }
                }
                return newAccordionCollapsible("tab", tab, index);
            }

        });
    }

    protected AccordionCollapsible newAccordionCollapsible(final String componentId, final ITab tab, final int index) {
        return new AccordionCollapsible(componentId, tab, index);
    }

    @Override
    public boolean isEnabled() {
        //always true to keep modals from being disabled by hierarhcy
        return true;
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        super.onComponentTag(tag);
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(final Integer activeIndex) {
        final Integer prevActiveIndex = this.activeIndex;
        this.activeIndex = activeIndex;
        if (!Objects.equals(prevActiveIndex, activeIndex)) {
            updateActiveStates();
        }
    }

    private void updateActiveStates() {
        for (final AccordionCollapsible collapsible : collapsibles) {
            collapsible.updateActiveState();
        }
    }

    public class AccordionCollapsible extends ModelCollapsible {

        private int index;

        public AccordionCollapsible(final String id, final ITab tab, final int index) {
            super(id, tab);
            this.index = index;
            setRenderBodyOnly(true);
        }

        public void updateModel(final int newIndex, final ITab newTab, final Object newModelObject) {
            this.index = newIndex;
            this.tab = newTab;
            this.panelModel.setObject(newModelObject);
        }

        public void updateActiveState() {
            final Integer curActiveIndex = getActiveIndex();
            super.setActive(curActiveIndex != null && curActiveIndex == index);
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
