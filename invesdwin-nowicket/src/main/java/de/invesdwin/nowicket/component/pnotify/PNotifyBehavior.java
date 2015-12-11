package de.invesdwin.nowicket.component.pnotify;

import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.StringResourceModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.time.duration.Duration;
import de.invesdwin.util.time.fdate.FTimeUnit;

@NotThreadSafe
public class PNotifyBehavior extends Behavior {

    public static final String DEFAULT_ICON_CSS_CLASS = "<default>";
    public static final PNotifyType DEFAULT_TYPE = PNotifyType.info;

    private Duration duration;
    private PNotifyType type = DEFAULT_TYPE;
    private String iconCssClass = DEFAULT_ICON_CSS_CLASS;
    private PNotifyPosition position = PNotifyPosition.bottom_right;
    private boolean buttons = true;
    private Integer widthPixels;
    private String title;
    private String text;
    private String addclass = "";
    private boolean stack = true;
    private boolean iconOnly;

    public PNotifyBehavior withType(final PNotifyType type) {
        this.type = type;
        return this;
    }

    public PNotifyBehavior withIconCssClass(final String iconCssClass) {
        this.iconCssClass = iconCssClass;
        return this;
    }

    public PNotifyBehavior withPosition(final PNotifyPosition position) {
        this.position = position;
        return this;
    }

    public PNotifyBehavior withDuration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    public PNotifyBehavior withButtons(final boolean buttons) {
        this.buttons = buttons;
        return this;
    }

    public PNotifyBehavior withWidthPixels(final Integer widthPixels) {
        this.widthPixels = widthPixels;
        return this;
    }

    public PNotifyBehavior withIconOnly() {
        this.iconOnly = true;
        return withWidthPixels(40);
    }

    public PNotifyBehavior withAddclass(final String addclass) {
        this.addclass = addclass;
        return this;
    }

    public PNotifyBehavior withTitle(final String title) {
        this.title = title;
        return this;
    }

    public PNotifyBehavior withText(final String text) {
        this.text = text;
        return this;
    }

    public PNotifyBehavior withStack(final boolean stack) {
        this.stack = stack;
        return this;
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        final CharSequence js = createJavaScript(component);
        response.render(OnDomReadyHeaderItem.forScript(js));
    }

    private CharSequence createJavaScript(final Component component) {
        final StringBuilder sb = new StringBuilder();
        sb.append("new PNotify({\n");
        sb.append("    history: false");
        sb.append(",\n");
        sb.append("    mouse_reset: false");
        configureTextAndTitle(component, sb);
        if (type != null) {
            sb.append(",\n");
            sb.append("    type: '");
            sb.append(type.toString());
            sb.append("'");
        }
        if (!DEFAULT_ICON_CSS_CLASS.equals(iconCssClass)) {
            sb.append(",\n");
            sb.append("    icon: ");
            if (iconCssClass == null) {
                sb.append("false");
            } else {
                sb.append(iconCssClass);
            }
        }
        if (duration != null) {
            sb.append(",\n");
            sb.append("    delay: ");
            sb.append(duration.longValue(FTimeUnit.MILLISECONDS));
        }
        configurePosition(sb);
        if (!buttons) {
            sb.append(",\n");
            sb.append("    buttons: {\n");
            sb.append("        closer: false,\n");
            sb.append("        sticker: false\n");
            sb.append("    }");
        }
        if (widthPixels != null) {
            sb.append(",\n");
            sb.append("    width: '");
            sb.append(widthPixels);
            sb.append("px'");
        }
        if (Strings.isNotBlank(addclass)) {
            sb.append(",\n");
            sb.append("    addclass: '");
            sb.append(addclass.trim());
            sb.append("'");
        }
        sb.append("\n});");
        return sb;
    }

    private void configurePosition(final StringBuilder sb) {
        if (position == PNotifyPosition.bottom_right) {
            addclass += " stack-bottomright";
            if (stack) {
                sb.append(",\n");
                sb.append("    stack: pnotify_stack_bottomright");
            }
        }
        if (!stack) {
            sb.append(",\n");
            sb.append("    stack: false");
        }
    }

    private void configureTextAndTitle(final Component component, final StringBuilder sb) {
        if (!iconOnly) {
            if (title != null) {
                sb.append(",\n");
                sb.append("    title: '");
                sb.append(getEscapedMessage(component, title));
                sb.append("'");
            }
            if (text != null) {
                sb.append(",\n");
                sb.append("    text: '");
                sb.append(getEscapedMessage(component, text));
                sb.append("'");
            }
        } else if (title != null || text != null) {
            throw new IllegalArgumentException(
                    "With iconOnly enabled, neither title [" + title + "] nor text [" + text + "] is supported!");
        }
    }

    private String getEscapedMessage(final Component component, final String message) {
        String localizedMessage;
        if (message == null) {
            return null;
        }
        try {
            localizedMessage = new StringResourceModel(message, component, HtmlContext.getModel(component), message)
                    .getObject();
        } catch (final MissingResourceException e) {
            localizedMessage = message;
        }
        return localizedMessage.replace("'", "\\'").replace("\"", "&quot;");
    }

    @Override
    public boolean isTemporary(final Component component) {
        return true;
    }

}
