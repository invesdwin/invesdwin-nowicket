package de.invesdwin.nowicket.examples.pages.home;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@NotThreadSafe
@GeneratedMarkup
public class Home implements Serializable {
    public String getHello() {
        return "Hello NoWicket";
    }
}
