package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.simple.SimpleDataTypes;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.simple.SimpleDataTypesPanel;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables.Tables;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables.TablesPanel;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@MountPath("datatypes")
@NotThreadSafe
public class DataTypesPage extends AExampleWebPage {

    public DataTypesPage() {
        this(Model.of(new DataTypes()));
    }

    public DataTypesPage(final IModel<DataTypes> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if ("simpleDataTypesPanel".equals(e.getWicketId())) {
                    return new SimpleDataTypesPanel(e.getWicketId(), Model.of(new SimpleDataTypes()));
                }
                if ("tablesPanel".equals(e.getWicketId())) {
                    return new TablesPanel(e.getWicketId(), Model.of(new Tables()));
                }
                if ("thirdCarPanel".equals(e.getWicketId())) {
                    return new de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar.CarPanel(
                            e.getWicketId(), Model.of(
                                    new de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar.Car()));
                }
                return super.create(e);
            }
        }).bind();
    }

}
