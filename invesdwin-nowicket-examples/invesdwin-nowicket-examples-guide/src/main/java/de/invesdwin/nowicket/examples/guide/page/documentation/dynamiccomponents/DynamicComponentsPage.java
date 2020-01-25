package de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@NotThreadSafe
@MountPath("dynamiccomponents")
public class DynamicComponentsPage extends AExampleWebPage {

    public DynamicComponentsPage() {
        this(Model.of(new DynamicComponents()));
    }

    public DynamicComponentsPage(final IModel<?> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if ("sixthCarPanel".equals(e.getWicketId())) {
                    return new de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents.sixthcar.CarPanel(
                            e.getWicketId(), Model.of(
                                    new de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents.sixthcar.Car()));
                }
                return super.create(e);
            }
        }).bind();
    }

}
