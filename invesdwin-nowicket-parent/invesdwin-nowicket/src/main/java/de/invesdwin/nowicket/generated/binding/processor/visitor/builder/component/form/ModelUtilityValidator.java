package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;

import de.invesdwin.norva.beanpath.spi.element.IValidatableBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.UploadButtonBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.FileUploadModel;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.time.fdate.FDate;

@NotThreadSafe
public final class ModelUtilityValidator implements INullAcceptingValidator<Object> {

    public static final String TITLE = "title";
    public static final String TITLE_PLACEHOLDER = "$(" + TITLE + ")";
    public static final String TITLE_SURROUNDING = "'";

    private final IHtmlElement<?, ?> element;
    private final Component component;

    private ModelUtilityValidator(final IHtmlElement<?, ?> element, final Component component) {
        this.element = element;
        this.component = component;
    }

    @Override
    public void validate(final IValidatable<Object> validatable) {
        try {
            final IValidatableBeanPathElement cElement = (IValidatableBeanPathElement) element.getModelElement()
                    .getBeanPathElement();
            final Object validatableValue = Components.getValidatableValue(component, validatable);
            final Object convertedValidatableValue = convertValidatableValueToBeanPathValue(cElement, validatableValue);
            final String message = cElement.getValidateElement().validate(convertedValidatableValue);
            if (Strings.isNotBlank(message)) {
                //use translated key or use message as fallback if no translation found
                final String normalizedMessage = normalizeMessage(message);
                final ValidationError error = new ValidationError(normalizedMessage);
                error.addKey(normalizedMessage);
                error.setVariable(TITLE, surroundTitle(element.getTitleModel().getObject()));
                validatable.error(error);
            }
        } finally {
            GuiService.get().processRequestFinally(component);
        }
    }

    @SuppressWarnings("unchecked")
    public static Object convertValidatableValueToBeanPathValue(final IValidatableBeanPathElement beanPathElement,
            final Object validatableValue) {
        if (validatableValue == null) {
            return null;
        } else if (validatableValue instanceof Date) {
            final Class<?> expectedType = beanPathElement.getValidatableType().getType();
            final Date date = (Date) validatableValue;
            if (Date.class.isAssignableFrom(expectedType)) {
                return date;
            } else if (Calendar.class.isAssignableFrom(expectedType)) {
                final Calendar cal = FDate.valueOf(date).calendarValue();
                return cal;
            } else if (FDate.class.isAssignableFrom(expectedType)) {
                final FDate fdate = FDate.valueOf(date);
                return fdate;
            }
        } else if (beanPathElement instanceof UploadButtonBeanPathElement) {
            //only validate on file name, do not upload the files yet
            final UploadButtonBeanPathElement uploadButtonBeanPathElement = (UploadButtonBeanPathElement) beanPathElement;
            final List<FileUpload> filesUploads = (List<FileUpload>) validatableValue;
            if (!uploadButtonBeanPathElement.isMultiUpload()) {
                if (filesUploads.size() > 0) {
                    org.assertj.core.api.Assertions.assertThat(filesUploads.size())
                            .as("MultiUpload is not supported!")
                            .isEqualTo(1);
                    return FileUploadModel.newFile(filesUploads.get(0));
                } else {
                    return null;
                }
            } else {
                final List<File> files = new ArrayList<File>();
                for (final FileUpload fileUpload : filesUploads) {
                    files.add(FileUploadModel.newFile(fileUpload));
                }
                return files;
            }
        }
        return validatableValue;
    }

    private String normalizeMessage(final String m) {
        String message = i18n(m.trim());
        final String title = surroundTitle(element.getTitleModel().getObject());
        message = message.replace(TITLE_PLACEHOLDER, title);
        if (!message.contains(title)) {
            message = title + " " + message;
        }
        message = Strings.eventuallyAddSuffix(message, ".");
        return message;
    }

    public static String surroundTitle(final String title) {
        if (Strings.isNotBlank(title)) {
            return TITLE_SURROUNDING + title + TITLE_SURROUNDING;
        } else {
            return "";
        }
    }

    private String i18n(final String t) {
        if (t == null) {
            return null;
        } else {
            try {
                return new StringResourceModel(t, component, HtmlContext.getModel(component)).setDefaultValue(t)
                        .getObject();
            } catch (final MissingResourceException e) {
                return t;
            }
        }
    }

    public static void maybeAddValidator(final IHtmlElement<?, ?> element, final Component component) {
        final FormComponent<?> formComponent = Components.asFormComponent(component);
        if (formComponent != null) {
            if (element.getModelElement().getBeanPathElement() instanceof IValidatableBeanPathElement) {
                final IValidatableBeanPathElement cElement = (IValidatableBeanPathElement) element.getModelElement()
                        .getBeanPathElement();
                if (cElement.getValidateElement() != null) {
                    //need to remember original component instead of formComponent for IFormComponentAware
                    formComponent.add(new ModelUtilityValidator(element, component));
                }
            }
        }
    }
}