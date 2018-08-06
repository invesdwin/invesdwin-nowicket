package de.invesdwin.nowicket.component.logviewer.js;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIJavaScriptReference;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

@Immutable
public final class LogViewerJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final LogViewerJsReference INSTANCE = new LogViewerJsReference();
    public static final String FUNCTION_NAME = "logViewer";

    private LogViewerJsReference() {
        super(LogViewerJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                JavaScriptHeaderItem
                        .forReference(ABaseWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()),
                CssHeaderItem.forReference(JQueryUICssReference.instance()),
                JavaScriptHeaderItem.forReference(JQueryUIJavaScriptReference.instance()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnDomReadyHeaderItem.forScript(FUNCTION_NAME + "_init()"));
    }
}