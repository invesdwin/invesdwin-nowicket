package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.Page;

import de.invesdwin.nowicket.application.PageFactory;

@NotThreadSafe
public class ShowPageGuiTask implements IGuiTask {

    private final Object modelObject;

    public ShowPageGuiTask(final Object modelObject) {
        this.modelObject = modelObject;
    }

    @Override
    public void process(final Component component) {
        final Page page = PageFactory.get().getPage(modelObject);
        component.setResponsePage(page);
        if (page.getRenderCount() > 0) {
            //reusing an old page, thus might have to process request again when the page is rendering to handle status messages and modals
            new WaitForNextAjaxCallGuiTask().process(page);
        }
    }

}
