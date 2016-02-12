package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.TitleModel;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

@NotThreadSafe
public abstract class AModelSuffixHtmlElement extends AModelHtmlElement<IModelElement<?>, Void> {

    private transient IModelElement<?> modelElement;

    public AModelSuffixHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    /**
     * This is only half a model element in fact.
     */
    @Override
    public boolean isModelElement() {
        return false;
    }

    protected abstract String getModelWicketId();

    @Override
    public IModelElement<?> getModelElement() {
        if (modelElement == null) {
            modelElement = getContext().getModelObjectContext().getElementRegistry().getElement(getModelWicketId());
        }
        return modelElement;
    }

    @Override
    public IModel<String> getTitleModel() {
        return new TitleModel(getModelWicketId(), this);
    }

    @Deprecated
    @Override
    public IModel<Void> getModel() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public boolean isEager() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public boolean isForced() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public boolean isModalCloser() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<String> getTooltipModel(final IModel<Object> targetModel) {
        throw new UnsupportedOperationException();
    }

}
