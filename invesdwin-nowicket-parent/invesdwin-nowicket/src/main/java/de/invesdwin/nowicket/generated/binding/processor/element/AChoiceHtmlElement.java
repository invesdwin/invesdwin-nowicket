package de.invesdwin.nowicket.generated.binding.processor.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.spi.element.utility.ContainerTitleBeanPathElement;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ChoiceTabTitleModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.tab.ModelTab;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.SelectionModifierModel;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.util.collections.delegate.DelegateList;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public abstract class AChoiceHtmlElement<E extends AChoiceModelElement<?>> extends AModelHtmlElement<E, Object>
        implements ITabbedHtmlElement<E, Object> {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(AChoiceHtmlElement.class);

    public AChoiceHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public IModel<Object> getModel() {
        return new BeanPathModel<Object>(this);
    }

    public IModel<List<Object>> getChoiceModel() {
        return new IModel<List<Object>>() {
            @SuppressWarnings("unchecked")
            @Override
            public List<Object> getObject() {
                final IModel<Object> rootObjectModel = getRootObjectModel();
                final List<Object> list = (List<Object>) getModelElement().getBeanPathElement()
                        .getChoiceModifier()
                        .getValueFromRoot(rootObjectModel.getObject());
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

                @Override
                public String getIdValue(final Enum object, final int index) {
                    if (object == null) {
                        //super is not null safe with CheckBoxMultipleChoice
                        return null;
                    } else {
                        return super.getIdValue(object, index);
                    }
                }
            };
        } else {
            return new ChoiceRenderer<Object>(ContainerTitleBeanPathElement.CONTAINER_TITLE_BEAN_PATH_FRAGMENT) {
                @Override
                public Object getDisplayValue(final Object object) {
                    if (object == null) {
                        return null;
                    }
                    Object displayValue;
                    try {
                        displayValue = super.getDisplayValue(object);
                    } catch (final Throwable t) {
                        //ignore when no title container method exists:
                        //org.apache.wicket.WicketRuntimeException: No get method defined for class: ... expression: title
                        displayValue = object;
                    }
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
                                            .setDefaultValue(displayValueString)
                                            .getObject();
                        } catch (final MissingResourceException e) {
                            return displayValueString;
                        }
                    }
                }
            };
        }
    }

    public boolean isSingleSelection() {
        return getModelElement().getBeanPathElement().isSingleSelection();
    }

    public boolean isMultiSelection() {
        return getModelElement().getBeanPathElement().isMultiSelection();
    }

    public IModel<List<Object>> getSelectionModel() {
        return new SelectionModifierModel(this);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<ITab> createWicketTabs() {
        return new DelegateList<ITab>(null) {
            @Override
            public List<ITab> getDelegate() {
                final List<ITab> tabs = new ArrayList<ITab>();
                for (final Object row : getChoiceModel().getObject()) {
                    final IModel<Object> rowObjectModel = (IModel) Model.of((Serializable) row);
                    final IModel<String> tabTitleModel = new ChoiceTabTitleModel(AChoiceHtmlElement.this,
                            rowObjectModel);
                    //cannot be delegated to BindingBuilder since it might be required in a model that gets refreshed each request cycle
                    final ITab tab = new ModelTab(AChoiceHtmlElement.this, tabTitleModel, rowObjectModel,
                            getContext().getRootObjectModel(), rowObjectModel);
                    tabs.add(tab);
                }
                return tabs;
            }

            @Override
            public int size() {
                return getChoiceModel().getObject().size();
            }

            @Override
            public boolean isEmpty() {
                return getChoiceModel().getObject().isEmpty();
            }
        };

    }

    @Override
    public IModel<? extends List<? extends ITab>> getTabModel() {
        return new IModel<List<? extends ITab>>() {
            @Override
            public List<? extends ITab> getObject() {
                return createWicketTabs();
            }
        };
    }

}
