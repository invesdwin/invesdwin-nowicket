package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.UrlUtils;

import de.invesdwin.nowicket.generated.binding.processor.element.ImageHtmlElement;

@NotThreadSafe
public class ModelImage extends Image {

    public ModelImage(final ImageHtmlElement element) {
        super(element.getWicketId(), element.getModel());
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        //allow absolute urls
        final String url = getDefaultModelObjectAsString();
        if (!UrlUtils.isRelative(url)) {
            tag.put("src", url);
        }
    }

}
