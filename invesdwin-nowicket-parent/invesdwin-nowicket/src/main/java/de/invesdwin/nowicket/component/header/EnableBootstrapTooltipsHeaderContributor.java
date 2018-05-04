package de.invesdwin.nowicket.component.header;

import static de.agilecoders.wicket.jquery.JQuery.$;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;
import de.agilecoders.wicket.jquery.Config;

/**
 * Add this behavior to a page to enable fancy bootstrap javascript tooltips via the following html markup:
 * 
 * <br>
 * <br>
 * &lt;tag data-toggle="tooltip" title="tooltip message" /&gt;
 *
 */
@NotThreadSafe
public class EnableBootstrapTooltipsHeaderContributor implements IHeaderContributor {

    public static final String FUNCTION_NAME = "enableBootstrapTooltips";
    public static final String FUNCTION_CALL = FUNCTION_NAME + "();";

    private static final Resource JS_RESOURCE = new ClassPathResource(FUNCTION_NAME + ".js",
            EnableBootstrapTooltipsHeaderContributor.class);

    private final TooltipConfig config;

    public EnableBootstrapTooltipsHeaderContributor() {
        this(new TooltipConfig());
    }

    public EnableBootstrapTooltipsHeaderContributor(final TooltipConfig config) {
        this.config = config;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forScript(createJavascript(), FUNCTION_NAME));
        response.render(OnDomReadyHeaderItem.forScript(FUNCTION_CALL));
    }

    private String createJavascript() {
        try {
            final InputStream in = JS_RESOURCE.getInputStream();
            String js = IOUtils.toString(in, Charset.defaultCharset());
            in.close();
            js = js.replace("${FUNCTION}", createInitializerScript(config));
            return js;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected CharSequence createInitializerScript(final Config config) {
        return $(createJQuerySelector()).chain("tooltip", config).get();
    }

    protected String createJQuerySelector() {
        return "[data-toggle=\"tooltip\"]:not([data-original-title])";
    }

}
