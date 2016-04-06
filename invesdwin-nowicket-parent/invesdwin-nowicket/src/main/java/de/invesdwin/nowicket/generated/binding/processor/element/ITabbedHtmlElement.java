package de.invesdwin.nowicket.generated.binding.processor.element;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

public interface ITabbedHtmlElement<E extends IModelElement<?>, M> extends IHtmlElement<E, M> {

    List<ITab> createWicketTabs();

    IModel<? extends Collection<? extends ITab>> getTabModel();

}
