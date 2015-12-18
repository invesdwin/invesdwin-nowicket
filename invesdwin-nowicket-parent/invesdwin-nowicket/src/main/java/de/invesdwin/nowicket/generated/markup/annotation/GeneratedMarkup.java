package de.invesdwin.nowicket.generated.markup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A Model with this annotation will get markup generated for it.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedMarkup {

    String DEFAULT_MODEL_CLASS_NAME_SUFFIX = "_DEFAULT_";

    /**
     * This is a suffix that will be removed from the model class name when trying to resolve page or panel classes.
     */
    String modelClassNameSuffix() default DEFAULT_MODEL_CLASS_NAME_SUFFIX;

}
