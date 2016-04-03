package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import org.apache.wicket.Component;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

/**
 * A marker interface to allow components to override eager settings.
 *
 */
public interface IEagerFilter {

    boolean isEagerAllowed(IHtmlElement<?, ?> element, Component component);

}
