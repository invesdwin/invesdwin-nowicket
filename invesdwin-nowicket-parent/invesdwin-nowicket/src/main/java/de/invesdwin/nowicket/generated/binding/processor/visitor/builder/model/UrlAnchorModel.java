package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.io.File;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.UrlUtils;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class UrlAnchorModel extends AbstractReadOnlyModel<String> {

    private final IHtmlElement<?, ?> element;
    private final IModel<Object> delegate;
    private final IModel<?> targetObjectModel;

    public UrlAnchorModel(final IHtmlElement<?, ?> element) {
        this.element = element;
        this.targetObjectModel = element.getTargetObjectModel();
        //makes getter work for an action accessor; dunno why BeanPathModel does not work here...
        this.delegate = new PropertyModel<Object>(element.getRootObjectModel().getObject(), element.getWicketId());
    }

    public UrlAnchorModel(final IHtmlElement<?, ?> element, final IModel<?> rootObjectModel, final String beanPath) {
        this.element = element;
        this.targetObjectModel = rootObjectModel;
        this.delegate = new BeanPathModel<Object>(rootObjectModel, beanPath);
    }

    @Override
    public String getObject() {
        final Object obj = delegate.getObject();
        if (obj instanceof File) {
            throw new IllegalArgumentException(
                    File.class.getSimpleName() + " is not supported as type for: " + element.getWicketId());
        } else if (obj instanceof IResource) {
            throw new IllegalArgumentException(
                    IResource.class.getSimpleName() + " is not supported as type for: " + element.getWicketId());
        } else if (obj instanceof ResourceReference) {
            final ResourceReference resourceReference = (ResourceReference) obj;
            final String absoluteUrl = convertToAbsoluteUrl(resourceReference);
            return absoluteUrl;
        } else {
            final String url = Strings.asString(obj);
            if (url == null) {
                return null;
            } else {
                if (UrlUtils.isRelative(url)) {
                    try {
                        //check if there is a package resource that is linked
                        final PackageResourceReference resourceRef = new PackageResourceReference(
                                targetObjectModel.getObject().getClass(), url);
                        if (PackageResource.exists(resourceRef.getKey())) {
                            final String absoluteUrl = convertToAbsoluteUrl(resourceRef);
                            return absoluteUrl;
                        } else {
                            final String absoluteUrl = convertToAbsoluteUrl(url);
                            return absoluteUrl;
                        }
                    } catch (final Throwable t) {
                        final String absoluteUrl = convertToAbsoluteUrl(url);
                        return absoluteUrl;
                    }
                } else {
                    //already absolute
                    return url;
                }
            }
        }
    }

    private String convertToAbsoluteUrl(final ResourceReference resourceReference) {
        return convertToAbsoluteUrl(RequestCycle.get().urlFor(resourceReference, null).toString());
    }

    private String convertToAbsoluteUrl(final String url) {
        return RequestCycle.get().getUrlRenderer().renderFullUrl(Url.parse(url));
    }
}
