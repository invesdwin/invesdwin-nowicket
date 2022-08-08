package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes.Method;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import de.invesdwin.nowicket.component.header.DisableComponentsOnAjaxCallJsReference;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.collections.Arrays;
import de.invesdwin.util.collections.Collections;

@NotThreadSafe
public class WaitForNextAjaxCallGuiTask implements IGuiTask {

    private static final MetaDataKey<Boolean> KEY_WAIT_BEHAVIOR_ADDED = new MetaDataKey<Boolean>() {
    };

    @Override
    public Collection<? extends Component> process(final Component component) {
        final Component root = Components.findComponentForDomReadyAjaxCall(component);
        final Boolean behaviorAdded = root.getMetaData(KEY_WAIT_BEHAVIOR_ADDED);
        if (behaviorAdded == null || !behaviorAdded) {
            root.setMetaData(KEY_WAIT_BEHAVIOR_ADDED, true);
            root.add(new AbstractDefaultAjaxBehavior() {

                private boolean eventFired = false;

                @Override
                protected void respond(final AjaxRequestTarget target) {
                    if (!eventFired) {
                        //run as a separate request after rendering
                        final Component root = Components.findForm(target.getPage()).getRootForm();
                        root.setMetaData(KEY_WAIT_BEHAVIOR_ADDED, false);
                        eventFired = true;
                        GuiService.get().processRequestFinally(root);
                    }
                }

                @Override
                public boolean isTemporary(final Component component) {
                    return eventFired;
                }

                @Override
                public void renderHead(final Component component, final IHeaderResponse response) {
                    super.renderHead(component, response);
                    if (!eventFired) {
                        response.render(OnDomReadyHeaderItem.forScript(getCallbackScript()
                                + DisableComponentsOnAjaxCallJsReference.UNDISABLE_FUNCTION_CALL_SNIPPET));
                    }
                }

                @Override
                public void onConfigure(final Component component) {
                    super.onConfigure(component);
                    if (eventFired) {
                        //make sure this behavior gets removed
                        component.remove(this);
                    }
                }

                @Override
                protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
                    super.updateAjaxAttributes(attributes);
                    attributes.setMethod(Method.POST); //prevent request is too large exception for GET requests
                }

            });
            return Arrays.asList(root);
        } else {
            return Collections.emptyList();
        }
    }

}
