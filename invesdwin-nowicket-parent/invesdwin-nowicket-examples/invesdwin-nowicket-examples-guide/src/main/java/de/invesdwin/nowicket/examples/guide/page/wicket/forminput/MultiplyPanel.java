package de.invesdwin.nowicket.examples.guide.page.wicket.forminput;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.GridColumnBorder;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class MultiplyPanel extends Panel {

    public MultiplyPanel(final String id, final IModel<Multiply> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createGridColumn(final GridColumnHtmlElement e) {
                if (Strings.equalsAny(e.getModelWicketId(), MultiplyConstants.left, MultiplyConstants.right)) {
                    return new GridColumnBorder(e) {
                        @Override
                        protected boolean showHelpText() {
                            //the help text breaks the alignment, but the red border still looks good
                            return false;
                        }
                    };
                }
                return super.createGridColumn(e);
            }
        }).bind();
    }

}
