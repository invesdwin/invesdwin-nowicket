package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.html.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallback;
import de.invesdwin.nowicket.generated.markup.processor.element.SubmitButtonModelElement;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class SubmitButtonHtmlElement extends AModelHtmlElement<SubmitButtonModelElement, Object> {

    public static final String BUTTON_TYPE_SUBMIT = Button.submit;

    public SubmitButtonHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Deprecated
    @Override
    public IModel<Object> getModel() {
        throw new UnsupportedOperationException();
    }

    public ISubmitButtonCallback getButtonCallback() {
        return getContext().getSubmitButtonCallbackFactory()
                .createSubmitButtonCallback(this, getTargetObjectModel(), () -> Objects.EMPTY_ARRAY,
                        getModelElement().getBeanPathElement().getInvoker());
    }

    public IModel<String> getIconCssClassModel() {
        return new ResourceModel(getModelElement().getIconCssClassPropertyName(), "")
                .wrapOnAssignment(getContext().getMarkupContainer());
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitSubmitButton(this);
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

}
