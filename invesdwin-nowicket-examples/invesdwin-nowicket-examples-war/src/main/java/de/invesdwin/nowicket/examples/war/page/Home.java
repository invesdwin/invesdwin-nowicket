package de.invesdwin.nowicket.examples.war.page;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@GeneratedMarkup
@NotThreadSafe
public class Home implements Serializable {

    public String getHello() {
        return "NoWicket!";
    }

}
