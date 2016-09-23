package de.invesdwin.nowicket.component.consent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

@NotThreadSafe
public class CookieConsent extends WebMarkupContainer {

    private Integer cookieDurationDays = null;
    private String buttonClass = "btn-success btn-sm";
    private String alertClass = "alert-info text-center";
    private String cookieName = "cookieConsent";
    private String cookieValue = "ok";
    private CookieConsentPosition position = CookieConsentPosition.bottom;

    public CookieConsent(final String id, final Model<URI> policyUri) {
        super(id, policyUri);
    }

    public CookieConsent withCookieDurationDays(final Integer cookieDurationDays) {
        this.cookieDurationDays = cookieDurationDays;
        return this;
    }

    public Integer getCookieDurationDays() {
        return cookieDurationDays;
    }

    public CookieConsent withButtonClass(final String buttonClass) {
        this.buttonClass = buttonClass;
        return this;
    }

    public String getButtonClass() {
        return buttonClass;
    }

    public CookieConsent withAlertClass(final String alertClass) {
        this.alertClass = alertClass;
        return this;
    }

    public String getAlertClass() {
        return alertClass;
    }

    public CookieConsent withCookieName(final String cookieName) {
        this.cookieName = cookieName;
        return this;
    }

    public String getCookieName() {
        return cookieName;
    }

    public CookieConsent withCookieValue(final String cookieValue) {
        this.cookieValue = cookieValue;
        return this;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public CookieConsent withPosition(final CookieConsentPosition position) {
        this.position = position;
        return this;
    }

    public CookieConsentPosition getPosition() {
        return position;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        final CharSequence js = createJavaScript();
        response.render(JavaScriptHeaderItem.forScript(js, getClass().getSimpleName()));
    }

    private CharSequence createJavaScript() {
        try {
            final InputStream in = new PackageResourceReference(getClass(), "cookieConsent.js").getResource()
                    .getResourceStream()
                    .getInputStream();
            String js = IOUtils.toString(in);
            in.close();

            //cookie duration
            final String cookieDuration;
            if (cookieDurationDays != null) {
                cookieDuration = String.valueOf(cookieDurationDays);
            } else {
                cookieDuration = "";
            }
            js = js.replace("[COOKIE_DURATION]", cookieDuration);

            //cookie name
            js = js.replace("[COOKIE_NAME]", cookieName);

            //cookie value
            js = js.replace("[COOKIE_VALUE]", cookieValue);

            //banner title
            final String bannerTitle = getString("banner.title");
            js = js.replace("[BANNER_TITLE]", bannerTitle);

            //banner message
            final String bannerMessage = getString("banner.message");
            js = js.replace("[BANNER_MESSAGE]", bannerMessage);

            //banner button text
            final String bannerButtonText = getString("banner.button.text");
            js = js.replace("[BANNER_BUTTON_TEXT]", bannerButtonText);

            //banner link url
            final String bannerLinkUrl = getDefaultModelObjectAsString();
            js = js.replace("[BANNER_LINK_URL]", bannerLinkUrl);

            //banner link text
            final String bannerLinkText = getString("banner.link.text");
            js = js.replace("[BANNER_LINK_TEXT]", bannerLinkText);

            //button class
            js = js.replace("[BUTTON_CLASS]", buttonClass);

            //alert class
            js = js.replace("[ALERT_CLASS]", alertClass);

            //position
            js = js.replace("[POSITION]", position.toString());

            return js;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } catch (final ResourceStreamNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
