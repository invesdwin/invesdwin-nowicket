package de.invesdwin.nowicket.examples.guide.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapTheme;
import de.agilecoders.wicket.core.settings.DefaultThemeProvider;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootstrap.BootstrapThemeTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import de.agilecoders.wicket.themes.markup.html.google.GoogleTheme;

@NotThreadSafe
public class ExampleThemeProvider extends DefaultThemeProvider {

    private static final Set<ITheme> THEMES = new HashSet<ITheme>() {
        {
            add(new BootstrapTheme());
            add(new BootstrapThemeTheme());
            add(new GoogleTheme());
            //wicket-bootstrap has broken css files for material design: https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/593
            //add(new MaterialDesignTheme());
        }
    };
    private static final Set<ThemeProvider> THEME_PROVIDERS = new HashSet<ThemeProvider>() {
        {
            add(new BootswatchThemeProvider(BootswatchTheme.Cerulean));
        }
    };

    public ExampleThemeProvider() {
        defaultTheme(new DefaultThemeProvider().defaultTheme());
        final Map<String, ITheme> themes = new HashMap<String, ITheme>();
        themes.put(defaultTheme().name(), defaultTheme());
        addThemes(themes);
        addThemeProviders(themes);
        final Comparator<ITheme> comparator = new Comparator<ITheme>() {
            @Override
            public int compare(final ITheme o1, final ITheme o2) {
                return o1.name().compareToIgnoreCase(o2.name());
            }
        };
        final List<ITheme> sortedThemes = new ArrayList<ITheme>(themes.values());
        Collections.sort(sortedThemes, comparator);
        for (final ITheme theme : sortedThemes) {
            add(theme);
        }
    }

    @Override
    public DefaultThemeProvider addDefaultTheme(final ITheme theme) {
        //ignore this call in super constructor to have a proper alphabetical ordering of themes
        return this;
    }

    private void addThemeProviders(final Map<String, ITheme> themes) {
        for (final ThemeProvider provider : THEME_PROVIDERS) {
            for (final ITheme theme : provider.available()) {
                themes.put(theme.name(), theme);
            }
        }
    }

    private void addThemes(final Map<String, ITheme> themes) {
        for (final ITheme theme : THEMES) {
            themes.put(theme.name(), theme);
        }
    }

}
