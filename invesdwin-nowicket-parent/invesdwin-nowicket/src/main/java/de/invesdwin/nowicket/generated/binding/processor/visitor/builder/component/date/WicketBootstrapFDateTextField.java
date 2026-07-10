package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.date;

import java.time.LocalDateTime;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.AbstractDateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.invesdwin.util.time.date.FDate;

/**
 * Adapted from de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField
 */
@NotThreadSafe
public class WicketBootstrapFDateTextField extends
        AbstractDateTextField<FDate, WicketFDateTextField, LocalDateTime, DateTextFieldConfig, WicketBootstrapFDateTextField> {

    private static final long serialVersionUID = 3499287675713818823L;

    /**
     * @param markupId
     *            The id of the text field
     */
    public WicketBootstrapFDateTextField(final String markupId) {
        this(markupId, new DateTextFieldConfig());
    }

    /**
     * @param markupId
     *            The id of the text field
     * @param datePattern
     *            The format of the date
     */
    public WicketBootstrapFDateTextField(final String markupId, final String datePattern) {
        this(markupId, new DateTextFieldConfig().withFormat(datePattern));
    }

    /**
     * @param markupId
     *            The id of the text field
     * @param model
     *            The date model
     */
    public WicketBootstrapFDateTextField(final String markupId, final IModel<FDate> model) {
        this(markupId, model, new DateTextFieldConfig());
    }

    /**
     * @param markupId
     *            The id of the text field
     * @param model
     *            The date model
     * @param dateFormat
     *            The format of the date
     */
    public WicketBootstrapFDateTextField(final String markupId, final IModel<FDate> model, final String dateFormat) {
        this(markupId, model, new DateTextFieldConfig().withFormat(dateFormat));
    }

    /**
     * @param markupId
     *            The id of the text field
     * @param model
     *            The date model
     * @param config
     *            The configuration of this field
     */
    public WicketBootstrapFDateTextField(final String markupId, final IModel<FDate> model,
            final DateTextFieldConfig config) {
        super(new WicketFDateTextField(markupId, model, Args.notNull(config, "config").getFormat()), FDate.class,
                config);
    }

    /**
     * @param markupId
     *            The id of the text field
     * @param config
     *            The configuration of this field
     */
    public WicketBootstrapFDateTextField(final String markupId, final DateTextFieldConfig config) {
        super(new WicketFDateTextField(markupId, Args.notNull(config, "config").getFormat()), FDate.class, config);
    }

}
