package de.invesdwin.nowicket.examples;

import java.util.Set;

import de.invesdwin.nowicket.examples.internal.ExampleWebApplication;
import de.invesdwin.nowicket.generated.markup.AAnnotatedGeneratedMarkup;

public class ExampleMarkupGenerator extends AAnnotatedGeneratedMarkup {
	public static void main(String[] args) {
		new ExampleMarkupGenerator().generate();
	}

	@Override
	protected Set<String> getClasspathBasePackages() {
		return ExampleWebApplication.BASE_PACKAGE;
	}
}
