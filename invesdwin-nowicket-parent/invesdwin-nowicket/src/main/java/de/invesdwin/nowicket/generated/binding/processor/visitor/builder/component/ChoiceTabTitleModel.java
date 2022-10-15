package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.lang.reflect.Method;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.norva.beanpath.spi.element.RootBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.utility.ContainerTitleBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;
import de.invesdwin.util.lang.reflection.Reflections;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class ChoiceTabTitleModel extends TitleModel {

    public ChoiceTabTitleModel(final AChoiceHtmlElement element, final IModel<Object> targetObjectModel) {
        super(element.getWicketId(), element, targetObjectModel, RootBeanPathElement.ROOT_BEAN_PATH);
    }

    @Override
    protected boolean hasTitleUtilityElement() {
        //always give returned value preference
        return true;
    }

    private Method getTitleMethod(final Object target) {
        if (target == null) {
            return null;
        }
        final Method titleMethod = Reflections.findMethod(target.getClass(),
                ContainerTitleBeanPathElement.CONTAINER_TITLE_BEAN_PATH_FRAGMENT);
        return titleMethod;
    }

    @Override
    protected String getTitle() {
        final Object target = getTitleTarget();
        final Method titleMethod = getTitleMethod(target);
        if (titleMethod != null) {
            final Object value = Reflections.invokeMethod(titleMethod, target);
            return Strings.asString(value);
        } else {
            return Strings.asString(target);
        }
    }

}
