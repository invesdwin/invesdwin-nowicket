package com.granatasoft.remotelist.website.components.login.button.child;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class LoginButtonsChildPanel extends Panel {

    public LoginButtonsChildPanel(final String id, final IModel<LoginButtonsChild> model, final boolean addBtnClasses) {
        super(id, model);
        new GeneratedBinding(this).bind();
        setRenderBodyOnly(true);
        if (addBtnClasses) {
            visitChildren(Button.class, new IVisitor<Button, Void>() {
                @Override
                public void component(final Button object, final IVisit<Void> visit) {
                    object.add(AttributeModifier.append("class", getBtnClass()));
                }
            });
        }
    }

    public String getBtnClass() {
        return "btn btn-default";
    }
}
