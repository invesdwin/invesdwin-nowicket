package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxchoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class AjaxChoice extends AValueObject {

    @Eager
    private Manufacturer manufacturer;
    private String model;

    /**
     * Implicit choices of enum in select box, null is valid since not annotated with @NotNull
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final Manufacturer manufacturer) {
        if (this.manufacturer != manufacturer) {
            model = null;
        }
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * Explicit choices in select box, note that you can return any type of iterable or even arrays
     */
    public List<String> getModelChoice() {
        if (manufacturer == null) {
            return null; //no choice
        } else {
            final List<String> models = new ArrayList<String>();
            models.add(null); //no selection is also a valid selection
            models.addAll(Arrays.asList(manufacturer.getModels()));
            return models;
        }
    }

    public void submit() throws Exception {
        if (manufacturer == null) {
            //we can use different colors in the status messages by defining different types (though normally we prefer validators for things like this
            GuiService.get().showStatusMessage(new StatusMessageConfig().withMessage("Please select a manufacturer!")
                    .withType(StatusMessageType.error));
            return;
        }
        if (model == null) {
            //we could also throw exceptions to abort a call and show a helpful message box
            throw new Exception("Please select a model for manufacturer '<b>" + manufacturer + "</b>'!");
        }

        GuiService.get().showStatusMessage(new StatusMessageConfig()
                .withMessage("You have selected: " + manufacturer + " " + model).withType(StatusMessageType.success));
    }

}
