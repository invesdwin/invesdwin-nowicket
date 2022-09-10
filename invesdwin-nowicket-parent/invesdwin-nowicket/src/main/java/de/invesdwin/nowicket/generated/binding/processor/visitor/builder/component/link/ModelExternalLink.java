package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelExternalLink extends ExternalLink {

    public ModelExternalLink(final String id, final IModel<String> model, final IModel<String> titleModel) {
        super(id, model, titleModel);
    }

    public ModelExternalLink(final AnchorHtmlElement element) {
        this(element.getWicketId(), element.getUrlModel(), element.getTitleModel());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        ModelResourceLink.maybeSetTargetBlank(tag);
        if ("iframe".equals(tag.getName())) {
            //add support for iframe
            final String url = getDefaultModelObjectAsString();
            tag.put("src", url);
        } else {
            super.onComponentTag(tag);
        }
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        if (useJSEventBindingWhenNeeded()) {
            super.renderHead(response);
        }
    }

    @Override
    protected void disableLink(final ComponentTag tag) {
        if (isBootstrapButtonStyle(tag)) {
            tag.setName("button");
            tag.put("type", "button");
            tag.append("class", "disabled", " ");
        }
        super.disableLink(tag);
        tag.setName("a"); //for bootstrap it should be a and not span
    }

    protected boolean isBootstrapButtonStyle(final ComponentTag tag) {
        return Strings.contains(tag.getAttribute("class"), "btn");
    }

    protected boolean useJSEventBindingWhenNeeded() {
        final ComponentTag tag = getMarkupTag();
        return !"iframe".equals(tag.getName());
    }

}
