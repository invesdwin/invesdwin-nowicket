package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.Page;

import de.invesdwin.nowicket.application.PageFactory;
import de.invesdwin.nowicket.generated.guiservice.internal.SessionGuiService;

@NotThreadSafe
public class ShowPageGuiTask implements IGuiTask {

    private final Object modelObject;

    public ShowPageGuiTask(final Object modelObject) {
        this.modelObject = modelObject;
    }

    @Override
    public Collection<? extends Component> process(final Component component) {
        final Page page = PageFactory.get().getPage(modelObject);
        component.setResponsePage(page);
        if (page.getRenderCount() > 0 && SessionGuiService.get().getGuiTasks().showPageShouldWaitForNextAjaxCall()) {
            //reusing an old page, thus might have to process request again when the page is rendering to handle status messages and modals
            return new WaitForNextAjaxCallGuiTask().process(page);
        } else {
            return Collections.emptyList();
        }
    }

}
