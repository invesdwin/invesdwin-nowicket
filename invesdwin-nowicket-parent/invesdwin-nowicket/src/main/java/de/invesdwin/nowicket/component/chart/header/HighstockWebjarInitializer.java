package de.invesdwin.nowicket.component.chart.header;

import javax.annotation.concurrent.Immutable;

import com.googlecode.wickedcharts.wicket6.JavaScriptResourceRegistry;

import de.invesdwin.nowicket.application.auth.AWebApplication;
import de.invesdwin.util.assertions.Assertions;

@Immutable
public final class HighstockWebjarInitializer {

    private static boolean initialized;

    private HighstockWebjarInitializer() {}

    public static void init() {
        //highstock webjar is optional, thus might be skipped
        if (HighstockJsReference.INSTANCE.getResource().getResourceStream() != null) {
            JavaScriptResourceRegistry.getInstance().setHighchartsReference(HighstockJsReference.INSTANCE);
            JavaScriptResourceRegistry.getInstance().setHighchartsExportingReference(
                    HighstockExportingJsReference.INSTANCE);
            JavaScriptResourceRegistry.getInstance().setHighchartsMoreReference(HighstockMoreJsReference.INSTANCE);
            JavaScriptResourceRegistry.getInstance().setJQueryReference(
                    AWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference());
            initialized = true;
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void assertInitialized() {
        Assertions.assertThat(initialized)
                .as("Please add the highstock webjar to the classpath in order to use the optional highstock components.")
                .isTrue();
    }

}
