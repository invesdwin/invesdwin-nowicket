package de.invesdwin.nowicket.generated.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;

/**
 * Use this to make a nested model get binded as a panel.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@BeanPathEndPoint
public @interface Panel {

}
