package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

@NotThreadSafe
public abstract class ANoModelHtmlElement extends AHtmlElement<IModelElement<?>, Void> {

    public ANoModelHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public boolean isModelElement() {
        return false;
    }

    @Override
    protected boolean shouldBeAddedToElementRegistry() {
        return true;
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
    public boolean isModalOpener() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<String> getTitleModel() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<String> getTitleModelFromTarget(final IModel<Object> targetObjectModel) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<Void> getModel() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<Object> getRootObjectModel() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<Object> getTargetObjectModel() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModelElement<?> getModelElement() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public IModel<String> getTooltipModelFromTarget(final IModel<Object> rootModel, final IModel<Object> targetModel) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public boolean isEnabledFromTarget(final IModel<Object> rootModel, final IModel<Object> targetObjectModel) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public boolean isVisibleFromTarget(final IModel<Object> rootModel, final IModel<Object> targetObjectModel) {
        throw new UnsupportedOperationException();
    }

}
