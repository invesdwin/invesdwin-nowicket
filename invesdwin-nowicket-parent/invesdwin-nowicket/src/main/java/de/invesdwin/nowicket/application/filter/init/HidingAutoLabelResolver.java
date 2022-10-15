package de.invesdwin.nowicket.application.filter.init;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.AutoLabelResolver;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.resolver.IComponentResolver;
import org.apache.wicket.model.IModel;

import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.lang.reflection.Reflections;
import de.invesdwin.util.lang.string.Strings;

@ThreadSafe
public class HidingAutoLabelResolver implements IComponentResolver {

    private static final ALoadingCache<Class<?>, MethodHandle> AUTOLABEL_COMPONENT_FIELD = new ALoadingCache<Class<?>, MethodHandle>() {

        @Override
        protected MethodHandle loadValue(final Class<?> key) {
            final Field componentField = Reflections.findField(key, "component");
            Reflections.makeAccessible(componentField);
            try {
                final MethodHandle methodHandle = MethodHandles.lookup().unreflectGetter(componentField);
                return methodHandle;
            } catch (final IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private final AutoLabelResolver delegate;

    public HidingAutoLabelResolver(final AutoLabelResolver delegate) {
        this.delegate = delegate;
    }

    @Override
    public Component resolve(final MarkupContainer container, final MarkupStream markupStream, final ComponentTag tag) {
        try {
            final Component autoLabel;
            try {
                autoLabel = delegate.resolve(container, markupStream, tag);
            } catch (final Throwable t) {
                //e.g. ComponentNotFoundException
                return null;
            }
            if (autoLabel == null) {
                return null;
            }
            final MethodHandle componentGetter = AUTOLABEL_COMPONENT_FIELD.get(autoLabel.getClass());
            final Component relatedComponent = (Component) componentGetter.invoke(autoLabel);
            autoLabel.setVisible(isVisible(relatedComponent));
            return autoLabel;
        } catch (final Throwable e) {
            throw new RuntimeException("At: " + tag, e);
        }
    }

    private boolean isVisible(final Component relatedComponent) {
        if (!relatedComponent.isVisibleInHierarchy()) {
            return false;
        }
        if (relatedComponent instanceof FormComponent) {
            final FormComponent<?> cRelatedComponent = (FormComponent<?>) relatedComponent;
            final IModel<String> label = cRelatedComponent.getLabel();
            if (label != null && Strings.isBlank(label.getObject())) {
                return false;
            }
        }
        return true;
    }

}
