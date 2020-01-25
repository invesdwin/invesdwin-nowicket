package com.granatasoft.remotelist.website.pages.auth;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.website.pages.ARemotelistPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.auth.SignOutPanel;

@NotThreadSafe
public class SignOutPage extends ARemotelistPage {

    public static final String MOUNT_PATH = "/signout";

    public SignOutPage() {
        super(null);
        add(new SignOutPanel("panel"));
        setTitleModel(new I18nModel(this, "sign.out.title"));
    }

}
