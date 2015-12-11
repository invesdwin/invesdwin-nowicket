package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.exception;

import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.StringResourceModel;
import org.springframework.security.access.AccessDeniedException;

import de.invesdwin.nowicket.component.modal.panel.ModalMessage;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class DefaultSubmitButtonExceptionHandler implements ISubmitButtonExceptionHandler {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory
            .getXLogger(DefaultSubmitButtonExceptionHandler.class);

    @Override
    public void handleSubmitButtonException(final IHtmlElement<?, ?> element, final Component component,
            final Throwable t) {
        if (shouldSwallowException(t)) {
            LOG.catching(org.slf4j.ext.XLogger.Level.WARN, new RuntimeException("Button exception swallowed...", t));
        } else {
            if (shouldShowExceptionMessage(t)) {
                logShowExceptionMessage(t);
                final String title = component.getLocalizer().getString(
                        DefaultSubmitButtonExceptionHandler.class.getSimpleName() + ".exception.title", component);
                String message;
                try {
                    message = new StringResourceModel(t.getMessage(), component,
                            element.getContext().getMarkupContainer().getDefaultModel()).getString();
                } catch (final MissingResourceException e) {
                    message = t.getMessage();
                }
                showExceptionMessage(element, component, t, title, message);
            } else if (shouldShowAccessDeniedExceptionMessage(t)) {
                logShowExceptionMessage(t);
                showAccessDeniedExceptionMessage(element, component, t);
            } else {
                propagateException(t);
            }
        }
    }

    protected void showAccessDeniedExceptionMessage(final IHtmlElement<?, ?> element, final Component component,
            final Throwable t) {
        final String elementTitle = element.getTitleModel().getObject();
        final String title = component.getLocalizer().getString(
                DefaultSubmitButtonExceptionHandler.class.getSimpleName() + ".access.denied.title", component);
        final String message = new StringResourceModel(
                DefaultSubmitButtonExceptionHandler.class.getSimpleName() + ".access.denied.message", component,
                element.getContext().getMarkupContainer().getDefaultModel(), null, elementTitle).getString();
        showExceptionMessage(element, component, t, title, message);
    }

    protected boolean shouldShowAccessDeniedExceptionMessage(final Throwable t) {
        return t instanceof AccessDeniedException;
    }

    protected void propagateException(final Throwable t) {
        throw new RuntimeException("Propagated button exception...", t);
    }

    protected boolean shouldShowExceptionMessage(final Throwable t) {
        return t instanceof Exception && !(t instanceof RuntimeException);
    }

    protected boolean shouldSwallowException(final Throwable t) {
        return t instanceof Exception && !(t instanceof RuntimeException) && Strings.isBlank(t.getMessage());
    }

    protected void showExceptionMessage(final IHtmlElement<?, ?> element, final Component component, final Throwable t,
            final String title, final String message) {
        GuiService.get().showModalPanel(new ModalMessage(title, message));
    }

    public void logShowExceptionMessage(final Throwable t) {
        LOG.catching(org.slf4j.ext.XLogger.Level.WARN,
                new RuntimeException("Showing " + ModalMessage.class.getSimpleName() + " for button exception...", t));
    }

}
