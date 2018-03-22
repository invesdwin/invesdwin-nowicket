package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes.Method;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.resource.IResourceStream;

import de.invesdwin.nowicket.component.AAjaxDownload;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.util.Components;

/**
 * http://stackoverflow.com/questions/25997713/apache-wicket-respond-with-generated-file-without-downloadlink
 *
 */
@NotThreadSafe
public class OfferDownloadGuiTask implements IGuiTask {

    private static final MetaDataKey<Boolean> KEY_OFFER_DOWNLOAD_BEHAVIOR_ADDED = new MetaDataKey<Boolean>() {
    };

    private OfferDownloadConfig config;

    public OfferDownloadGuiTask(final OfferDownloadConfig config) {
        this.config = config;
    }

    @Override
    public Collection<? extends Component> process(final Component component) {
        final Component root = Components.findComponentForDomReadyAjaxCall(component);
        final Boolean behaviorAdded = root.getMetaData(KEY_OFFER_DOWNLOAD_BEHAVIOR_ADDED);
        if (behaviorAdded == null || !behaviorAdded) {
            root.setMetaData(KEY_OFFER_DOWNLOAD_BEHAVIOR_ADDED, true);
            final AAjaxDownload ajaxDownload = new AAjaxDownload() {

                private boolean requestHandled = false;

                @Override
                protected IResourceStream getResourceStream() {
                    return config.getResourceStream();
                }

                @Override
                protected String getFileName() {
                    return config.getFileName();
                }

                @Override
                public boolean isTemporary(final Component component) {
                    return requestHandled;
                }

                @Override
                public void onConfigure(final Component component) {
                    super.onConfigure(component);
                    if (requestHandled) {
                        //make sure this behavior gets removed
                        component.remove(this);
                    }
                }

                @Override
                public void onRequest() {
                    if (!requestHandled) {
                        super.onRequest();
                        config = null;
                        requestHandled = true;
                    }
                }

            };
            component.add(ajaxDownload);
            // see http://wickedsource.org/tag/wicket-tutorial-ajax-behavior/
            component.add(new AbstractDefaultAjaxBehavior() {

                private boolean eventFired = false;

                @Override
                public boolean isTemporary(final Component component) {
                    return eventFired;
                }

                @Override
                protected void respond(final AjaxRequestTarget target) {
                    //second safety net is required since the hierarchy might not have been put together properly when the initializationFinished calls were made
                    if (!eventFired) {
                        //run as a separate request after rendering
                        final Component root = Components.findForm(target.getPage()).getRootForm();
                        ajaxDownload.initiate(target);
                        root.setMetaData(KEY_OFFER_DOWNLOAD_BEHAVIOR_ADDED, false);
                        eventFired = true;
                    }
                }

                @Override
                public void renderHead(final Component component, final IHeaderResponse response) {
                    super.renderHead(component, response);
                    if (!eventFired) {
                        response.render(OnDomReadyHeaderItem.forScript(getCallbackScript()));
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
            return Arrays.asList(component);
        } else {
            return Collections.emptyList();
        }
    }

}
