package com.granatasoft.remotelist.website.pages.auth;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.website.pages.ARemotelistPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.auth.SignInPanel;

@NotThreadSafe
public class SignInPage extends ARemotelistPage {

    public static final String MOUNT_PATH = "/signin";

    public SignInPage() {
        super(null);
        add(new SignInPanel("panel"));
        setTitleModel(new I18nModel(this, "sign.in.title"));
    }

}
