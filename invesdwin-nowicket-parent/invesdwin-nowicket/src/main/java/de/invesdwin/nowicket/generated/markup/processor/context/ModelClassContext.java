package de.invesdwin.nowicket.generated.markup.processor.context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.GeneratedBindingDefaults;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.norva.beanpath.impl.clazz.BeanClassContext;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.lang.reflection.Reflections;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class ModelClassContext extends AModelContext {

    public static final String FILE_ENDING_HTML = ".html";
    public static final String FILE_ENDING_JAVA = ".java";

    private final File destination;
    private final BeanClassContext beanClassContext;
    private final String modelNameSuffix;
    private final ALoadingCache<MarkupType, File> markupType_propertiesFile = new ALoadingCache<MarkupType, File>() {
        @Override
        protected File loadValue(final MarkupType key) {
            final File htmlFile = getHtmlFile(key);
            final String replaced = Strings.replaceEnd(htmlFile.getAbsolutePath(), FILE_ENDING_HTML, ".properties");
            return new File(replaced);
        }
    };
    private final ALoadingCache<MarkupType, File> markupType_htmlFile = new ALoadingCache<MarkupType, File>() {

        @Override
        protected File loadValue(final MarkupType key) {
            String relModelPath = getModelClass().getName().replace(".", "/");
            if (Strings.isNotBlank(modelNameSuffix)) {
                relModelPath = Strings.removeEnd(relModelPath, modelNameSuffix);
            }
            final String markupClassSuffix = key.toString();
            final String relFilePath = relModelPath + markupClassSuffix + FILE_ENDING_HTML;
            return new File(getDestination(), relFilePath);
        }
    };

    public ModelClassContext(final File destination, final Class<?> modelClass) {
        this.destination = destination;
        this.beanClassContext = new BeanClassContext(modelClass);
        this.modelNameSuffix = extractModelNameSuffix(modelClass);
    }

    public static String extractModelNameSuffix(final Class<?> modelClass) {
        final GeneratedMarkup generatedMarkup = Reflections.getAnnotation(modelClass, GeneratedMarkup.class);
        String modelNameSuffix = GeneratedMarkup.DEFAULT_MODEL_CLASS_NAME_SUFFIX;
        if (generatedMarkup != null) {
            modelNameSuffix = generatedMarkup.modelClassNameSuffix();
        }
        if (GeneratedMarkup.DEFAULT_MODEL_CLASS_NAME_SUFFIX.equals(modelNameSuffix)) {
            return GeneratedBindingDefaults.get().getDefaultModelClassNameSuffix();
        } else {
            return modelNameSuffix;
        }
    }

    public File getDestination() {
        return destination;
    }

    public Class<?> getModelClass() {
        return getBeanClassContext().getRootContainer().getType().getType();
    }

    public BeanClassContext getBeanClassContext() {
        return beanClassContext;
    }

    public File getPropertiesFile(final MarkupType markupType) {
        return markupType_propertiesFile.get(markupType);
    }

    public File getHtmlFile(final MarkupType type) {
        return markupType_htmlFile.get(type);
    }

    public List<MarkupType> getExistingMarkupTypes() {
        final List<MarkupType> availableMarkupTypes = new ArrayList<MarkupType>();
        for (final MarkupType markupType : MarkupType.values()) {
            String relModelPath = getModelClass().getName().replace(".", "/");
            if (Strings.isNotBlank(modelNameSuffix)) {
                relModelPath = Strings.removeEnd(relModelPath, modelNameSuffix);
            }
            final String markupClassSuffix = markupType.toString();
            for (final String fileEnding : new String[] { FILE_ENDING_JAVA, FILE_ENDING_HTML }) {
                final String relFilePath = relModelPath + markupClassSuffix + fileEnding;
                final File file = new File(getDestination(), relFilePath);
                final boolean foundFileEnding;
                if (file.exists()) {
                    foundFileEnding = true;
                } else if (FILE_ENDING_JAVA.equals(fileEnding)) {
                    foundFileEnding = Reflections.classExists(getModelClass().getName() + markupClassSuffix);
                } else {
                    foundFileEnding = false;
                }
                if (foundFileEnding) {
                    availableMarkupTypes.add(markupType);
                    break;
                }
            }
        }
        return availableMarkupTypes;
    }
}
