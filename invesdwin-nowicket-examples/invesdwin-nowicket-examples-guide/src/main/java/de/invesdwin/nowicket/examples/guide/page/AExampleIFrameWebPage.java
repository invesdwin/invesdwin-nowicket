package de.invesdwin.nowicket.examples.guide.page;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.component.footer.AFooter;

@NotThreadSafe
public abstract class AExampleIFrameWebPage extends AWebPage {

    public AExampleIFrameWebPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected AFooter newFooter(final String id) {
        return null;
    }

    @Override
    protected Navbar newNavbar(final String id) {
        return null;
    }

    @Override
    protected HtmlTag newHtmlTag(final String id) {
        return new HtmlTag(id, getLocale(), false); //modernizr already added from the outside
    }

    @Override
    protected String getContainerClass() {
        //full width
        return "container-fluid";
    }
}
