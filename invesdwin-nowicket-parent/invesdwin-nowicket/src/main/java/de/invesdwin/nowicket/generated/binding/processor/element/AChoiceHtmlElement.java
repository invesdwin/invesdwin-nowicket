package de.invesdwin.nowicket.generated.binding.processor.element;

import java.util.HashSet;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.SelectionModifierModel;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public abstract class AChoiceHtmlElement<E extends AChoiceModelElement<?>> extends AModelHtmlElement<E, Object> {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(AChoiceHtmlElement.class);

    public AChoiceHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public IModel<Object> getModel() {
        return new BeanPathModel<Object>(this);
    }

    public IModel<List<Object>> getChoiceModel() {
        return new AbstractReadOnlyModel<List<Object>>() {

            @Override
            public List<Object> getObject() {
                final List<Object> list = (List<Object>) getModelElement().getBeanPathElement()
                        .getChoiceModifier()
                        .getValue();
                //check before removing null
                maybeCheckDuplicateRenderedStringsInDevMode(list);
                //setNullValid Behavior in ModelComponentBehavior already handles null properly; ListMultipleChoice does not support null selection
                list.remove(null);
                return list;
            }

        };
    }

    /**
     * Check only in dev mode for performance reasons.
     */
    private void maybeCheckDuplicateRenderedStringsInDevMode(final List<Object> choiceModel) {
        if (ABaseWebApplication.get().usesDevelopmentConfig()) {
            final IChoiceRenderer<Object> renderer = getChoiceRenderer();
            final Set<String> duplicateFilter = new HashSet<String>();
            for (final Object o : choiceModel) {
                final Object displayValue = renderer.getDisplayValue(o);
                final String displayValueStr;
                if (displayValue == null) {
                    //wicket renders null as empty string
                    displayValueStr = Strings.EMPTY;
                } else {
                    displayValueStr = String.valueOf(displayValue);
                }
                if (!duplicateFilter.add(displayValueStr)) {
                    //CHECKSTYLE:OFF
                    LOG.warn("Duplicate displayValue \"{}\" in choice model for element {}." //
                            + "\nPossible causes are: " //
                            + "\n - duplicate objects returned by choice method (current selected value gets added to choice aswell)" //
                            + "\n - default hashCode/equals implementation in objects causing multiple instances of the same displayValue to be treated unequal" //
                            + "\n - wrong toString implementation in the objects" //
                            + "\n - localization properties contain duplicates", //
                            displayValueStr, toString());
                    //CHECKSTYLE:ON
                }
            }
        }
    }

    public IChoiceRenderer<Object> getChoiceRenderer() {
        return newChoiceRenderer();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private IChoiceRenderer<Object> newChoiceRenderer() {
        if (getModelElement().getBeanPathElement().getAccessor().getType().isEnum()) {
            return new EnumChoiceRenderer(getContext().getMarkupContainer()) {
                @Override
                public Object getDisplayValue(final Enum object) {
                    if (object == null) {
                        return null;
                    }
                    Object displayValue;
                    try {
                        //1. try default enum property
                        displayValue = super.getDisplayValue(object);
                    } catch (final MissingResourceException e) {
                        displayValue = null;
                    }
                    if (displayValue != null) {
                        return displayValue;
                    } else {
                        //3. use enums toString() and lookup string value in resources since it might return some other property
                        final String objectString = object.toString();
                        if (objectString == null) {
                            return null;
                        }
                        try {
                            return new StringResourceModel(objectString, getContext().getMarkupContainer(),
                                    getContext().getMarkupContainer().getDefaultModel()).setDefaultValue(objectString)
                                            .getObject();
                        } catch (final MissingResourceException e) {
                            return objectString;
                        }
                    }
                }
            };
        } else {
            return new ChoiceRenderer<Object>() {
                @Override
                public Object getDisplayValue(final Object object) {
                    if (object == null) {
                        return null;
                    }
                    final Object displayValue = super.getDisplayValue(object);
                    if (displayValue == null) {
                        return null;
                    } else {
                        final String displayValueString = displayValue.toString();
                        if (displayValueString == null) {
                            return null;
                        }
                        try {
                            //lookup string value in resources, thus objects toString() might define some other property
                            return new StringResourceModel(displayValueString, getContext().getMarkupContainer(),
                                    getContext().getMarkupContainer().getDefaultModel())
                                            .setDefaultValue(displayValueString).getObject();
                        } catch (final MissingResourceException e) {
                            return displayValueString;
                        }
                    }
                }
            };
        }
    }

    public boolean isMultiSelect() {
        return getModelElement().getBeanPathElement().isMultiSelect();
    }

    public IModel<List<Object>> getSelectionModel() {
        return new SelectionModifierModel(this);
    }
}
