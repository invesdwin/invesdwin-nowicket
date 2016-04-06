package de.invesdwin.nowicket.generated.binding.processor.visitor.builder;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;

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
import de.invesdwin.nowicket.generated.binding.processor.element.TabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableAnchorColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableContainerColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableDateColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableNumberColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableRemoveFromButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSelectionButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableSubmitButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableTextColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextAreaHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TextInputHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UnorderedListHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.UploadButtonHtmlElement;

public interface IBindingBuilder {

    Component createForm(FormHtmlElement e);

    Component createModal(ModalHtmlElement e);

    Component createFeedback(FeedbackHtmlElement e);

    Component createTable(TableHtmlElement e);

    IColumn<? extends Object, String> createRemoveFromButtonColumn(TableRemoveFromButtonColumnHtmlElement e);

    IColumn<? extends Object, String> createSubmitButtonColumn(TableSubmitButtonColumnHtmlElement e);

    IColumn<? extends Object, String> createDateColumn(TableDateColumnHtmlElement e);

    IColumn<? extends Object, String> createNumberColumn(TableNumberColumnHtmlElement e);

    IColumn<? extends Object, String> createTextColumn(TableTextColumnHtmlElement e);

    IColumn<? extends Object, String> createUrlAnchorColumn(TableAnchorColumnHtmlElement e);

    IColumn<? extends Object, String> createFileAnchorColumn(TableAnchorColumnHtmlElement e);

    IColumn<? extends Object, String> createResourceAnchorColumn(TableAnchorColumnHtmlElement e);

    IColumn<? extends Object, String> createResourceReferenceAnchorColumn(TableAnchorColumnHtmlElement e);

    IColumn<? extends Object, String> createContainerColumn(TableContainerColumnHtmlElement e);

    IColumn<? extends Object, String> createSelectionButtonColumn(TableSelectionButtonColumnHtmlElement e);

    Component createUnorderedList(UnorderedListHtmlElement e);

    Component createTabbed(TabbedHtmlElement e);

    ITab createTab(final IHtmlElement<?, ?> e, IModel<String> tabTitleModel, final IModel<Object> targetObjectModel);

    Component createGridColumn(GridColumnHtmlElement e);

    Component createSubmitButton(SubmitButtonHtmlElement e);

    Component createUploadButton(UploadButtonHtmlElement e);

    Component createCheckBoxInput(CheckBoxInputHtmlElement e);

    Component createBooleanCheckBoxInput(CheckBoxInputHtmlElement e);

    Component createSingleSelectionCheckBoxInput(CheckBoxInputHtmlElement e);

    Component createMultiSelectionCheckBoxInput(CheckBoxInputHtmlElement e);

    Component createSelect(SelectHtmlElement e);

    Component createSingleSelectionSelect(SelectHtmlElement e);

    Component createMultiSelectionSelect(SelectHtmlElement e);

    Component createTextInput(TextInputHtmlElement e);

    Component createAnchor(AnchorHtmlElement e);

    Component createUrlAnchor(AnchorHtmlElement e);

    Component createFileAnchor(AnchorHtmlElement e);

    Component createResourceAnchor(AnchorHtmlElement e);

    Component createResourceReferenceAnchor(AnchorHtmlElement e);

    Component createImage(ImageHtmlElement e);

    Component createDateInput(DateInputHtmlElement e);

    Component createNumberInput(NumberInputHtmlElement e);

    Component createTextArea(TextAreaHtmlElement e);

    Component createRadioInput(RadioInputHtmlElement e);

    Component createPasswordInput(PasswordInputHtmlElement e);

    Component createLabel(LabelHtmlElement e);

    Component createLegend(LegendHtmlElement e);

    Component createUnknown(IUnknownHtmlElement<?> e);

    /**
     * @return true tells that no other configure calls should still be made, thus configuration is finished
     */
    boolean configureRoot(RootHtmlElement e, MarkupContainer c);

    /**
     * @return true tells that no other configure calls should still be made, thus configuration is finished
     */
    boolean configure(final IHtmlElement<?, ?> e, Component c);

}
