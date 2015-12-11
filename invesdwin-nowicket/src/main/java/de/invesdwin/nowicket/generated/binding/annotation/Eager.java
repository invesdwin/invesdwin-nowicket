package de.invesdwin.nowicket.generated.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a property to get eager binding, thus receive synchronization right after the value has been changed.
 * 
 * Default is lazy synchronization, thus properties get synchronized on some button click.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Eager {

}
