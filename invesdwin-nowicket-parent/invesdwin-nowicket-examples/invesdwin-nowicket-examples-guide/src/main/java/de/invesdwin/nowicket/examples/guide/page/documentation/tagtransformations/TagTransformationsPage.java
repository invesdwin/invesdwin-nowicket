package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.links.Links;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.links.LinksPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.listsandchoices.ListsAndChoices;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.listsandchoices.ListsAndChoicesPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tabbedandpanels.TabbedAndPanels;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tabbedandpanels.TabbedAndPanelsPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandchoices.TablesAndChoices;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandchoices.TablesAndChoicesPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandpanels.TablesAndPanels;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandpanels.TablesAndPanelsPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.texts.Texts;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.texts.TextsPanel;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@NotThreadSafe
@MountPath("tagtransformations")
public class TagTransformationsPage extends AExampleWebPage {

    public TagTransformationsPage() {
        this(Model.of(new TagTransformations()));
    }

    public TagTransformationsPage(final IModel<TagTransformations> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if ("textsPanel".equals(e.getWicketId())) {
                    return new TextsPanel(e.getWicketId(), Model.of(new Texts()));
                }
                if ("listsAndChoicesPanel".equals(e.getWicketId())) {
                    return new ListsAndChoicesPanel(e.getWicketId(), Model.of(new ListsAndChoices()));
                }
                if ("tablesAndChoicesPanel".equals(e.getWicketId())) {
                    return new TablesAndChoicesPanel(e.getWicketId(), Model.of(new TablesAndChoices()));
                }
                if ("tablesAndPanelsPanel".equals(e.getWicketId())) {
                    return new TablesAndPanelsPanel(e.getWicketId(), Model.of(new TablesAndPanels()));
                }
                if ("tabbedAndPanelsPanel".equals(e.getWicketId())) {
                    return new TabbedAndPanelsPanel(e.getWicketId(), Model.of(new TabbedAndPanels()));
                }
                if ("linksPanel".equals(e.getWicketId())) {
                    return new LinksPanel(e.getWicketId(), Model.of(new Links()));
                }
                return super.create(e);
            }
        }).bind();
    }

}
