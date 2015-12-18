package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.UrlUtils;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class UrlAnchorModel extends AbstractReadOnlyModel<String> {

    private final IModel<Object> delegate;
    private final IModel<?> targetObjectModel;

    public UrlAnchorModel(final AnchorHtmlElement element) {
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
                        return RequestCycle.get().urlFor(resourceRef, null).toString();
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
