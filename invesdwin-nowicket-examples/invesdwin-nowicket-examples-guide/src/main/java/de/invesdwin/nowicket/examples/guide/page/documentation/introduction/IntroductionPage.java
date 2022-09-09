package de.invesdwin.nowicket.examples.guide.page.documentation.introduction;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.component.carousel.ResponsiveCarousel;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.util.time.duration.Duration;

@NotThreadSafe
@MountPath("introduction")
public class IntroductionPage extends AExampleWebPage {

    public IntroductionPage() {
        this(Model.of(new Introduction()));
    }

    public IntroductionPage(final IModel<Introduction> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (IntroductionConstants.frameworkSloganImgs.equals(e.getWicketId())) {
                    return new ResponsiveCarousel(e.getWicketId(), (IModel) e.getModel())
                            .setInterval(Duration.FIVE_SECONDS.javaTimeValue());
                }
                return super.create(e);
            }
        }).bind();
    }

}
