package de.invesdwin.nowicket.generated.markup;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.classpath.ClassPathScanner;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.lang.reflection.Reflections;

@NotThreadSafe
public abstract class AAnnotatedGeneratedMarkup {

    public static final File[] DEFAULT_POSSIBLE_DIRECTORIES = { new File("src/main/java"), new File("src/test/java") };
    private final File[] possibleDirectories;
    //though it is recommended to extend AValueObject
    private Class<?> assertBaseClass = Serializable.class;

    public AAnnotatedGeneratedMarkup(final File... possibleDirectories) {
        Assertions.assertThat(possibleDirectories).isNotEmpty();
        this.possibleDirectories = possibleDirectories;
    }

    public AAnnotatedGeneratedMarkup() {
        this(DEFAULT_POSSIBLE_DIRECTORIES);
    }

    public AAnnotatedGeneratedMarkup setAssertBaseClass(final Class<?> assertBaseClass) {
        this.assertBaseClass = assertBaseClass;
        return this;
    }

    /**
     * by overriding this, one can redirect output to src/main/resources for src/main/java
     */
    protected File redirectDestination(final File sourceDirectory) {
        return sourceDirectory;
    }

    public void generate() {
        final ClassPathScanner scanner = new ClassPathScanner();
        scanner.addIncludeFilter(new AnnotationTypeFilter(GeneratedMarkup.class));

        final ALoadingCache<File, Set<Class<?>>> directory_modelClasses = new ALoadingCache<File, Set<Class<?>>>() {
            @Override
            protected Set<Class<?>> loadValue(final File key) {
                return new HashSet<Class<?>>();
            }
        };
        for (final String basePackage : getClasspathBasePackages()) {
            for (final BeanDefinition beanDefinition : scanner.findCandidateComponents(basePackage)) {
                final Class<?> modelClass = Reflections.classForName(beanDefinition.getBeanClassName());
                for (final File possibleDirectory : possibleDirectories) {
                    final String modelClassPath = modelClass.getName().replace(".", "/")
                            + ModelClassContext.FILE_ENDING_JAVA;
                    final File modelClassFile = new File(possibleDirectory, modelClassPath);
                    if (modelClassFile.exists()) {
                        Assertions.assertThat(directory_modelClasses.get(possibleDirectory).add(modelClass)).isTrue();
                        break;
                    }
                }
            }
        }
        for (final Entry<File, Set<Class<?>>> entry : directory_modelClasses.entrySet()) {
            final File directory = entry.getKey();
            Set<Class<?>> modelClasses = entry.getValue();
            if (assertBaseClass != null) {
                for (final Class<?> modelClass : modelClasses) {
                    Assertions.assertThat(assertBaseClass).isAssignableFrom(modelClass);
                }
            }
            modelClasses = filterModelClasses(modelClasses);
            final File destination = redirectDestination(directory);
            new SpecifiedGeneratedMarkup(destination, modelClasses).generate();
        }
    }

    protected Set<Class<?>> filterModelClasses(final Set<Class<?>> modelClasses) {
        return modelClasses;
    }

    protected abstract Set<String> getClasspathBasePackages();

}
