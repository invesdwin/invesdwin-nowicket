package de.invesdwin.nowicket.generated.guiservice;

import java.awt.Dimension;
import java.io.File;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.generated.guiservice.internal.SessionGuiService;

// @Named
@ThreadSafe
public class GuiService implements FactoryBean<GuiService>, IGuiService {

    private static final GuiService INSTANCE = new GuiService();
    private static volatile IGuiService guiServiceOverride;

    public static GuiService get() {
        return INSTANCE;
    }

    /**
     * For internal use only during tests e.g.
     */
    public static void setGuiServiceOverride(final IGuiService guiServiceOverride) {
        GuiService.guiServiceOverride = guiServiceOverride;
    }

    @Override
    public GuiService getObject() throws Exception {
        return INSTANCE;
    }

    private IGuiService getGuiServiceImpl() {
        if (GuiService.guiServiceOverride != null) {
            return GuiService.guiServiceOverride;
        } else {
            return SessionGuiService.get();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return INSTANCE.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void showPage(final Object modelObject) {
        getGuiServiceImpl().showPage(modelObject);
    }

    @Override
    public void showModalPanel(final Object modelObject, final Dimension dimension) {
        getGuiServiceImpl().showModalPanel(modelObject, dimension);
    }

    @Override
    public boolean isModalPanelShowing() {
        return getGuiServiceImpl().isModalPanelShowing();
    }

    @Override
    public void hideModalPanel() {
        getGuiServiceImpl().hideModalPanel();
    }

    @Override
    public void showStatusMessage(final StatusMessageConfig config) {
        getGuiServiceImpl().showStatusMessage(config);
    }

    @Override
    public void processRequestFinally(final Component component) {
        getGuiServiceImpl().processRequestFinally(component);
    }

    @Override
    public void processInitializationFinished(final MarkupContainer markupContainer) {
        getGuiServiceImpl().processInitializationFinished(markupContainer);
    }

    @Override
    public File getSessionFolder() {
        return getGuiServiceImpl().getSessionFolder();
    }

    @Override
    public void showModalPanel(final Object modelObject) {
        getGuiServiceImpl().showModalPanel(modelObject);
    }

    @Override
    public void showStatusMessage(final String message) {
        getGuiServiceImpl().showStatusMessage(message);
    }

    @Override
    public void showStatusMessage(final String title, final String message) {
        getGuiServiceImpl().showStatusMessage(title, message);
    }

    @Override
    public void offerDownload(final OfferDownloadConfig config) {
        getGuiServiceImpl().offerDownload(config);
    }

    /**
     * The internationalization here resolves the properties from the page, thus nested components do not work here.
     * 
     * This does not work well in constructors, instead please only use this in property getters and dynamic methods.
     * This is because the page cannot be retrieved during construction to do the internationalization.
     */
    public static String i18n(final Class<? extends Component> componentClass, final String property) {
        return new I18nModel(componentClass, property).getObject();
    }

}
