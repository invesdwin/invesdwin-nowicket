package de.invesdwin.nowicket.examples.guide.pages.wicket.helloworld;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@NotThreadSafe
@GeneratedMarkup
public class HelloWorld implements Serializable {
    public String getHello() {
        return "Hello NoWicket!";
    }
}
