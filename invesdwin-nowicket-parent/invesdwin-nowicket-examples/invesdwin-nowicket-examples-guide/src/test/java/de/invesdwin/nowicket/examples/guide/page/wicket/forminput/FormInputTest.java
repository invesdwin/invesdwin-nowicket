package de.invesdwin.nowicket.examples.guide.page.wicket.forminput;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.Test;

import de.invesdwin.nowicket.component.modal.panel.ModalMessage;
import de.invesdwin.nowicket.examples.guide.AExampleTest;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;
import de.invesdwin.nowicket.generated.guiservice.test.GuiServiceMethod;
import de.invesdwin.nowicket.generated.guiservice.test.GuiServiceMethodCall;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class FormInputTest extends AExampleTest {

    private final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(getClass());

    @Test
    public void testResetWithDirtyCheck() {

        log.warn("Webserver listening on http://localhost:"
                + getSpringContext().getEnvironment().getProperty("local.server.port"));

        Assertions.assertThat(getGuiServiceTester().getMethodCalls()).isEmpty();
        final FormInput formInput = new FormInput();

        //check not dirty message in the beginning
        formInput.reset();
        Assertions.assertThat(getGuiServiceTester().getMethodCalls()).hasSize(1);
        final GuiServiceMethodCall statusMessageCallError = getGuiServiceTester().getMethodCalls().pop();
        Assertions.assertThat(statusMessageCallError.getMethod()).isSameAs(GuiServiceMethod.showStatusMessage);
        final StatusMessageConfig statusMessageConfigError = (StatusMessageConfig) statusMessageCallError.getArgs()
                .get(0);
        //not dirty
        Assertions.assertThat(statusMessageConfigError.getType()).isSameAs(StatusMessageType.error);

        //mark dirty
        formInput.setBooleann(!formInput.getBooleann());
        Assertions.assertThat(formInput.dirtyTracker().isDirty(FormInputConstants.booleann)).isTrue();

        //modal is shown for reset confirmation
        formInput.reset();
        Assertions.assertThat(getGuiServiceTester().getMethodCalls()).hasSize(1);
        final GuiServiceMethodCall showModalPanelCall = getGuiServiceTester().getMethodCalls().pop();
        Assertions.assertThat(showModalPanelCall.getMethod()).isSameAs(GuiServiceMethod.showModalPanel);
        final ModalMessage modalMessage = (ModalMessage) showModalPanelCall.getArgs().get(0);
        //i18n is disabled during model tests, since a page is required for it, thus we can test the properties more robustly
        Assertions.assertThat(modalMessage.getMessage()).contains(FormInputConstants.booleann);
        Assertions.assertThat(modalMessage.getMessage()).contains("your.inputs.will.be.lost");
        modalMessage.ok();

        Assertions.assertThat(getGuiServiceTester().getMethodCalls()).hasSize(2);

        final GuiServiceMethodCall statusMessageCallSuccess = getGuiServiceTester().getMethodCalls().pop();
        Assertions.assertThat(statusMessageCallSuccess.getMethod()).isSameAs(GuiServiceMethod.showStatusMessage);
        final StatusMessageConfig statusMessageConfigSuccess = (StatusMessageConfig) statusMessageCallSuccess.getArgs()
                .get(0);
        //successfull reset
        Assertions.assertThat(statusMessageConfigSuccess.getType()).isSameAs(StatusMessageType.success);

        final GuiServiceMethodCall showPageCall = getGuiServiceTester().getMethodCalls().pop();
        Assertions.assertThat(showPageCall.getMethod()).isSameAs(GuiServiceMethod.showPage);
        //verify its a fresh page model
        final FormInput showPageModel = (FormInput) showPageCall.getArgs().get(0);
        Assertions.assertThat(showPageModel.dirtyTracker().isDirty()).isFalse();
        Assertions.assertThat(showPageModel.getBooleann()).isEqualTo(!formInput.getBooleann());

    }

}
