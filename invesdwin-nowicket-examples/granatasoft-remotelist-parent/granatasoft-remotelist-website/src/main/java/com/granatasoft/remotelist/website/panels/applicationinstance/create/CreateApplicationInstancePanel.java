package com.granatasoft.remotelist.website.panels.applicationinstance.create;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.SelectHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.palette.ModelPalette;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.palette.ModelPalette.ModelPaletteOptions;

@NotThreadSafe
public class CreateApplicationInstancePanel extends Panel {

    public CreateApplicationInstancePanel(final String id, final IModel<CreateApplicationInstance> model) {
        super(id, model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createSelect(final SelectHtmlElement e) {
                if (e.getWicketId().equals(CreateApplicationInstanceConstants.server)) {
                    final ModelPaletteOptions modelPaletteOptions = new ModelPaletteOptions();
                    modelPaletteOptions.setMaxRows(5);
                    return new ModelPalette(e, modelPaletteOptions);
                }
                return super.createSelect(e);
            }
        }).bind();
    }
}
