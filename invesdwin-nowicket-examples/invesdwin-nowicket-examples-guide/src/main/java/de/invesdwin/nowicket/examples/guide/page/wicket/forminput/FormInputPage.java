package de.invesdwin.nowicket.examples.guide.page.wicket.forminput;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelCheckBoxMultipleChoice;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelRadioChoice;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
@MountPath("forminput")
public class FormInputPage extends AExampleWebPage {

    public FormInputPage() {
        this(Model.of(new FormInput()));
    }

    public FormInputPage(final IModel<FormInput> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (FormInputConstants.multiply.equals(e.getWicketId())) {
                    return new MultiplyPanel(e.getWicketId(), new IModel<Multiply>() {
                        @Override
                        public Multiply getObject() {
                            //always retrieve data from the same model in page
                            final FormInput formInput = (FormInput) getDefaultModelObject();
                            return formInput.getMultiply();
                        }
                    });
                }
                if (Strings.equalsAny(e.getWicketId(), FormInputConstants.selectANumberRadioChoicee,
                        FormInputConstants.selectANumberRadioGroup)) {
                    //no need to differentiate between radioGroup and radioChoice in this sample...
                    return new ModelRadioChoice((AChoiceHtmlElement<?>) e).setInline(true);
                }
                if (FormInputConstants.selectOneOrMoreNumbersCheckGroup.equals(e.getWicketId())) {
                    return new ModelCheckBoxMultipleChoice((AChoiceHtmlElement<?>) e).setInline(true);
                }
                return super.create(e);
            }
        }).bind();
    }

}
