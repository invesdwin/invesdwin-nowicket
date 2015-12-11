package de.invesdwin.nowicket.generated.binding.processor.visitor.builder;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.component.csrf.CsrfTokenForm;
import de.invesdwin.nowicket.component.modal.ModalContainer;
import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.CheckBoxInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.DateInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FeedbackHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.FormHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IUnknownHtmlElement;
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
import de.invesdwin.nowicket.generated.binding.processor.element.TabbedColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableAnchorColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableDateColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableNumberColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableRemoveFromButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSubmitButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableTextColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextAreaHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UnorderedListHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.AjaxNotificationPanel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.GridColumnBorder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelDateTextField;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelDropDownChoice;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelFileUploadField;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelImage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelLabel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelListMultipleChoice;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelNumberTextField;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelRadioChoice;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelRefreshingView;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelTextField;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.ModelButton;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.FormComponentAwarePropertyResolver;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.ModelPropertyValidator;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.SQLInjectionValidator;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelDownloadLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelExternalLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link.ModelResourceLink;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.ModelTab;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.ModelTabbedPanel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelDataTable;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelDateColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelDownloadAnchorColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelNumberColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelResourceAnchorColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelResourceReferenceAnchorColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelTextColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.ModelUrlAnchorColumn;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.TableModelButtonColumn;
import de.invesdwin.nowicket.util.Components;

// CHECKSTYLE:OFF fan out
@NotThreadSafe
public class DefaultBindingBuilder implements IBindingBuilder {
    //CHECKSTYLE:ON

    @Override
    public Component createForm(final FormHtmlElement e) {
        final CsrfTokenForm<Object> form = new CsrfTokenForm<Object>(e.getWicketId());
        return form;
    }

    @Override
    public Component createModal(final ModalHtmlElement e) {
        final ModalContainer modal = new ModalContainer(e.getWicketId());
        return modal;
    }

    @Override
    public Component createFeedback(final FeedbackHtmlElement e) {
        final AjaxNotificationPanel feedback = new AjaxNotificationPanel(e.getWicketId());
        return feedback;
    }

    @Override
    public Component createTable(final TableHtmlElement e) {
        final ModelDataTable table = new ModelDataTable(e);
        return table;
    }

    @Override
    public IColumn<? extends Object, String> createRemoveFromButtonColumn(final TableRemoveFromButtonColumnHtmlElement e) {
        return createSubmitButtonColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createSubmitButtonColumn(final TableSubmitButtonColumnHtmlElement e) {
        final TableModelButtonColumn actionIconColumn = new TableModelButtonColumn(e);
        return actionIconColumn;
    }

    @Override
    public IColumn<? extends Object, String> createDateColumn(final TableDateColumnHtmlElement e) {
        final ModelDateColumn dateColumn = new ModelDateColumn(e);
        return dateColumn;
    }

    @Override
    public IColumn<? extends Object, String> createNumberColumn(final TableNumberColumnHtmlElement e) {
        final ModelNumberColumn numberColumn = new ModelNumberColumn(e);
        return numberColumn;
    }

    @Override
    public IColumn<? extends Object, String> createTextColumn(final TableTextColumnHtmlElement e) {
        final ModelTextColumn textColumn = new ModelTextColumn(e);
        return textColumn;
    }

    @Override
    public IColumn<? extends Object, String> createFileAnchorColumn(final TableAnchorColumnHtmlElement e) {
        final ModelDownloadAnchorColumn downloadLink = new ModelDownloadAnchorColumn(e);
        return downloadLink;
    }

    @Override
    public IColumn<? extends Object, String> createResourceAnchorColumn(final TableAnchorColumnHtmlElement e) {
        final ModelResourceAnchorColumn resourceLink = new ModelResourceAnchorColumn(e);
        return resourceLink;
    }

    @Override
    public IColumn<? extends Object, String> createResourceReferenceAnchorColumn(final TableAnchorColumnHtmlElement e) {
        final ModelResourceReferenceAnchorColumn resourceLink = new ModelResourceReferenceAnchorColumn(e);
        return resourceLink;
    }

    @Override
    public IColumn<? extends Object, String> createUrlAnchorColumn(final TableAnchorColumnHtmlElement e) {
        final ModelUrlAnchorColumn externalLink = new ModelUrlAnchorColumn(e);
        return externalLink;
    }

    @Override
    public Component createTabbed(final TabbedHtmlElement e) {
        final ModelTabbedPanel tabbedPanel = new ModelTabbedPanel(e);
        return tabbedPanel;
    }

    @Override
    public ITab createTabbedColumn(final TabbedColumnHtmlElement e) {
        final ModelTab tab = new ModelTab(e);
        return tab;
    }

    @Override
    public Component createUnorderedList(final UnorderedListHtmlElement e) {
        final ModelRefreshingView listView = new ModelRefreshingView(e);
        return listView;
    }

    @Override
    public Component createGridColumn(final GridColumnHtmlElement e) {
        final GridColumnBorder gridColumn = new GridColumnBorder(e);
        return gridColumn;
    }

    @Override
    public Component createSubmitButton(final SubmitButtonHtmlElement e) {
        final ModelButton button = new ModelButton(e);
        return button;
    }

    @Override
    public Component createUploadButton(final UploadButtonHtmlElement e) {
        final ModelFileUploadField fileUploadField = new ModelFileUploadField(e);
        return fileUploadField;
    }

    @Override
    public Component createCheckBoxInput(final CheckBoxInputHtmlElement e) {
        final CheckBox checkBox = new CheckBox(e.getWicketId(), e.getModel());
        return checkBox;
    }

    @Override
    public Component createSelect(final SelectHtmlElement e) {
        return e.createWicketSelect();
    }

    @Override
    public Component createSingleSelect(final SelectHtmlElement e) {
        final ModelDropDownChoice dropDownChoice = new ModelDropDownChoice(e);
        return dropDownChoice;
    }

    @Override
    public Component createMultiSelect(final SelectHtmlElement e) {
        final ModelListMultipleChoice listMultipleChoice = new ModelListMultipleChoice(e);
        return listMultipleChoice;
    }

    @Override
    public Component createTextInput(final TextInputHtmlElement e) {
        final ModelTextField textField = new ModelTextField(e);
        return textField;
    }

    @Override
    public Component createAnchor(final AnchorHtmlElement e) {
        final Component link = e.createWicketAnchor();
        return link;
    }

    @Override
    public Component createFileAnchor(final AnchorHtmlElement e) {
        final ModelDownloadLink downloadLink = new ModelDownloadLink(e);
        return downloadLink;
    }

    @Override
    public Component createResourceAnchor(final AnchorHtmlElement e) {
        final ModelResourceLink resourceLink = new ModelResourceLink(e, e.getResourceModel().getObject());
        return resourceLink;
    }

    @Override
    public Component createResourceReferenceAnchor(final AnchorHtmlElement e) {
        final ModelResourceLink resourceLink = new ModelResourceLink(e, e.getResourceReferenceModel().getObject());
        return resourceLink;
    }

    @Override
    public Component createUrlAnchor(final AnchorHtmlElement e) {
        final ModelExternalLink externalLink = new ModelExternalLink(e);
        return externalLink;
    }

    @Override
    public Component createImage(final ImageHtmlElement e) {
        final ModelImage image = new ModelImage(e);
        return image;
    }

    @Override
    public Component createDateInput(final DateInputHtmlElement e) {
        final ModelDateTextField dateTextField = new ModelDateTextField(e);
        return dateTextField;
    }

    @Override
    public Component createNumberInput(final NumberInputHtmlElement e) {
        final ModelNumberTextField numberTextField = new ModelNumberTextField(e);
        return numberTextField;
    }

    @Override
    public Component createTextArea(final TextAreaHtmlElement e) {
        final TextArea<Object> textArea = new TextArea<Object>(e.getWicketId(), e.getModel());
        return textArea;
    }

    @Override
    public Component createRadioInput(final RadioInputHtmlElement e) {
        final ModelRadioChoice radioChoice = new ModelRadioChoice(e);
        return radioChoice;
    }

    @Override
    public Component createPasswordInput(final PasswordInputHtmlElement e) {
        final PasswordTextField passwordTextField = new PasswordTextField(e.getWicketId(), e.getModel());
        return passwordTextField;
    }

    @Override
    public Component createLabel(final LabelHtmlElement e) {
        final ModelLabel label = new ModelLabel(e);
        return label;
    }

    @Override
    public Component createLegend(final LegendHtmlElement e) {
        final ModelLabel label = new ModelLabel(e, e.getTitleModel());
        return label;
    }

    @Override
    public Component createUnknown(final IUnknownHtmlElement<?> e) {
        //no default binding
        return null;
    }

    @Override
    public boolean configureRoot(final RootHtmlElement e, final MarkupContainer c) {
        if (c instanceof AWebPage) {
            final AWebPage webPage = (AWebPage) c;
            webPage.setTitleModel(e.getTitleModel());
        }
        return true;
    }

    @Override
    public boolean configure(final IHtmlElement<?, ?> e, final Component c) {
        if (e.isModelElement() && !(e.getModelElement() instanceof TableHtmlElement)) {
            c.add(new ModelComponentBehavior(e, c, e.getTargetObjectModel()));
        }
        final FormComponent<?> formComponent = Components.asFormComponent(c);
        if (formComponent != null) {
            if (e.isModelElement()) {
                formComponent.setLabel(e.getTitleModel());
            }
            formComponent.add(new SQLInjectionValidator(c));
            if (e.getModelElement().getBeanPathElement().isProperty()) {
                FormComponentAwarePropertyResolver.maybeRegisterElement(e, c, formComponent);
                formComponent.add(new ModelPropertyValidator(c));
            }
        }
        c.setOutputMarkupId(true);
        return true;
    }

}
