package de.invesdwin.nowicket.generated.binding.processor.element;

import java.io.Serializable;
import java.text.Format;
import java.util.Locale;

import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

public interface IHtmlElement<E extends IModelElement<?>, M> extends Serializable {

    String ATTR_TYPE = "type";
    String ATTR_WICKET_ID = "wicket:id";

    boolean isModelElement();

    E getModelElement();

    Element getElement();

    HtmlContext getContext();

    boolean accept(IHtmlVisitor... visitors);

    String getWicketId();

    IModel<M> getModel();

    IModel<Object> getRootObjectModel();

    IModel<Object> getTargetObjectModel();

    boolean isEager();

    Format getFormat(Locale locale);

    boolean isForced();

    boolean isModalCloser();

    IModel<String> getTitleModel(IModel<Object> targetObjectModel);

    IModel<String> getTitleModel();

    IModel<String> getTooltipModel(IModel<Object> targetObjectModel);

    boolean isEnabled(IModel<Object> targetObjectModel);

    boolean isVisible(IModel<Object> targetObjectModel);

}
