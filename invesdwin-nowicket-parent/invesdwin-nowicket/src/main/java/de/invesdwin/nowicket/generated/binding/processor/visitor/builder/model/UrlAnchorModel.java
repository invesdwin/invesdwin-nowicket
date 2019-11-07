package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.io.File;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.UrlUtils;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.invesdwin.nowicket.component.FileResourceReference;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class UrlAnchorModel implements IModel<String> {

    private final IModel<Object> delegate;
    private final IModel<?> targetObjectModel;

    public UrlAnchorModel(final IHtmlElement<?, ?> element) {
        this.targetObjectModel = element.getTargetObjectModel();
        //makes getter work for an action accessor; dunno why BeanPathModel does not work here...
        this.delegate = new PropertyModel<Object>(element.getRootObjectModel().getObject(), element.getWicketId());
    }

    public UrlAnchorModel(final IModel<?> rootObjectModel, final String beanPath) {
        this.targetObjectModel = rootObjectModel;
        this.delegate = new BeanPathModel<Object>(rootObjectModel, beanPath);
    }

    @Override
    public String getObject() {
        final Object obj = delegate.getObject();
        if (obj instanceof File) {
            final File file = (File) obj;
            final String absoluteUrl = convertToUrl(file);
            return absoluteUrl;
        } else if (obj instanceof ResourceReference) {
            final ResourceReference resourceReference = (ResourceReference) obj;
            final String absoluteUrl = convertToUrl(resourceReference);
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
                            final String absoluteUrl = convertToUrl(resourceRef);
                            return absoluteUrl;
                        } else {
                            return url;
                        }
                    } catch (final Throwable t) {
                        return url;
                    }
                } else {
                    return url;
                }
            }
        }
    }

    public static String convertToUrl(final File file) {
        final FileResourceReference resourceReference = new FileResourceReference(file);
        return convertToUrl(resourceReference);
    }

    public static String convertToUrl(final ResourceReference resourceReference) {
        return RequestCycle.get().urlFor(resourceReference, null).toString();
    }

}
