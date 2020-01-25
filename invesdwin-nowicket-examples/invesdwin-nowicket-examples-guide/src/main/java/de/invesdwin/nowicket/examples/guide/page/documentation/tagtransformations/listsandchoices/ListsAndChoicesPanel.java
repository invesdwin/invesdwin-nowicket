package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.listsandchoices;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.palette.ModelPalette;

@NotThreadSafe
public class ListsAndChoicesPanel extends Panel {

    public ListsAndChoicesPanel(final String id, final IModel<ListsAndChoices> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createSelect(final SelectHtmlElement e) {
                if (ListsAndChoicesConstants.asMultiselectPalette.equals(e.getWicketId())) {
                    return new ModelPalette(e);
                }
                return super.createSelect(e);
            }
        }).bind();
    }

}
