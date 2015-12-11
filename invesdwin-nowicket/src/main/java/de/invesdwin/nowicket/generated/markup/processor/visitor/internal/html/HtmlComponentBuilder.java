package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Element;
import org.apache.ecs.html.A;
import org.apache.ecs.html.Button;
import org.apache.ecs.html.Comment;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.FieldSet;
import org.apache.ecs.html.Form;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.IFrame;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Label;
import org.apache.ecs.html.Legend;
import org.apache.ecs.html.Select;
import org.apache.ecs.html.Table;
import org.apache.ecs.xml.XML;

import de.invesdwin.nowicket.generated.binding.processor.element.CheckBoxInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.NumberInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.SubmitButtonHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.CheckBoxInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.DateInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.FieldSetOpenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NumberInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SelectModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SubmitButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.UploadButtonModelElement;
import de.invesdwin.nowicket.util.Attributes;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class HtmlComponentBuilder {

    private static final String BUTTON_CLASSES = "btn btn-default";

    public Html createHtml() {
        final Html html = new Html();
        html.addAttribute("xmlns:wicket", "http://wicket.apache.org");
        return html;
    }

    public Form createForm(final String wicketId) {
        final Form form = new Form();
        form.addAttribute(IHtmlElement.ATTR_WICKET_ID, wicketId);
        form.setClass("form-horizontal");
        form.removeAttribute("enctype");
        form.removeAttribute("accept-charset");
        return form;
    }

    public Element createFeedback(final String wicketId) {
        final Comment comment = new Comment();
        final Div div = new Div();
        div.addAttribute(IHtmlElement.ATTR_WICKET_ID, wicketId);
        comment.addElement(div);
        return comment;
    }

    public Element createModal(final String wicketId) {
        final Div div = new Div();
        div.addAttribute(IHtmlElement.ATTR_WICKET_ID, wicketId);
        return div;
    }

    public Element createTextInput(final TextInputModelElement e) {
        final Input input = new Input();
        input.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        input.setType(TextInputHtmlElement.INPUT_TYPE_TEXT);
        return wrapFormControlInputInFormGroup(e, input);
    }

    public Element createDateInput(final DateInputModelElement e) {
        final Input input = new Input();
        input.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        input.setType(DateInputHtmlElement.INPUT_TYPE_DATE);
        return wrapFormControlInputInFormGroup(e, input);
    }

    public Element createNumberInput(final NumberInputModelElement e) {
        final Input input = new Input();
        input.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        input.setType(NumberInputHtmlElement.INPUT_TYPE_NUMBER);
        return wrapFormControlInputInFormGroup(e, input);
    }

    public Element createFileInput(final UploadButtonModelElement e) {
        final Input input = new Input();
        input.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        input.setType(UploadButtonHtmlElement.INPUT_TYPE_FILE);
        if (e.getBeanPathElement().getAccessor().getRawType().isIterable()
                || e.getBeanPathElement().getAccessor().getRawType().isArray()) {
            input.addAttribute("multiple", "multiple");
        }
        //file input is actually a button not a form-control
        return wrapButtonInputInFormGroup(e, input);
    }

    public Element createSelect(final SelectModelElement e) {
        final Select select = new Select();
        select.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        return wrapFormControlInputInFormGroup(e, select);
    }

    public Element createCheckBoxInput(final CheckBoxInputModelElement e) {
        final Input input = new Input();
        input.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        input.setType(CheckBoxInputHtmlElement.INPUT_TYPE_CHECKBOX);
        return wrapCheckBoxInFormGroup(e, input);
    }

    public Element createSubmitButton(final SubmitButtonModelElement e) {
        final Button button = new Button();
        button.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        button.setClass(BUTTON_CLASSES);
        button.setType(SubmitButtonHtmlElement.BUTTON_TYPE_SUBMIT);
        button.setTagText(e.getStaticTitle());
        return wrapButtonInFormGroup(button);
    }

    public Element createAnchor(final AnchorModelElement e) {
        final A a = new A();
        a.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        a.setClass(BUTTON_CLASSES);
        a.setTagText(e.getStaticTitle());
        return wrapButtonInFormGroup(a);
    }

    public FieldSet createFieldSet(final FieldSetOpenModelElement e) {
        final FieldSet fieldset = new FieldSet();
        final Legend legend = new Legend(e.getStaticTitle());
        legend.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getLegendWicketId());
        fieldset.addElement(legend);
        return fieldset;
    }

    public Element createTable(final TableModelElement e) {
        final Div divTableResponsive = new Div();
        divTableResponsive.setClass("table-responsive");
        final Table table = new Table();
        table.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        table.setClass("table table-condensed table-hover table-striped");
        divTableResponsive.addElement(table);
        return divTableResponsive;
    }

    public Element createTabbed(final TabbedModelElement e) {
        final IFrame iframe = new IFrame();
        iframe.addAttribute(IHtmlElement.ATTR_WICKET_ID, e.getWicketId());
        return iframe;
    }

    private Element wrapButtonInFormGroup(final Element button) {
        final Div divFormGroup = new Div();
        divFormGroup.setClass("form-group");
        final Div divCol = new Div();
        divCol.setClass("col-sm-6");
        final Div divBtnToolbar = new Div();
        divBtnToolbar.setClass("btn-toolbar");
        final Div divBtnGroup = new Div();
        divBtnGroup.setClass("btn-group");
        divBtnGroup.addElement(button);
        divBtnToolbar.addElement(divBtnGroup);
        divCol.addElement(divBtnToolbar);
        divFormGroup.addElement(divCol);
        return divFormGroup;
    }

    private Element wrapCheckBoxInFormGroup(final CheckBoxInputModelElement e, final Input input) {
        final Div divFormGroup = new Div();
        divFormGroup.setClass("form-group");
        final Div divCol = new Div();
        divCol.setClass("col-sm-offset-2 col-sm-4");
        setGridColumnId(e, divCol);
        final Div divCheckbox = new Div();
        divCheckbox.setClass("checkbox");
        final Label label = createLabel(e, input);
        divCheckbox.addElement(label);
        divCol.addElement(divCheckbox);
        divFormGroup.addElement(divCol);
        return divFormGroup;
    }

    private Element wrapButtonInputInFormGroup(final IModelElement<?> e, final ConcreteElement element) {
        return wrapInputInFormGroup(e, element, BUTTON_CLASSES);
    }

    private Element wrapFormControlInputInFormGroup(final IModelElement<?> e, final ConcreteElement element) {
        return wrapInputInFormGroup(e, element, "form-control");
    }

    private Element wrapInputInFormGroup(final IModelElement<?> e, final ConcreteElement element,
            final String additionalClass) {
        final Div divFormGroup = new Div();
        divFormGroup.setClass("form-group");
        final Label label = createLabel(e, null);
        divFormGroup.addElement(label);
        final Div divCol = new Div();
        divCol.setClass("col-sm-4");
        setGridColumnId(e, divCol);
        if (Strings.isNotBlank(additionalClass)) {
            Attributes.merge(element, "class", additionalClass);
        }
        divCol.addElement(element);
        divFormGroup.addElement(divCol);
        return divFormGroup;
    }

    private void setGridColumnId(final IModelElement<?> e, final Div divCol) {
        final String id = e.getWicketId() + GridColumnHtmlElement.GRID_COLUMN_WICKET_ID_SUFFIX;
        divCol.addAttribute(IHtmlElement.ATTR_WICKET_ID, id);
    }

    /**
     * About how to create labels in wicket: https://cwiki.apache.org/confluence/display/WICKET/Wicket%27s+XHTML+tags
     */
    private Label createLabel(final IModelElement<?> e, final Input input) {
        final Label label = new Label();
        label.addAttribute("wicket:for", e.getWicketId());
        if (input == null) {
            label.setClass("control-label col-sm-2");
        } else {
            label.addElement(input);
        }
        final XML wicketLabel = new XML("wicket:label");
        wicketLabel.setTagText(e.getStaticTitle());
        label.addElement(wicketLabel);
        return label;
    }

}
