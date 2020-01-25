package com.granatasoft.remotelist.website.panels.applicationinstance.update;


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
public class UpdateApplicationInstancePanel extends Panel {

    public UpdateApplicationInstancePanel(final String id, final IModel<UpdateApplicationInstance> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            public Component createSelect(final SelectHtmlElement e) {
                if (e.getWicketId().equals(UpdateApplicationInstanceConstants.server)) {
                    final ModelPaletteOptions modelPaletteOptions = new ModelPaletteOptions();
                    modelPaletteOptions.withMaxRows(5);
                    return new ModelPalette(e, modelPaletteOptions);
                }
                return super.createSelect(e);
            }
        }).bind();
    }
}
