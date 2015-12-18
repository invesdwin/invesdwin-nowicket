package de.invesdwin.nowicket.component.csrf;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.StringValue;

import de.invesdwin.util.lang.UUIDs;

/**
 * Taken from: http://apache-wicket.1842946.n4.nabble.com/Implementing-a-SecureForm-to-avoid-CSRF-attacks-td4666175.html
 */
@NotThreadSafe
public class CsrfTokenForm<T> extends Form<T> {

    private static final String TOKEN_NAME = "CSRF_TOKEN";
    private String token;

    public CsrfTokenForm(final String id) {
        super(id);
    }

    public CsrfTokenForm(final String id, final IModel<T> model) {
        super(id, model);
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        if (isRootForm()) {
            this.token = UUIDs.newPseudorandomUUID();
        }
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        // render the hidden field
        if (isRootForm()) {
            final AppendingStringBuffer buffer = new AppendingStringBuffer("<input type=\"hidden\" name=\"");
            buffer.append(TOKEN_NAME)
            .append("\" id=\"")
            .append(TOKEN_NAME)
            .append("\" value=\"")
            .append(token)
            .append("\" />");
            getResponse().write(buffer);
        }

        // do the rest of the processing
        super.onComponentTagBody(markupStream, openTag);
    }

    @Override
    protected void onValidate() {
        if (isRootForm()) {
            // Check the random id in the hidden field. This guards against CSRF attacks.
            final StringValue requestToken = getRequest().getPostParameters().getParameterValue(TOKEN_NAME);
            if (!requestToken.equals(StringValue.valueOf(token))) {
                throw new IllegalStateException(getString("csrf.token.mismatch"));
            }
        }

        super.onValidate();
    }

}