package de.invesdwin.nowicket.examples.guide.pages.guestbook.persistence;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class GuestbookEntryEntityPanel extends Panel {
    public GuestbookEntryEntityPanel(final String id, final IModel<GuestbookEntryEntity> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
