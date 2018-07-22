package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.properties;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;

import de.invesdwin.nowicket.generated.markup.processor.context.MarkupType;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.AnchorModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.CheckBoxInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.DateInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.FieldSetOpenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.HiddenModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.IButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.NumberInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.PanelModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.RootModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SelectModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.SubmitButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TabbedModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.UploadButtonModelElement;
import de.invesdwin.nowicket.generated.markup.processor.visitor.AModelVisitor;

@NotThreadSafe
public class PropertiesVisitor extends AModelVisitor {

    private final MarkupType markupType;
    private final Properties existingProperties;
    private final List<Properties> newPropertiesOrdered;

    public PropertiesVisitor(final ModelClassContext context, final MarkupType markupType) {
        super(context);
        this.markupType = markupType;
        this.existingProperties = loadExistingProperties();
        this.newPropertiesOrdered = new ArrayList<Properties>();
    }

    protected Properties loadExistingProperties() {
        final File propertiesFile = getContext().getPropertiesFile(markupType);
        final Properties properties = new Properties();
        if (propertiesFile.exists()) {
            try {
                properties.load(new FileInputStream(propertiesFile));
            } catch (final IOException e) {
                throw new RuntimeException();
            }
        }
        return properties;
    }

    @Override
    public void visitRoot(final RootModelElement e) {
        //ignore
    }

    @Override
    public void visitTextInput(final TextInputModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitDateInput(final DateInputModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitNumberInput(final NumberInputModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitSelect(final SelectModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitCheckBoxInput(final CheckBoxInputModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitSubmitButton(final SubmitButtonModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitAnchor(final AnchorModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitUploadButton(final UploadButtonModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitFieldSetOpen(final FieldSetOpenModelElement e) {
        addProperty(e);
    }

    @Override
    public void visitPanel(final PanelModelElement e) {
        //no need to add property for panel elment itself, since it cannot be localized here anyway
    }

    @Override
    public void visitTabbed(final TabbedModelElement e) {
        //no need to add property for tabbed elment itself, since fieldset open already added it
        for (final TabbedColumnModelElement column : e.getRawColumns()) {
            addProperty(column);
        }
    }

    @Override
    public void visitTable(final TableModelElement e) {
        //no need to add property for table elment itself, since fieldset open already added it
        for (final ATableColumnModelElement<?> column : e.getRawColumns()) {
            addProperty(column);
        }
    }

    private void addProperty(final IModelElement<?> e) {
        if (addProperty(e.getWicketId(), e.getStaticTitle()) && e instanceof IButtonModelElement<?>) {
            // only add icon properties initially on button add
            final IButtonModelElement<?> cE = (IButtonModelElement<?>) e;
            addProperty(cE.getIconCssClassPropertyName(), cE.getDefaultIconCssClass());
        }
    }

    private boolean addProperty(final String key, final String value) {
        if (existingProperties.get(key) == null) {
            final Properties newProperty = new Properties();
            newProperty.setProperty(key, value);
            newPropertiesOrdered.add(newProperty);
            return true;
        }
        return false;
    }

    @Override
    public void visitFieldSetClose() {
        // ignore
    }

    @Override
    public void visitHidden(final HiddenModelElement e) {
        // ignore
    }

    @Override
    public void finish() {
        if (newPropertiesOrdered.size() > 0) {
            try {
                /*
                 * java.util.Properties does automatic unicode encoding of values here
                 * 
                 * properties.store() does not preserve order, thus using separate properties instances here
                 */
                String appendString = "";
                if (existingProperties.size() > 0) {
                    appendString += "\n";
                }
                for (final Properties newProperties : newPropertiesOrdered) {
                    final ByteArrayOutputStream encodedProperties = new ByteArrayOutputStream();
                    newProperties.store(encodedProperties, "");
                    final BufferedReader reader = new BufferedReader(new StringReader(encodedProperties.toString()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("=")) {
                            appendString += line + "\n";
                        }
                    }
                }
                try (FileOutputStream fos = new FileOutputStream(getContext().getPropertiesFile(markupType), true)) {
                    IOUtils.write(appendString, fos, Charset.defaultCharset());
                }
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
