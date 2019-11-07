package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.invesdwin.nowicket.generated.binding.processor.element.AModelHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class TitleModel implements IModel<String> {

    private final String wicketId;
    private final AModelHtmlElement<?, ?> element;
    private final IModel<Object> targetObjectModel;
    private final IModel<Object> propertyModel;

    public TitleModel(final String wicketId, final AModelHtmlElement<?, ?> element) {
        this.wicketId = wicketId;
        this.element = element;
        this.targetObjectModel = element.getTargetObjectModel();
        this.propertyModel = new BeanPathModel<Object>(element);
    }

    public TitleModel(final String wicketId, final AModelHtmlElement<?, ?> element,
            final IModel<Object> targetObjectModel, final String propertyPath) {
        this.wicketId = wicketId;
        this.element = element;
        this.targetObjectModel = targetObjectModel;
        this.propertyModel = new BeanPathModel<Object>(targetObjectModel, propertyPath);
    }

    @Override
    public String getObject() {
        if (!element.isVisible(targetObjectModel)) {
            return null;
        }
        final String title = getTitle();
        if (title == null) {
            return null;
        }
        try {
            if (hasTitleUtilityElement() && !isVisibleName(title)) {
                //title() or getXYZTitle() method has priority
                String str = new StringResourceModel(title, element.getContext().getMarkupContainer(),
                        element.getContext().getMarkupContainer().getDefaultModel()).setDefaultValue(title).getObject();
                str = postProcessTitle(str);
                return str;
            } else {
                //properties have priority over static title or title annotation
                String str = new StringResourceModel(wicketId, element.getContext().getMarkupContainer(),
                        element.getContext().getMarkupContainer().getDefaultModel()).setDefaultValue(title).getObject();
                str = postProcessTitle(str);
                return str;
            }
        } catch (final MissingResourceException e) {
            return postProcessTitle(title);
        }
    }

    protected boolean hasTitleUtilityElement() {
        return element.getModelElement().getBeanPathElement().getTitleElement() != null
                || element.getModelElement().getBeanPathElement().getContainerTitleElement() != null;
    }

    protected String getTitle() {
        return element.getModelElement().getBeanPathElement().getTitle(getTitleTarget());
    }

    protected boolean isVisibleName(final String title) {
        return Objects.equals(element.getModelElement().getBeanPathElement().getVisibleName(), title);
    }

    protected Object getTitleTarget() {
        if (targetObjectModel == null) {
            return null;
        } else {
            return targetObjectModel.getObject();
        }
    }

    protected String postProcessTitle(final String title) {
        String processedTitle = title;
        if (Strings.contains(title, AModelHtmlElement.VALUE_PLACEHOLDER)) {
            processedTitle = processedTitle.replace(AModelHtmlElement.VALUE_PLACEHOLDER,
                    Strings.asString(propertyModel.getObject()));
        }
        return processedTitle;
    }

}
