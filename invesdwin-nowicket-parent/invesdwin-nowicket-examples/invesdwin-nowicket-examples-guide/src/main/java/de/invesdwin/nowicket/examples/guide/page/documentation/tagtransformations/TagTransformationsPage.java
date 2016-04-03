package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.collapsibles.Collapsibles;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.collapsibles.CollapsiblesPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.links.Links;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.links.LinksPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.listsandchoices.ListsAndChoices;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.listsandchoices.ListsAndChoicesPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tables.Tables;
import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tables.TablesPanel;
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
                if ("listsPanel".equals(e.getWicketId())) {
                    return new ListsAndChoicesPanel(e.getWicketId(), Model.of(new ListsAndChoices()));
                }
                if ("tablesPanel".equals(e.getWicketId())) {
                    return new TablesPanel(e.getWicketId(), Model.of(new Tables()));
                }
                if ("collapsiblesPanel".equals(e.getWicketId())) {
                    return new CollapsiblesPanel(e.getWicketId(), Model.of(new Collapsibles()));
                }
                if ("linksPanel".equals(e.getWicketId())) {
                    return new LinksPanel(e.getWicketId(), Model.of(new Links()));
                }
                return super.create(e);
            }
        }).bind();
    }

}
