package de.invesdwin.nowicket.generated.guiservice.test;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.generated.guiservice.IGuiService;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;

@NotThreadSafe
public class GuiServiceTester implements IGuiService {

    private final Stack<GuiServiceMethodCall> methodCalls = new Stack<GuiServiceMethodCall>();
    private final AtomicInteger modalPanelsShowing = new AtomicInteger();

    private final File sessionFolder = new File(ABaseWebApplication.get().getSessionsDirectory(),
            getClass().getSimpleName());

    public Stack<GuiServiceMethodCall> getMethodCalls() {
        return methodCalls;
    }

    public void reset() {
        methodCalls.clear();
        modalPanelsShowing.set(0);
        FileUtils.deleteQuietly(sessionFolder);
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
    public void showModalPanel(final Object modelObject, final Dimension dimension) {
        methodCalls.add(new GuiServiceMethodCall(GuiServiceMethod.showModalPanel, modelObject, dimension));
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
                FileUtils.forceMkdir(sessionFolder);
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
