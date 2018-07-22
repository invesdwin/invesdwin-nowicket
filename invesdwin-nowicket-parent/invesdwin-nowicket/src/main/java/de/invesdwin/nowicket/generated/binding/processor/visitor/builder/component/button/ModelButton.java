package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxFallbackButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.invesdwin.nowicket.generated.binding.processor.element.SubmitButtonHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelButton extends BootstrapAjaxFallbackButton {

    private final SubmitButtonHtmlElement element;

    public ModelButton(final SubmitButtonHtmlElement element) {
        //menu button does not get any css classes, thus keeping the values set in markup
        super(element.getWicketId(), element.getTitleModel(), null, Type.Menu);
        this.element = element;
        final String iconCssClass = element.getIconCssClassModel().getObject();
        if (Strings.isNotBlank(iconCssClass)) {
            setIconType(new IconType(iconCssClass) {
                @Override
                public String cssClassName() {
                    return getCssClassName();
                }
            });
        }
    }

    @Override
    protected <L extends Serializable> Component newLabel(final String markupId, final IModel<L> model) {
        final Label label = (Label) super.newLabel(markupId, model);
        //allow styled properties for label
        label.setEscapeModelStrings(false);
        return label;
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        /*
         * Anchors don't work with fallback, but we just ignore the exception when still needed by menu etc in
         * bootstrap. Thus this workaround.
         */
        final boolean anchor = "a".equals(tag.getName());
        if (anchor) {
            tag.setName("button");
        }
        super.onComponentTag(tag);
        if (anchor) {
            tag.setName("a");
            //if missing, add href to make cursor display properly
            if (Strings.isBlank(tag.getAttribute("href"))) {
                tag.put("href", "#");
            }
        }
    }

    @Override
    protected void onSubmit(final Optional<AjaxRequestTarget> target) {
        element.getButtonCallback().onSubmit(getForm());
    }

    @Override
    protected void onError(final Optional<AjaxRequestTarget> target) {
        element.getButtonCallback().onError(getForm());
    }

}
