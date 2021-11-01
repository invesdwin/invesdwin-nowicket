package de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart;

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
@MountPath("tutorialstart")
public class TutorialStartPage extends AExampleWebPage {

    public TutorialStartPage() {
        this(Model.of(new TutorialStart()));
    }

    public TutorialStartPage(final IModel<TutorialStart> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if ("firstCarPanel".equals(e.getWicketId())) {
                    return new de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.firstcar.CarPanel(
                            e.getWicketId(), Model.of(
                                    new de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.firstcar.Car()));
                }
                if ("secondCarPanel".equals(e.getWicketId())) {
                    return new de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.secondcar.CarPanel(
                            e.getWicketId(), Model.of(
                                    new de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.secondcar.Car()));
                }
                return super.create(e);
            }
        }).bind();
    }

}
