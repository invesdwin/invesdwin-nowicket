package de.invesdwin.nowicket.application;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.util.lang.string.Strings;

@ThreadSafe
public final class PanelFactory {

    private static final String PANEL_CLASS_MODEL_SUFFIX = Panel.class.getSimpleName();
    private static final PanelFactory INSTANCE = new PanelFactory();

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(PanelFactory.class);

    private PanelFactory() {
    }

    public static PanelFactory get() {
        return INSTANCE;
    }

    public Panel getPanel(final String wicketId, final Object modelObject) {
        final Class<Panel> panelClass = findPanelClass(modelObject.getClass());
        if (panelClass == null) {
            throw new IllegalStateException("Unable to find any " + PANEL_CLASS_MODEL_SUFFIX + " for model class ["
                    + modelObject.getClass().getName() + "] or any of its super classes!");
        }
        return tryIModelConstructor(wicketId, modelObject, panelClass);
    }

    private Panel tryIModelConstructor(final String wicketId, final Object modelObject, final Class<Panel> panelClass) {
        try {
            final Constructor<Panel> constructor = panelClass.getConstructor(String.class, IModel.class);
            return constructor.newInstance(wicketId, Model.of((Serializable) modelObject));
        } catch (final NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException e) {
            return tryNoModelConstructor(wicketId, modelObject, panelClass, e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Panel tryNoModelConstructor(final String wicketId, final Object modelObject, final Class<Panel> panelClass,
            final Exception parentE) {
        try {
            final Constructor<Panel> constructor = panelClass.getConstructor(String.class, modelObject.getClass());
            return constructor.newInstance(wicketId, modelObject);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            LOG.catching(parentE);
            throw new RuntimeException(e);
        }
    }

    public Class<Panel> findPanelClass(final Object modelObject) {
        if (modelObject == null) {
            return null;
        } else {
            return findPanelClass(modelObject.getClass());
        }
    }

    public Class<Panel> findPanelClass(final Class<?> modelClass) {
        if (modelClass == null) {
            return null;
        }
        try {
            String relModelPath = modelClass.getName();
            final String modelNameSuffix = ModelClassContext.extractModelNameSuffix(modelClass);
            if (Strings.isNotBlank(modelNameSuffix)) {
                relModelPath = Strings.removeEnd(relModelPath, modelNameSuffix);
            }
            return (Class<Panel>) Class.forName(relModelPath + PANEL_CLASS_MODEL_SUFFIX);
        } catch (final ClassNotFoundException e) {
            return findPanelClass(modelClass.getSuperclass());
        }
    }

}
