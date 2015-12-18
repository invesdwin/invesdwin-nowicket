package de.invesdwin.nowicket.generated.binding.processor.element;

import java.io.File;
import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.FileAnchorModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.UrlAnchorModel;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorType;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.util.error.UnknownArgumentException;

@NotThreadSafe
public class AnchorHtmlElement extends AModelHtmlElement<AnchorModelElement, Object> {

    public static final String TARGET_BLANK = "_blank";

    private transient AnchorModelElement anchorModelElement;

    public AnchorHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public AnchorModelElement getModelElement() {
        if (anchorModelElement == null) {
            final IModelElement<?> modelElement = super.getModelElement();
            if (modelElement instanceof AnchorModelElement) {
                anchorModelElement = (AnchorModelElement) modelElement;
            } else {
                anchorModelElement = new AnchorModelElement(modelElement);
            }
        }
        return anchorModelElement;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IModel<Object> getModel() {
        final AnchorType type = getType();
        if (type == null) {
            //maybe interceptor tries to bind this model somewhere useful
            return super.getModel();
        }
        switch (type) {
        case URL:
            return (IModel) getUrlModel();
        case FILE:
            return (IModel) getFileModel();
        case RESOURCE:
            return (IModel) getResourceModel();
        case RESOURCE_REFERENCE:
            return (IModel) getResourceReferenceModel();
        default:
            throw UnknownArgumentException.newInstance(AnchorType.class, type);
        }
    }

    public IModel<String> getUrlModel() {
        if (getType() == AnchorType.URL) {
            return new UrlAnchorModel(this);
        } else {
            return null;
        }
    }

    public IModel<File> getFileModel() {
        if (getType() == AnchorType.FILE) {
            return new FileAnchorModel(this);
        } else {
            return null;
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public IModel<IResource> getResourceModel() {
        if (getType() == AnchorType.RESOURCE) {
            return (IModel) super.getModel();
        } else {
            return null;
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public IModel<ResourceReference> getResourceReferenceModel() {
        if (getType() == AnchorType.RESOURCE_REFERENCE) {
            return (IModel) super.getModel();
        } else {
            return null;
        }
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitAnchor(this);
    }

    public AnchorType getType() {
        return getModelElement().getType();
    }

    public Component createWicketAnchor() {
        final AnchorType type = getType();
        if (type == null) {
            return null;
        }
        switch (type) {
        case URL:
            return getContext().getBindingBuilder().createUrlAnchor(this);
        case FILE:
            return getContext().getBindingBuilder().createFileAnchor(this);
        case RESOURCE:
            return getContext().getBindingBuilder().createResourceAnchor(this);
        case RESOURCE_REFERENCE:
            return getContext().getBindingBuilder().createResourceReferenceAnchor(this);
        default:
            throw UnknownArgumentException.newInstance(AnchorType.class, getType());
        }
    }

}
