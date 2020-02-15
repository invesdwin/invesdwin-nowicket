package de.invesdwin.nowicket.examples.guide.page.documentation.introduction;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.Carousel;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@NotThreadSafe
@MountPath("introduction")
public class IntroductionPage extends AExampleWebPage {

    public IntroductionPage() {
        this(Model.of(new Introduction()));
    }

    public IntroductionPage(final IModel<Introduction> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (IntroductionConstants.frameworkSloganImgs.equals(e.getWicketId())) {
                    return new Carousel(e.getWicketId(), (IModel) e.getModel())
                            .setInterval(org.apache.wicket.util.time.Duration.valueOf(5000));
                }
                return super.create(e);
            }
        }).bind();
    }

}
