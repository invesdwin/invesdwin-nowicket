package de.invesdwin.nowicket.generated.binding.processor.element;

import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.impl.clazz.BeanClassContainer;
import de.invesdwin.norva.beanpath.spi.element.IActionBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IPropertyBeanPathElement;
import de.invesdwin.nowicket.component.modal.ModalContainer;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.TitleModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public abstract class AModelHtmlElement<E extends IModelElement<?>, M> extends AHtmlElement<E, M> {

    public static final String VALUE = "value";
    public static final String VALUE_PLACEHOLDER = "$(" + VALUE + ")";

    protected IModel<Object> targetObjectModel;
    private transient E modelElement;

    public AModelHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    public AModelHtmlElement(final HtmlContext context, final String wicketId) {
        super(context, wicketId);
    }

    @Override
    public boolean isModelElement() {
        return true;
    }

    protected String getFormatString() {
        final IBeanPathElement beanPathElement = getModelElement().getBeanPathElement();
        if (!beanPathElement.isProperty()) {
            return null;
        }
        final IPropertyBeanPathElement property = (IPropertyBeanPathElement) getModelElement().getBeanPathElement();
        return property.getFormatString();
    }

    @Override
    public boolean isEager() {
        final IBeanPathElement beanPathElement = getModelElement().getBeanPathElement();
        if (!beanPathElement.isProperty()) {
            return false;
        }
        final IPropertyBeanPathElement property = (IPropertyBeanPathElement) getModelElement().getBeanPathElement();
        return property.isEager();
    }

    @Override
    public boolean isForced() {
        return getModelElement().getBeanPathElement().isForced();
    }

    @Override
    public boolean isModalCloser() {
        if (!getModelElement().getBeanPathElement().isAction()) {
            return false;
        }
        final IActionBeanPathElement action = (IActionBeanPathElement) getModelElement().getBeanPathElement();
        return action.isModalCloser();
    }

    @Override
    public boolean isModalOpener() {
        if (!getModelElement().getBeanPathElement().isAction()) {
            return false;
        }
        final IActionBeanPathElement action = (IActionBeanPathElement) getModelElement().getBeanPathElement();
        return action.isModalOpener();
    }

    @Override
    public IModel<String> getTitleModelFromTarget(final IModel<Object> targetObjectModel) {
        return new TitleModel(getWicketId(), this, targetObjectModel,
                getModelElement().getBeanPathElement().getAccessor().getBeanPathFragment());
    }

    @Override
    public IModel<String> getTitleModel() {
        return new TitleModel(getWicketId(), this);
    }

    @Override
    public IModel<M> getModel() {
        return new BeanPathModel<M>(this);
    }

    @Override
    public IModel<Object> getTargetObjectModel() {
        if (targetObjectModel == null) {
            targetObjectModel = new LoadableDetachableModel<Object>() {
                @Override
                protected Object load() {
                    final Object rootObject = getContext().getModelObjectContext().getModelObject();
                    final BeanClassContainer container = getModelElement().getBeanPathElement()
                            .getContainer()
                            .unwrap(BeanClassContainer.class);
                    return container.getTargetFromRoot(rootObject);
                }
            };
        }
        return targetObjectModel;
    }

    @Override
    public IModel<Object> getRootObjectModel() {
        return getContext().getRootObjectModel();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getModelElement() {
        if (modelElement == null) {
            modelElement = (E) getContext().getModelObjectContext().getElementRegistry().getElement(getWicketId());
        }
        return modelElement;
    }

    @Override
    protected void onFirstAccept() {
        super.onFirstAccept();
        Assertions.assertThat(getModelElement()).isNotNull();
    }

    @Override
    public IModel<String> getTooltipModelFromTarget(final IModel<Object> rootObjectModel,
            final IModel<Object> targetObjectModel) {
        return new IModel<String>() {
            @Override
            public String getObject() {
                final String tooltip = getModelElement().getBeanPathElement()
                        .getTooltipFromTarget(rootObjectModel, targetObjectModel.getObject());
                if (tooltip == null) {
                    return null;
                } else {
                    try {
                        return new StringResourceModel(tooltip, getContext().getMarkupContainer(),
                                getContext().getMarkupContainer().getDefaultModel()).setDefaultValue(tooltip)
                                        .getObject();
                    } catch (final MissingResourceException e) {
                        return tooltip;
                    }
                }
            }
        };
    }

    @Override
    public boolean isEnabledFromTarget(final IModel<Object> rootObjectModel, final IModel<Object> targetObjectModel) {
        final boolean enabled = getModelElement().getBeanPathElement()
                .isEnabledFromTarget(rootObjectModel.getObject(), targetObjectModel.getObject())
                && !isHiddenByModalContainer();
        return enabled;
    }

    protected boolean isHiddenByModalContainer() {
        final MarkupContainer markupContainer = getContext().getMarkupContainer();
        final ModalContainer parentModalContainer = markupContainer.findParent(ModalContainer.class);
        if (parentModalContainer != null && !parentModalContainer.isShowing()) {
            //this modal container is not showing this component currently
            return true;
        }
        final Boolean showingChildModalContainerFound = markupContainer.visitChildren(ModalContainer.class,
                new IVisitor<ModalContainer, Boolean>() {
                    @Override
                    public void component(final ModalContainer object, final IVisit<Boolean> visit) {
                        if (object.isShowing()) {
                            //a child modal container is blocking this component currently
                            visit.stop(true);
                        }
                    }
                });
        return showingChildModalContainerFound != null && showingChildModalContainerFound;
    }

    @Override
    public boolean isVisibleFromTarget(final IModel<Object> rootObjectModel, final IModel<Object> targetObjectModel) {
        return getModelElement().getBeanPathElement()
                .isVisibleFromTarget(rootObjectModel.getObject(), targetObjectModel.getObject());
    }

}
