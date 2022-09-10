package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.ComponentTag;

import de.invesdwin.util.lang.Strings;

/**
 * better styled link on disabled
 */
@NotThreadSafe
public abstract class AModelAjaxFallbackLink extends AjaxFallbackLink<Void> {

    public AModelAjaxFallbackLink(final String id) {
        super(id);
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

}
