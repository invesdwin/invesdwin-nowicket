package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelResourceLink extends ResourceLink<Object> {

    public ModelResourceLink(final String id, final ResourceReference resourceReference,
            final IModel<String> titleModel) {
        super(id, resourceReference);
        setBody(titleModel);
    }

    public ModelResourceLink(final String id, final IResource resource, final IModel<String> titleModel) {
        super(id, resource);
        setBody(titleModel);
    }

    public ModelResourceLink(final AnchorHtmlElement element, final ResourceReference resourceReference) {
        this(element.getWicketId(), resourceReference, element.getTitleModel());
    }

    public ModelResourceLink(final AnchorHtmlElement element, final IResource resource) {
        this(element.getWicketId(), resource, element.getTitleModel());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        maybeSetTargetBlank(tag);
        if ("iframe".equals(tag.getName())) {
            //add support for iframe
            final String url = getURL().toString();
            tag.put("src", url);
        } else {
            super.onComponentTag(tag);
        }
    }

    protected boolean isBootstrapButtonStyle(final ComponentTag tag) {
        return Strings.contains(tag.getAttribute("class"), "btn");
    }

    @Override
    protected void disableLink(final ComponentTag tag) {
        if (isBootstrapButtonStyle(tag)) {
            tag.setName("button");
            tag.put("type", "button");
            tag.append("class", "btn-disabled", " ");
        }
        super.disableLink(tag);
        tag.setName("a"); //for bootstrap it should be a and not span
    }

    public static void maybeSetTargetBlank(final ComponentTag tag) {
        if ("a".equals(tag.getName()) && Strings.isBlank(tag.getAttribute("target"))) {
            tag.put("target", AnchorHtmlElement.TARGET_BLANK);
        }
    }

}
