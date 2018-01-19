package de.invesdwin.nowicket.generated.guiservice.internal;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.RequestHandlerStack.ReplaceHandlerException;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.session.ISessionStore.UnboundListener;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.component.modal.ModalContainer;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.nowicket.generated.guiservice.IGuiService;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.internal.tasks.GuiTasks;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.error.Throwables;

@NotThreadSafe
public class SessionGuiService implements IGuiService, Serializable {

    private static final MetaDataKey<SessionGuiService> KEY_SESSION_GUI_SERVICE = new MetaDataKey<SessionGuiService>() {
    };
    private static final MetaDataKey<Boolean> KEY_INITIALIZATION_FINISHED_BEHAVIOR_ADDED = new MetaDataKey<Boolean>() {
    };
    private static final MetaDataKey<Boolean> KEY_INITIALIZATION_FINISHED_BEHAVIOR_ALREADY_CONFIGURED = new MetaDataKey<Boolean>() {
    };

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(SessionGuiService.class);

    private GuiTasks guiTasks = new GuiTasks();
    private File sessionFolder;

    public static SessionGuiService get() {
        SessionGuiService sessionGuiService = AWebSession.get().getMetaData(KEY_SESSION_GUI_SERVICE);
        if (sessionGuiService == null) {
            sessionGuiService = new SessionGuiService();
            AWebSession.get().setMetaData(KEY_SESSION_GUI_SERVICE, sessionGuiService);
        }
        return sessionGuiService;
    }

    @Override
    public synchronized void processInitializationFinished(final MarkupContainer markupContainer) {
        final Component root = Components.findRoot(markupContainer);
        final Boolean behaviorAdded = root.getMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ADDED);
        if (behaviorAdded == null || !behaviorAdded) {
            root.setMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ADDED, true);
            root.add(new Behavior() {

                @Override
                public void onConfigure(final Component component) {
                    //second safety net is required since the hierarchy might not have been put together properly when the initializationFinished calls were made
                    final Component root = Components.findRoot(component);
                    final Boolean behaviorAlreadyConfigured = root
                            .getMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ALREADY_CONFIGURED);
                    if (behaviorAlreadyConfigured == null || !behaviorAlreadyConfigured) {
                        root.setMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ALREADY_CONFIGURED, true);
                        //run before first render and execute any tasks that are scheduled directly
                        processRequestFinally(root);
                    }
                }

                @Override
                public boolean isTemporary(final Component component) {
                    return true;
                }

            });
        }
    }

    @Override
    public void processRequestFinally(final Component component) {
        try {
            guiTasks.process(component);
            updateAllComponents(component);
        } catch (final ReplaceHandlerException e) {
            resetGuiTasks(e);
            //rethrow so that redirect can happen
            throw e;
        } catch (final Throwable t) {
            resetGuiTasks(t);
        }
    }

    private void resetGuiTasks(final Throwable t) {
        LOG.catching(new RuntimeException(GuiTasks.class.getSimpleName()
                + ".process() threw an exception, resetting everything to keep the overall website working for the next request...",
                t));
        guiTasks = new GuiTasks();
    }

    private void updateAllComponents(final Component component) {
        final AjaxRequestTarget target = RequestCycle.get().find(AjaxRequestTarget.class);
        final Form<?> form = Components.findForm(component);
        if (target != null && form != null) {
            final MarkupContainer root = (MarkupContainer) Components.findRoot(form);
            try {
                root.visitChildren(new IVisitor<Component, Void>() {
                    @Override
                    public void component(final Component object, final IVisit<Void> visit) {
                        if (object instanceof Form || object instanceof TabbedPanel
                                || object instanceof ModalContainer) {
                            target.add(object);
                        }
                        if (!object.isVisible()) {
                            final List<ModelComponentBehavior> modelComponentBehaviors = object
                                    .getBehaviors(ModelComponentBehavior.class);
                            for (final ModelComponentBehavior behavior : modelComponentBehaviors) {
                                //update visibility manually since onConfigure() will be skipped
                                behavior.onConfigure(object);
                            }
                        }
                    }
                });
            } catch (final Throwable t) {
                if (t.getMessage().contains("longer be added")) {
                    //CHECKSTYLE:OFF
                    LOG.debug("Ignoring exception cause for frozen components: {}", new Object() {
                        @Override
                        public String toString() {
                            return Throwables.getFullStackTrace(t);
                        }
                    });
                    //CHECKSTYLE:ON
                } else {
                    throw t;
                }
            }
        }
    }

    @Override
    public void showPage(final Object modelObject) {
        guiTasks.showPage(modelObject);
    }

    @Override
    public void showModalPanel(final Object modelObject, final Dimension dimension) {
        guiTasks.showModalPanel(modelObject, dimension);
    }

    @Override
    public void showStatusMessage(final StatusMessageConfig config) {
        guiTasks.showStatusMessage(config);
    }

    @Override
    public boolean isModalPanelShowing() {
        return guiTasks.isModalPanelShowing();
    }

    @Override
    public void hideModalPanel() {
        guiTasks.hideModalPanel();
    }

    @Override
    public File getSessionFolder() {
        if (sessionFolder == null) {
            sessionFolder = new File(ABaseWebApplication.get().getSessionsDirectory(),
                    getClass().getSimpleName() + "/" + AWebSession.get().getId());
            //clean up folder initially to prevent session clash with stale data
            FileUtils.deleteQuietly(sessionFolder);
            //add listener to clean up the session directory on session unbind aswell
            final ABaseWebApplication application = ABaseWebApplication.get();
            application.getSessionStore().registerUnboundListener(new UnboundListener() {
                @Override
                public void sessionUnbound(final String sessionId) {
                    if (sessionFolder.getAbsolutePath().contains(sessionId)) {
                        application.getSessionStore().unregisterUnboundListener(this);
                        FileUtils.deleteQuietly(sessionFolder);
                    }
                }
            });
            try {
                FileUtils.forceMkdir(sessionFolder);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFolder;
    }

    @Override
    public void showModalPanel(final Object modelObject) {
        showModalPanel(modelObject, null);
    }

    @Override
    public void showStatusMessage(final String message) {
        showStatusMessage(null, message);
    }

    @Override
    public void showStatusMessage(final String title, final String message) {
        showStatusMessage(new StatusMessageConfig().withTitle(title).withMessage(message));
    }

    @Override
    public void offerDownload(final OfferDownloadConfig config) {
        guiTasks.offerDownload(config);
    }

    public GuiTasks getGuiTasks() {
        return guiTasks;
    }
}
