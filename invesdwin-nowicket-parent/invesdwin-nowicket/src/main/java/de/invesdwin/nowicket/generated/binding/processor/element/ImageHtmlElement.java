package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.UrlAnchorModel;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorType;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

@NotThreadSafe
public class ImageHtmlElement extends AModelHtmlElement<IModelElement<?>, Object> {

    public ImageHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public IModel<Object> getModel() {
        switch (getType()) {
        case URL:
        case RESOURCE_REFERENCE:
            return (IModel) new UrlAnchorModel(this);
        default:
            return super.getModel();
        }
    }

    public AnchorType getType() {
        return AnchorType.valueOf(getModelElement().getBeanPathElement());
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitImage(this);
    }

}
