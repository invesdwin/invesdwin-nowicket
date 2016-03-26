package de.invesdwin.nowicket.examples.guide.page.wicket.modalwindow;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.modalwindow.panel.ModalWindow;
import de.invesdwin.nowicket.examples.guide.page.wicket.modalwindow.panel.ModalWindowPanel;

@NotThreadSafe
@MountPath("modalwindow")
public class ModalWindowStartPage extends AExampleWebPage {

    public ModalWindowStartPage() {
        super(null);
        add(new ModalWindowPanel("panel", Model.of(new ModalWindow(0, new AtomicLong()))));
    }

}
