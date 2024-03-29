package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class GridColumnBorder extends Border {

    private final GridColumnHtmlElement element;
    private final Label help;
    private final AttributeAppender isInvalidBehavior;

    public GridColumnBorder(final GridColumnHtmlElement element) {
        super(element.getWicketId());
        this.element = element;

        this.isInvalidBehavior = new AttributeAppender("class", Model.of("is-invalid")) {
            @Override
            public boolean isTemporary(final Component component) {
                return true;
            }
        }.setSeparator(" ");
        this.help = new Label("help", Model.of()); //needs to escape markup or modals do not close
        help.setEscapeModelStrings(false);
        addToBorder(help);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        final Component component = element.getContext()
                .getComponentRegistry()
                .getComponent(element.getModelWicketId());
        final FormComponent<?> formComponent = Components.asFormComponent(component);
        if (formComponent != null) {
            final StringBuilder sb = new StringBuilder();
            if (showHelpText()) {
                if (formComponent.isVisibleInHierarchy() && !formComponent.isValid()) {
                    boolean firstMessage = true;
                    for (final FeedbackMessage message : formComponent.getFeedbackMessages()) {
                        if (!firstMessage) {
                            sb.append("<br>");
                        }
                        firstMessage = false;
                        sb.append(message.getMessage());
                    }
                    component.add(isInvalidBehavior);
                    add(isInvalidBehavior);
                }
                help.setDefaultModelObject(sb);
            }
            help.setVisible(sb.length() > 0);
        }
    }

    /**
     * return false here to disable the help text and instead only color the border
     */
    protected boolean showHelpText() {
        return true;
    }

    @Override
    public boolean isVisible() {
        final Component component = element.getContext()
                .getComponentRegistry()
                .getComponent(element.getModelWicketId());
        if (component != null) {
            return component.isVisible();
        } else {
            return super.isVisible();
        }
    }

}
