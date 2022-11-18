package de.invesdwin.nowicket.util;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.util.lang.string.Strings;
import jakarta.servlet.http.HttpServletRequest;

@Immutable
public final class UserAgents {

    private UserAgents() {}

    public static boolean isBot() {
        final String userAgent = ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest())
                .getHeader("User-Agent");
        return isBot(userAgent);
    }

    public static boolean isBot(final String userAgent) {
        return Strings.containsAnyIgnoreCase(userAgent, "bot", "crawl", "slurp", "spider", "google", "yahoo", "bing");
    }

}
