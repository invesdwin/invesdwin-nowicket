package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column.selection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableSelectionButtonColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;

@NotThreadSafe
public class SelectionColumnCheckBoxPanel extends Panel {

    public SelectionColumnCheckBoxPanel(final String id, final IModel<Object> rowModel,
            final TableSelectionButtonColumnHtmlElement element) {
        super(id);
        final CheckBox checkBox = newCheckBox("checkBox", element.newBooleanModel(rowModel));
        add(checkBox);
        setRenderBodyOnly(true);
        add(new ModelComponentBehavior(element, checkBox, rowModel));
    }

    private CheckBox newCheckBox(final String id, final IModel<Boolean> model) {
        return new CheckBox(id, model);
    }

}
