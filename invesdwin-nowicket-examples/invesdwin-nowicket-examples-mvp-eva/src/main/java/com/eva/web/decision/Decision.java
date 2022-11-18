package com.eva.web.decision;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.component.modal.panel.ModalMessage;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import jakarta.validation.constraints.NotNull;

@NotThreadSafe
@GeneratedMarkup
public class Decision extends AValueObject {

    @NotNull
    private CompanyType companyType;

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(final CompanyType companyType) {
        this.companyType = companyType;
    }

    public void ok() {
        GuiService.get().showModalPanel(new ModalMessage("Success?!?", "Yay! Though maybe ask Eva?"));
    }

    public String evaImg() {
        return "eva.png";
    }

}
