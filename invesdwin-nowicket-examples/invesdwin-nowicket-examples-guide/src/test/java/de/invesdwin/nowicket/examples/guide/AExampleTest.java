package de.invesdwin.nowicket.examples.guide;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.SocketUtils;

import de.invesdwin.instrument.DynamicInstrumentationLoader;
import de.invesdwin.nowicket.examples.guide.internal.ExampleWebApplication;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.test.GuiServiceTester;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public abstract class AExampleTest {

    //using random port during tests
    private static int testServerPort = SocketUtils.findAvailableTcpPort();

    static {
        DynamicInstrumentationLoader.waitForInitialized();
        DynamicInstrumentationLoader.initLoadTimeWeavingContext();
    }

    private static ConfigurableApplicationContext springContext;
    private static WicketTester wicketTester;
    private static GuiServiceTester guiServiceTester;

    @BeforeAll
    public static void setUpOnce() throws Exception {
        Assertions.assertThat(wicketTester).isNull();
        //make @Configurable and PropertyChangeSupport aspect work
        AExampleTest.springContext = SpringApplication.run(Main.class, "--server.port=" + getTestServerPort());
        //wicketTester might sometimes still be needed
        AExampleTest.wicketTester = new WicketTester(new ExampleWebApplication());
        //the guiServiceTester is useful to test modals and other things
        AExampleTest.guiServiceTester = new GuiServiceTester();
        GuiService.setGuiServiceOverride(guiServiceTester);
    }

    public static int getTestServerPort() {
        return testServerPort;
    }

    public static void setTestServerPort(final int testServerPort) {
        AExampleTest.testServerPort = testServerPort;
    }

    /**
     * Since SessionGuiService is deactivated here, you cannot test generated modals in wicketTester, but need to check
     * GuiServiceTester instead. You should rather use model based testing anyway instead of rendered tests with wicket
     * tester.
     */
    @Deprecated
    protected WicketTester getWicketTester() {
        return AExampleTest.wicketTester;
    }

    protected ConfigurableApplicationContext getSpringContext() {
        return AExampleTest.springContext;
    }

    protected GuiServiceTester getGuiServiceTester() {
        return AExampleTest.guiServiceTester;
    }

    @BeforeEach
    public void setUp() throws Exception {
        if (wicketTester.getLastRenderedPage() != null) {
            wicketTester.clearFeedbackMessages();
        }
        guiServiceTester.reset();
    }

    @AfterAll
    public static void tearDownOnce() throws Exception {
        Assertions.assertThat(wicketTester).isNotNull();
        wicketTester.destroy();
        AExampleTest.wicketTester = null;
        GuiService.setGuiServiceOverride(null);
        AExampleTest.guiServiceTester = null;
        springContext.close();
        AExampleTest.springContext = null;
        //need to clear security context anyway, even if not enabled
        SecurityContextHolder.clearContext();
        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

}
