package com.granatasoft.remotelist.run;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.PlatformInitializerProperties;
import de.invesdwin.context.client.wicket.generated.markup.AnnotatedGeneratedMarkup;

@NotThreadSafe
public final class GeneratedMarkupMain {

    private GeneratedMarkupMain() {}

    public static void main(final String[] args) {
        PlatformInitializerProperties.setAllowed(false);
        new AnnotatedGeneratedMarkup().generate();
    }
}
