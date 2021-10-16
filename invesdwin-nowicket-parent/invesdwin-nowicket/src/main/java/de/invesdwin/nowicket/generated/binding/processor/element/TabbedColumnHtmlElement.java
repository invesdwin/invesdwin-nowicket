package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import de.invesdwin.norva.beanpath.impl.clazz.BeanClassContainer;
import de.invesdwin.norva.beanpath.spi.IBeanPathContainer;
import de.invesdwin.norva.beanpath.spi.element.TabbedColumnBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelTab;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedColumnModelElement;

@NotThreadSafe
public class TabbedColumnHtmlElement extends AModelHtmlElement<TabbedColumnModelElement, Object> {

    private IModel<Object> tableObjectModel;

    public TabbedColumnHtmlElement(final HtmlContext context, final TabbedColumnModelElement modelElement) {
        super(context, modelElement.getWicketId());
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    public ITab createWicketTab() {
        //cannot be delegated to BindingBuilder since it might be required in a model that gets refreshed each request cycle
        return new ModelTab(this, getTitleModel(), getModel(), getTableObjectModel(), getTargetObjectModel());
    }

    @Override
    protected boolean isHiddenByModalContainer() {
        //never hidden, or else modals will be disabled all the time
        return false;
    }

    protected IModel<Object> getTableObjectModel() {
        if (tableObjectModel == null) {
            tableObjectModel = new LoadableDetachableModel<Object>() {
                @Override
                protected Object load() {
                    final Object rootObject = getContext().getModelObjectContext().getModelObject();
                    final TabbedColumnBeanPathElement element = getModelElement().getBeanPathElement();
                    final IBeanPathContainer tableContainer = element.getContainer();
                    final BeanClassContainer container = tableContainer.unwrap(BeanClassContainer.class);
                    return container.getTargetFromRoot(rootObject);
                }
            };
        }
        return tableObjectModel;
    }

}
