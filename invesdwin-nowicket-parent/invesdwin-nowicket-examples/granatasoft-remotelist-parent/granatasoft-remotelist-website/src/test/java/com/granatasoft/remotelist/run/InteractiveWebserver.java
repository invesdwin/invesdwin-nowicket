package com.granatasoft.remotelist.run;

import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.Test;

import de.invesdwin.context.test.ATest;
import de.invesdwin.context.webserver.test.WebserverTest;

@NotThreadSafe
@WebserverTest
public class InteractiveWebserver extends ATest {

    @Override
    public void setUpOnce() throws Exception {
        super.setUpOnce();

        // call the preparer to seed default data.
        new SampleDataPreparer().prepare();
    }

    @Test
    public void waitTest() throws InterruptedException {
        TimeUnit.DAYS.sleep(Long.MAX_VALUE);
    }
}
