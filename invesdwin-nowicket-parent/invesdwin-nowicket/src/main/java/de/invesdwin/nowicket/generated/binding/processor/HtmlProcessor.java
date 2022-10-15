package de.invesdwin.nowicket.generated.binding.processor;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.CheckBoxInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ContainerOpenHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FeedbackHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FormHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.HiddenInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ImageHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.LabelHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.LegendHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ModalHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.NumberInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.PasswordInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.RadioInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.RootHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SubmitButtonHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextAreaHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UnknownModelHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UnknownNoModelHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UnorderedListHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SubmitButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedModelElement;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.string.Strings;

// CS:OFF ClassDataAbstraction
@NotThreadSafe
public class HtmlProcessor {
    // CS:ON ClassDataAbstraction

    private final HtmlContext context;
    private final IHtmlVisitor[] visitors;

    public HtmlProcessor(final HtmlContext context, final IHtmlVisitor... visitors) {
        this.context = context;
        this.visitors = visitors;
    }

    public HtmlContext getContext() {
        return context;
    }

    public void process() {
        new RootHtmlElement(context).accept(visitors);
        final Document doc = context.getHtmlDocument();
        traverse(doc);
        for (final IHtmlVisitor visitor : visitors) {
            visitor.finish();
        }
    }

    private void traverse(final Element element) {
        boolean visitContainerClose = false;
        try {
            IHtmlElement<?, ?> htmlElement = detectHtmlElement(element);
            if (htmlElement != null) {
                if (isUnknownElement(htmlElement)) {
                    //redirect to no model element that is unknown
                    htmlElement = new UnknownNoModelHtmlElement(getContext(), element);
                }
                if (element.children().size() > 0) {
                    Assertions.assertThat(new ContainerOpenHtmlElement(getContext(), element).accept(visitors))
                            .isTrue();
                    visitContainerClose = true;
                }
                Assertions.assertThat(htmlElement.accept(visitors))
                        .as("Maybe you have duplicate wicket ids [%s] in your html file [%s]?",
                                htmlElement.getWicketId(), context.getHtmlFile().getFilename())
                        .isTrue();
            }
        } catch (final Throwable t) {
            throw new RuntimeException("On " + Element.class.getSimpleName() + ": " + element.toString(), t);
        }
        for (final Element child : element.children()) {
            traverse(child);
        }
        if (visitContainerClose) {
            for (final IHtmlVisitor visitor : visitors) {
                visitor.visitContainerClose();
            }
        }
    }

    private boolean isUnknownElement(final IHtmlElement<?, ?> htmlElement) {
        return htmlElement.isModelElement() && getContext().getModelObjectContext()
                .getElementRegistry()
                .getElement(htmlElement.getWicketId()) == null;
    }

    private IHtmlElement<?, ?> detectHtmlElement(final Element element) {
        final String wicketId = element.attr(IHtmlElement.ATTR_WICKET_ID);
        if (Strings.isBlank(wicketId)) {
            return null;
        }
        final IHtmlElement<?, ?> divElement = detectDivElement(element, wicketId);
        if (divElement != null) {
            return divElement;
        }
        final IHtmlElement<?, ?> inputElement = detectInputElement(element, wicketId);
        if (inputElement != null) {
            return inputElement;
        }
        final IHtmlElement<?, ?> buttonElement = detectButtonElement(element, wicketId);
        if (buttonElement != null) {
            return buttonElement;
        }
        final IHtmlElement<?, ?> tabbedElement = detectTabbedElement(element, wicketId);
        if (tabbedElement != null) {
            return tabbedElement;
        }
        final IHtmlElement<?, ?> otherElement = detectOtherElement(element, wicketId);
        if (otherElement != null) {
            return otherElement;
        }
        return new UnknownModelHtmlElement(getContext(), element);
    }

    private IHtmlElement<?, ?> detectDivElement(final Element element, final String wicketId) {
        if ("div".equals(element.tagName())) {
            if (wicketId.equals(FeedbackHtmlElement.WICKET_ID)) {
                return new FeedbackHtmlElement(getContext(), element);
            }
            if (wicketId.equals(ModalHtmlElement.WICKET_ID)) {
                return new ModalHtmlElement(getContext(), element);
            }
            if (wicketId.endsWith(GridColumnHtmlElement.GRID_COLUMN_WICKET_ID_SUFFIX)) {
                return new GridColumnHtmlElement(getContext(), element);
            }
        }
        return null;
    }

    private IHtmlElement<?, ?> detectInputElement(final Element element, final String wicketId) {
        if ("input".equals(element.tagName())) {
            final String inputType = element.attr(IHtmlElement.ATTR_TYPE);
            final IHtmlElement<?, ?> textFieldInputElement = detectTextFieldInputElement(element, inputType);
            if (textFieldInputElement != null) {
                return textFieldInputElement;
            }
            //no blank check here, since input is normally a text field, thus blank check is above there
            if (SubmitButtonHtmlElement.BUTTON_TYPE_SUBMIT.equals(inputType)) {
                return newButtonOrAnchor(element, wicketId);
            }
            if (UploadButtonHtmlElement.INPUT_TYPE_FILE.equals(inputType)) {
                return new UploadButtonHtmlElement(getContext(), element);
            }
            if (CheckBoxInputHtmlElement.INPUT_TYPE_CHECKBOX.equals(inputType)) {
                return new CheckBoxInputHtmlElement(getContext(), element);
            }
            if (RadioInputHtmlElement.INPUT_TYPE_RADIO.equals(inputType)) {
                return new RadioInputHtmlElement(getContext(), element);
            }
        }
        return null;
    }

    private IHtmlElement<?, ?> detectButtonElement(final Element element, final String wicketId) {
        if ("button".equals(element.tagName())) {
            final String buttonType = element.attr(IHtmlElement.ATTR_TYPE);
            //submit button is default
            if (Strings.isBlank(buttonType) || SubmitButtonHtmlElement.BUTTON_TYPE_SUBMIT.equals(buttonType)) {
                return newButtonOrAnchor(element, wicketId);
            }
        }
        return null;
    }

    private IHtmlElement<?, ?> newButtonOrAnchor(final Element element, final String wicketId) {
        final IModelElement<?> modelElement = getContext().getModelObjectContext()
                .getElementRegistry()
                .getElement(wicketId);
        if (modelElement instanceof SubmitButtonModelElement) {
            return new SubmitButtonHtmlElement(getContext(), element);
        } else {
            return new AnchorHtmlElement(getContext(), element);
        }
    }

    private IHtmlElement<?, ?> detectTextFieldInputElement(final Element element, final String inputType) {
        //text input is default
        if (Strings.isBlank(inputType) || TextInputHtmlElement.INPUT_TYPE_TEXT.equalsIgnoreCase(inputType)) {
            return new TextInputHtmlElement(getContext(), element);
        }
        if (PasswordInputHtmlElement.INPUT_TYPE_PASSWORD.equalsIgnoreCase(inputType)) {
            return new PasswordInputHtmlElement(getContext(), element);
        }
        if (NumberInputHtmlElement.INPUT_TYPE_NUMBER.equalsIgnoreCase(inputType)) {
            return new NumberInputHtmlElement(getContext(), element);
        }
        if (DateInputHtmlElement.INPUT_TYPE_DATE.equalsIgnoreCase(inputType)) {
            return new DateInputHtmlElement(getContext(), element);
        }
        if (HiddenInputHtmlElement.INPUT_TYPE_HIDDEN.equalsIgnoreCase(inputType)) {
            return new HiddenInputHtmlElement(context, element);
        }
        return null;
    }

    //CS:OFF NPath
    private IHtmlElement<?, ?> detectOtherElement(final Element element, final String wicketId) {
        //CS:ON NPath
        if ("form".equals(element.tagName())) {
            return new FormHtmlElement(getContext(), element);
        }
        if ("label".equals(element.tagName())) {
            return new LabelHtmlElement(getContext(), element);
        }
        if ("p".equals(element.tagName())) {
            return new LabelHtmlElement(getContext(), element);
        }
        if ("textarea".equals(element.tagName())) {
            return new TextAreaHtmlElement(getContext(), element);
        }
        if ("table".equals(element.tagName())) {
            return new TableHtmlElement(getContext(), element);
        }
        if ("select".equals(element.tagName())) {
            return new SelectHtmlElement(getContext(), element);
        }
        if ("legend".equals(element.tagName()) || wicketId.endsWith(LegendHtmlElement.LEGEND_WICKET_ID_SUFFIX)) {
            return new LegendHtmlElement(getContext(), element);
        }
        if ("ul".equals(element.tagName())) {
            return new UnorderedListHtmlElement(getContext(), element);
        }
        if ("a".equals(element.tagName())) {
            return newButtonOrAnchor(element, wicketId);
        }
        if ("img".equals(element.tagName())) {
            return new ImageHtmlElement(getContext(), element);
        }
        return null;
    }

    private IHtmlElement<?, ?> detectTabbedElement(final Element element, final String wicketId) {
        if (Strings.equalsAny(element.tagName(), "div", "span")) {
            final IModelElement<?> modelElement = getContext().getModelObjectContext()
                    .getElementRegistry()
                    .getElement(wicketId);
            if (modelElement instanceof TabbedModelElement) {
                return new TabbedHtmlElement(context, element);
            }
        }
        if ("iframe".equals(element.tagName())) {
            final IModelElement<?> modelElement = getContext().getModelObjectContext()
                    .getElementRegistry()
                    .getElement(wicketId);
            if (modelElement instanceof TabbedModelElement) {
                return new TabbedHtmlElement(getContext(), element);
            } else {
                return new AnchorHtmlElement(getContext(), element);
            }
        }
        return null;
    }
}
