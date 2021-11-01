package de.invesdwin.nowicket.examples.guide.page.documentation.validation;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@MountPath("validation")
@NotThreadSafe
public class ValidationPage extends AExampleWebPage {

    public ValidationPage() {
        this(Model.of(new Validation()));
    }

    public ValidationPage(final IModel<Validation> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if ("fourthCarPanel".equals(e.getWicketId())) {
                    return new de.invesdwin.nowicket.examples.guide.page.documentation.validation.fourthcar.CarPanel(
                            e.getWicketId(), Model.of(
                                    new de.invesdwin.nowicket.examples.guide.page.documentation.validation.fourthcar.Car()));
                }
                return super.create(e);
            }
        }).bind();
    }

}
