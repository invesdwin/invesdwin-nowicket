package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;

import de.invesdwin.util.lang.Strings;

/**
 * better styled link on disabled
 */
@NotThreadSafe
public abstract class AModelAjaxFallbackLink extends AjaxFallbackLink<Void> {

    private boolean disableDefaultDisabledStyle;

    public AModelAjaxFallbackLink(final String id) {
        super(id);
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        if (isBootstrapButtonStyle(openTag)) {
            disableDefaultDisabledStyle = true;
        }
        super.onComponentTagBody(markupStream, openTag);
        if (isBootstrapButtonStyle(openTag)) {
            disableDefaultDisabledStyle = false;
        }
    }

    @Override
    public String getBeforeDisabledLink() {
        if (disableDefaultDisabledStyle) {
            return null;
        } else {
            return super.getBeforeDisabledLink();
        }
    }

    @Override
    public String getAfterDisabledLink() {
        if (disableDefaultDisabledStyle) {
            return null;
        } else {
            return super.getAfterDisabledLink();
        }
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

    protected boolean isBootstrapButtonStyle(final ComponentTag tag) {
        return Strings.contains(tag.getAttribute("class"), "btn");
    }

}
