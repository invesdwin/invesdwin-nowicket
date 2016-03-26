package de.invesdwin.nowicket.examples.guide.page.wicket.forminput;

import java.util.Locale;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum UseLocale {
    English(Locale.ENGLISH),
    German(Locale.GERMAN);

    private Locale locale;

    UseLocale(final Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static UseLocale valueOf(final Locale locale) {
        for (final UseLocale useLocale : values()) {
            if (useLocale.getLocale().equals(locale)) {
                return useLocale;
            }
        }
        return UseLocale.English;
    }

}
