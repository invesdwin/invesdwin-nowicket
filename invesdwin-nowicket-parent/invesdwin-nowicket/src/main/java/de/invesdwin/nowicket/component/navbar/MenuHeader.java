package de.invesdwin.nowicket.component.navbar;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;

@NotThreadSafe
public class MenuHeader extends AbstractLink {

    public MenuHeader(final IModel<String> label) {
        super(ButtonList.getButtonMarkupId());
        setBody(label);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        tag.setName("div");
        tag.getAttributes().clear();
        tag.append("class", "dropdown-header", " ");
    }

}
