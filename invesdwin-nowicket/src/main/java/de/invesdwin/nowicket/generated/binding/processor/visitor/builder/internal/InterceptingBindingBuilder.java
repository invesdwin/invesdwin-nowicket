package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.internal;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.tabs.ITab;

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
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.DefaultBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;

@NotThreadSafe
public class InterceptingBindingBuilder implements IBindingBuilder {

    private final List<IBindingBuilder> orderedBindingBuilders;

    public InterceptingBindingBuilder(final List<IBindingBuilder> orderedBindingBuilders) {
        this.orderedBindingBuilders = orderedBindingBuilders;
    }

    @Override
    public Component createForm(final FormHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createForm(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    private IllegalStateException newIllegalStateException() {
        return new IllegalStateException("At least " + DefaultBindingBuilder.class.getSimpleName()
                + " should return a component! Maybe your intercetor is overriding the wrong createXYZ() method?");
    }

    @Override
    public Component createModal(final ModalHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createModal(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createFeedback(final FeedbackHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createFeedback(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createTable(final TableHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createTable(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createSubmitButtonColumn(final TableSubmitButtonColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createSubmitButtonColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createDateColumn(final TableDateColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createDateColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createNumberColumn(final TableNumberColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createNumberColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createRemoveFromButtonColumn(final TableRemoveFromButtonColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createRemoveFromButtonColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createTextColumn(final TableTextColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createTextColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createFileAnchorColumn(final TableAnchorColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createFileAnchorColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createResourceAnchorColumn(final TableAnchorColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createResourceAnchorColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createResourceReferenceAnchorColumn(final TableAnchorColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createResourceReferenceAnchorColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public IColumn<? extends Object, String> createUrlAnchorColumn(final TableAnchorColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final IColumn<? extends Object, String> c = b.createUrlAnchorColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createTabbed(final TabbedHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createTabbed(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public ITab createTabbedColumn(final TabbedColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final ITab c = b.createTabbedColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createUnorderedList(final UnorderedListHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createUnorderedList(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createGridColumn(final GridColumnHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createGridColumn(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createSubmitButton(final SubmitButtonHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createSubmitButton(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createUploadButton(final UploadButtonHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createUploadButton(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createCheckBoxInput(final CheckBoxInputHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createCheckBoxInput(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createSelect(final SelectHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createSelect(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createSingleSelect(final SelectHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createSingleSelect(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createMultiSelect(final SelectHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createMultiSelect(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createTextInput(final TextInputHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createTextInput(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createAnchor(final AnchorHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createAnchor(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createFileAnchor(final AnchorHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createFileAnchor(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createResourceAnchor(final AnchorHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createResourceAnchor(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createResourceReferenceAnchor(final AnchorHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createResourceReferenceAnchor(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createUrlAnchor(final AnchorHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createUrlAnchor(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createImage(final ImageHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createImage(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createDateInput(final DateInputHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createDateInput(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createNumberInput(final NumberInputHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createNumberInput(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createTextArea(final TextAreaHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createTextArea(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createRadioInput(final RadioInputHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createRadioInput(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createPasswordInput(final PasswordInputHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createPasswordInput(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createLabel(final LabelHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createLabel(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createLegend(final LegendHtmlElement e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createLegend(e);
            if (c != null) {
                return c;
            }
        }
        throw newIllegalStateException();
    }

    @Override
    public Component createUnknown(final IUnknownHtmlElement<?> e) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            final Component c = b.createUnknown(e);
            if (c != null) {
                return c;
            }
        }
        //unknown can in fact return null
        return null;
    }

    @Override
    public boolean configureRoot(final RootHtmlElement e, final MarkupContainer c) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            if (b.configureRoot(e, c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean configure(final IHtmlElement<?, ?> e, final Component c) {
        for (final IBindingBuilder b : orderedBindingBuilders) {
            if (b.configure(e, c)) {
                return true;
            }
        }
        return false;
    }

}
