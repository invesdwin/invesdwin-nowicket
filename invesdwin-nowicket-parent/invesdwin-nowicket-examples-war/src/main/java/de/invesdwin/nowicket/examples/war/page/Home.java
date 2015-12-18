package de.invesdwin.nowicket.examples.war.page;

import java.io.Serializable;

import org.apache.wicket.request.flow.RedirectToUrlException;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@GeneratedMarkup
public class Home implements Serializable {

	public void showInternalErrorPage() {
		throw new RuntimeException("sample error");
	}

	public void showPageNotFoundPage() {
		throw new RedirectToUrlException("/unknownPage");
	}

}
