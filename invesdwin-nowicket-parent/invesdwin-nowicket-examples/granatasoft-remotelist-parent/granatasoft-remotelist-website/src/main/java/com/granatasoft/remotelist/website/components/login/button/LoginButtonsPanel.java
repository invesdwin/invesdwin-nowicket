package com.granatasoft.remotelist.website.components.login.button;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.granatasoft.remotelist.website.components.login.button.child.LoginButtonsChild;
import com.granatasoft.remotelist.website.components.login.button.child.LoginButtonsChildPanel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@NotThreadSafe
public class LoginButtonsPanel extends Panel {

    private String btnClass;

    public LoginButtonsPanel(final String wicketId, final IModel<LoginButtons> model) {
        super(wicketId, model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {

            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (LoginButtonsConstants.masterButton.equals(e.getWicketId())) {
                    return newLoginButtonsChildPanel(e.getWicketId(), (IModel<LoginButtonsChild>) e.getModel(), true);
                } else if (LoginButtonsConstants.childButtons.equals(e.getWicketId())) {
                    final IModel<? extends List<LoginButtonsChild>> listViewModel = new IModel<List<LoginButtonsChild>>() {
                        @Override
                        public List<LoginButtonsChild> getObject() {
                            return model.getObject().getChildButtons();
                        }
                    };
                    return new ListView<LoginButtonsChild>(e.getWicketId(), listViewModel) {
                        @Override
                        protected void populateItem(final ListItem<LoginButtonsChild> item) {
                            item.add(newLoginButtonsChildPanel("childButtonsPanel", item.getModel(), false));
                        }
                    };
                } else if ("masterButtonToggle".equals(e.getWicketId())) {
                    return newMasterButtonToggle(e.getWicketId());
                } else if ("toolbar".equals(e.getWicketId())) {
                    return newToolbar(e.getWicketId());
                }
                return super.create(e);
            }

        }).bind();
    }

    protected LoginButtonsChildPanel newLoginButtonsChildPanel(final String id, final IModel<LoginButtonsChild> model,
            final boolean addBtnClasses) {
        return new LoginButtonsChildPanel(id, model, addBtnClasses);
    }

    protected Component newToolbar(final String id) {
        return new WebMarkupContainer(id);
    }

    protected Component newMasterButtonToggle(final String id) {
        return new WebMarkupContainer(id);
    }
}
