package de.invesdwin.nowicket.generated.markup.processor.element;

import de.invesdwin.norva.beanpath.spi.element.AActionBeanPathElement;

public interface IButtonModelElement<E extends AActionBeanPathElement> extends IModelElement<E> {

    /**
     * Per default there is no icon on buttons.
     */
    String NO_ICON_CSS_CLASS = "";
    String ICON_CSS_CLASS_PROPERTY_NAME_SUFFIX = ".iconCssClass";

    String getIconCssClassPropertyName();

    String getDefaultIconCssClass();

}
