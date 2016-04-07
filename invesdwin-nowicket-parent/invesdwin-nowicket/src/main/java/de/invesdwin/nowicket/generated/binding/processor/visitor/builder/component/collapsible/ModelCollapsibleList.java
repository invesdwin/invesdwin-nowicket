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
import de.invesdwin.util.lang.Objects;

/**
 * A panel group of collapsible where all are allowed to be open at the same time.
 *
 */
@NotThreadSafe
public class ModelCollapsibleList extends Panel {

    private final List<AccordionCollapsible> oldCollapsibles = new ArrayList<AccordionCollapsible>();
    private final List<AccordionCollapsible> collapsibles = new ArrayList<AccordionCollapsible>();

    public ModelCollapsibleList(final ITabbedHtmlElement<?, ?> element) {
        this(element.getWicketId(), element.getTabModel());
    }

    public ModelCollapsibleList(final String id, final IModel<? extends Collection<? extends ITab>> tabs) {
        super(id, tabs);

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
                oldCollapsibles.clear();
                oldCollapsibles.addAll(collapsibles);
                collapsibles.clear();
                return models.iterator();
            }

            @Override
            protected void populateItem(final Item<ITab> item) {
                final ITab tab = item.getModelObject();
                final AccordionCollapsible collapsible = newCollapsible(tab);
                item.add(collapsible);
                collapsibles.add(collapsible);
                item.setRenderBodyOnly(true);
            }

            private AccordionCollapsible newCollapsible(final ITab tab) {
                for (final AccordionCollapsible oldCollapsible : oldCollapsibles) {
                    final Object oldModelObject = oldCollapsible.getTab()
                            .getPanel(AccordionCollapsible.COLLAPSIBLE_WICKET_ID)
                            .getDefaultModelObject();
                    final IModel<?> newModel = tab.getPanel(AccordionCollapsible.COLLAPSIBLE_WICKET_ID)
                            .getDefaultModel();
                    final Object newModelObject = newModel.getObject();
                    if (Objects.equals(oldModelObject, newModelObject)) {
                        //update model object to detect changes
                        oldCollapsible.updateModel(tab, newModelObject);
                        return oldCollapsible;
                    }
                }
                return newAccordionCollapsible("tab", tab);
            }

        });
    }

    protected AccordionCollapsible newAccordionCollapsible(final String componentId, final ITab tab) {
        return new AccordionCollapsible(componentId, tab);
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

    public List<AccordionCollapsible> getCollapsibles() {
        return collapsibles;
    }

    public class AccordionCollapsible extends ModelCollapsible {

        public AccordionCollapsible(final String id, final ITab tab) {
            super(id, tab);
            setRenderBodyOnly(true);
        }

        public void updateModel(final ITab newTab, final Object newModelObject) {
            this.tab = newTab;
            this.panelModel.setObject(newModelObject);
        }

        @Override
        public boolean isVisible() {
            /*
             * need to make this always visible to prevent rendering exceptions; the getItemModels will be responsible
             * for hiding collapsibles
             */
            return true;
        }

    }

}
