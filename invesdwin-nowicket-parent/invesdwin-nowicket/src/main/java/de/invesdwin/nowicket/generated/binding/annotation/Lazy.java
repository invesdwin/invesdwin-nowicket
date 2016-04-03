package de.invesdwin.nowicket.generated.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a property to get lazy binding, even if it is supposed to be eager from the outside. This annotation has a
 * higher priority than @Eager if it nearer than it in the bean path.
 * 
 * Default is lazy synchronization, thus properties get synchronized on some button click.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Lazy {

}
