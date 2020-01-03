package de.invesdwin.nowicket.generated.guiservice.test;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.component.modal.ModalConfig;
import de.invesdwin.nowicket.generated.guiservice.IGuiService;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.util.lang.Files;

@NotThreadSafe
public class GuiServiceTester implements IGuiService {

    private final Stack<GuiServiceMethodCall> methodCalls = new Stack<GuiServiceMethodCall>();
    private final AtomicInteger modalPanelsShowing = new AtomicInteger();
    private final AtomicBoolean skipUpdateAllComponentsForCurrentRequest = new AtomicBoolean(false);

    private final File sessionFolder = new File(ABaseWebApplication.get().getSessionsDirectory(),
            getClass().getSimpleName());

    public Stack<GuiServiceMethodCall> getMethodCalls() {
        return methodCalls;
    }

    public void reset() {
        methodCalls.clear();
        modalPanelsShowing.set(0);
        Files.deleteQuietly(sessionFolder);
        skipUpdateAllComponentsForCurrentRequest.set(false);
    }

    @Override
    public void showPage(final Object modelObject) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showPage, modelObject));
        modalPanelsShowing.set(0);
    }

    @Override
    public void showStatusMessage(final StatusMessageConfig config) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showStatusMessage, config));
    }

    @Override
    public void showModalPanel(final Object modelObject, final ModalConfig config) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showModalPanel, modelObject, config));
        modalPanelsShowing.incrementAndGet();
    }

    @Override
    public boolean isModalPanelShowing() {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.isModalPanelShowing));
        return modalPanelsShowing.get() > 0;
    }

    @Override
    public void hideModalPanel() {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.hideModalPanel));
        if (modalPanelsShowing.get() > 0) {
            modalPanelsShowing.decrementAndGet();
        }
    }

    @Override
    public void disableUpdateAllComponentsForCurrentRequest() {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.skipUpdateAllComponentsForCurrentRequest));
        skipUpdateAllComponentsForCurrentRequest.set(true);
    }

    @Override
    public boolean isDisableUpdateAllComponentsForCurrentRequest() {
        return skipUpdateAllComponentsForCurrentRequest.get();
    }

    @Override
    public void processRequestFinally(final Component component) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.processRequestFinally, component));
    }

    @Override
    public void processInitializationFinished(final MarkupContainer markupContainer) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.processInitializationFinished, markupContainer));
    }

    @Override
    public File getSessionFolder() {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.getSessionFolder));
        if (!sessionFolder.exists()) {
            try {
                Files.forceMkdir(sessionFolder);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFolder;
    }

    @Override
    public void showModalPanel(final Object modelObject) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showModalPanel, modelObject));
        modalPanelsShowing.incrementAndGet();
    }

    @Override
    public void showStatusMessage(final String message) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showStatusMessage, message));
    }

    @Override
    public void showStatusMessage(final String title, final String message) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showStatusMessage, title, message));
    }

    @Override
    public void offerDownload(final OfferDownloadConfig config) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.offerDownload, config));
    }

}
