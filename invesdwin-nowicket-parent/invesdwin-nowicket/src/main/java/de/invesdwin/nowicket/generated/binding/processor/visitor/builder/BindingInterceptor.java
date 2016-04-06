package de.invesdwin.nowicket.generated.binding.processor.visitor.builder;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;
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

@NotThreadSafe
public class BindingInterceptor implements IBindingBuilder, Serializable {

    @Override
    public Component createForm(final FormHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createModal(final ModalHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createFeedback(final FeedbackHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createTable(final TableHtmlElement e) {
        return create(e);
    }

    @Override
    public IColumn<Object, String> createSubmitButtonColumn(final TableSubmitButtonColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<Object, String> createDateColumn(final TableDateColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<Object, String> createNumberColumn(final TableNumberColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<Object, String> createRemoveFromButtonColumn(final TableRemoveFromButtonColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<Object, String> createTextColumn(final TableTextColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createFileAnchorColumn(final TableAnchorColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createResourceAnchorColumn(final TableAnchorColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createResourceReferenceAnchorColumn(final TableAnchorColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createUrlAnchorColumn(final TableAnchorColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createContainerColumn(final TableContainerColumnHtmlElement e) {
        return createTableColumn(e);
    }

    @Override
    public IColumn<? extends Object, String> createSelectionButtonColumn(
            final TableSelectionButtonColumnHtmlElement e) {
        return createTableColumn(e);
    }

    /**
     * this can be overridden to create any type of column; alternatively one can override any of the other
     * createXYZColumn methods to avoid the need for casting
     */
    protected IColumn<Object, String> createTableColumn(final ATableColumnHtmlElement<?, ?> e) {
        return null;
    }

    @Override
    public Component createTabbed(final TabbedHtmlElement e) {
        return create(e);
    }

    /**
     * this can be overridden to create a tabbed column
     */
    @Override
    public ITab createTab(final IHtmlElement<?, ?> e, final IModel<String> tabTitleModel,
            final IModel<Object> targetObjectModel) {
        return null;
    }

    @Override
    public Component createUnorderedList(final UnorderedListHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createGridColumn(final GridColumnHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createSubmitButton(final SubmitButtonHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createUploadButton(final UploadButtonHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createCheckBoxInput(final CheckBoxInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createBooleanCheckBoxInput(final CheckBoxInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createMultiSelectionCheckBoxInput(final CheckBoxInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createSingleSelectionCheckBoxInput(final CheckBoxInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createSelect(final SelectHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createSingleSelectionSelect(final SelectHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createMultiSelectionSelect(final SelectHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createTextInput(final TextInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createAnchor(final AnchorHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createFileAnchor(final AnchorHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createResourceAnchor(final AnchorHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createResourceReferenceAnchor(final AnchorHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createUrlAnchor(final AnchorHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createImage(final ImageHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createDateInput(final DateInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createNumberInput(final NumberInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createTextArea(final TextAreaHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createRadioInput(final RadioInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createPasswordInput(final PasswordInputHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createLabel(final LabelHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createLegend(final LegendHtmlElement e) {
        return create(e);
    }

    @Override
    public Component createUnknown(final IUnknownHtmlElement<?> e) {
        return create(e);
    }

    /**
     * this can be overridden to create any type of component; alternatively one can override any of the other createXYZ
     * methods to avoid the need for casting
     */
    protected Component create(final IHtmlElement<?, ?> e) {
        return null;
    }

    @Override
    public boolean configureRoot(final RootHtmlElement e, final MarkupContainer c) {
        return false;
    }

    @Override
    public boolean configure(final IHtmlElement<?, ?> e, final Component c) {
        return false;
    }

}
