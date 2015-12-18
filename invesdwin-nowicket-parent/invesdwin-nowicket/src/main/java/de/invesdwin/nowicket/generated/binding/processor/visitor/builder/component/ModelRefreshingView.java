package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.io.Serializable;
import java.util.Iterator;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.application.PanelFactory;
import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;

@NotThreadSafe
public class ModelRefreshingView extends Panel {

    public ModelRefreshingView(final AChoiceHtmlElement<?> element) {
        super(element.getWicketId());
        setRenderBodyOnly(true);
        add(new RefreshingView<Object>("panels") {

            {
                setItemReuseStrategy(new ReuseIfModelsEqualStrategy());
            }

            @Override
            protected Iterator<IModel<Object>> getItemModels() {
                return new Iterator<IModel<Object>>() {

                    private final Iterator<Object> delegate = element.getChoiceModel().getObject().iterator();

                    @Override
                    public boolean hasNext() {
                        return delegate.hasNext();
                    }

                    @SuppressWarnings({ "rawtypes", "unchecked" })
                    @Override
                    public IModel<Object> next() {
                        final Object next = delegate.next();
                        final IModel<Object> model = (IModel) Model.of((Serializable) next);
                        return model;
                    }

                    @Override
                    public void remove() {
                        delegate.remove();
                    }

                };
            }

            @Override
            protected void populateItem(final Item<Object> item) {
                final Panel panel = PanelFactory.get().getPanel("panel", item.getModelObject());
                item.add(panel);
            }
        });
    }

    @Override
    public boolean isEnabled() {
        //always true to keep modals from being disabled by hierarchy
        return true;
    }

}
