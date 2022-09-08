package de.invesdwin.nowicket.component.toastr;

import java.util.MissingResourceException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.StringResourceModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.time.date.FTimeUnit;
import de.invesdwin.util.time.duration.Duration;

@NotThreadSafe
public class ToastrBehavior extends Behavior {

    public static final ToastrType DEFAULT_TYPE = ToastrType.info;
    private static final String TOAST_CONTAINER = "toast-container";
    private static final String TOAST_CONTAINER_NOICON = "toast-container-noicon";

    private Duration duration;
    private ToastrType type = DEFAULT_TYPE;
    private boolean iconEnabled = true;
    private boolean progressEnabled = true;
    private ToastrPosition position = ToastrPosition.bottom_right;
    private boolean buttons = true;
    private Integer widthPixels;
    private String title;
    private String text;
    private String addclass = "";
    private boolean stack = true;
    private boolean iconOnly;

    public ToastrBehavior setType(final ToastrType type) {
        this.type = type;
        return this;
    }

    public ToastrBehavior setIconEnabled(final boolean iconEnabled) {
        this.iconEnabled = iconEnabled;
        return this;
    }

    public ToastrBehavior setPosition(final ToastrPosition position) {
        this.position = position;
        return this;
    }

    public ToastrBehavior setDuration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    public ToastrBehavior setProgressEnabled(final boolean progressEnabled) {
        this.progressEnabled = progressEnabled;
        return this;
    }

    public ToastrBehavior setButtons(final boolean buttons) {
        this.buttons = buttons;
        return this;
    }

    public ToastrBehavior setWidthPixels(final Integer widthPixels) {
        this.widthPixels = widthPixels;
        return this;
    }

    public ToastrBehavior setIconOnly() {
        this.iconOnly = true;
        return setWidthPixels(40);
    }

    public ToastrBehavior setAddclass(final String addclass) {
        this.addclass = addclass;
        return this;
    }

    public ToastrBehavior setTitle(final String title) {
        this.title = title;
        return this;
    }

    public ToastrBehavior setText(final String text) {
        this.text = text;
        return this;
    }

    public ToastrBehavior setStack(final boolean stack) {
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
        configureTextAndTitle(component, sb);
        if (type != null) {
            sb.append(",\n");
            sb.append("    type: '");
            sb.append(type.toString());
            sb.append("'");
        }
        if (!iconEnabled) {
            sb.append("\n");
            sb.append("toastr.options.containerClass = '");
            sb.append(TOAST_CONTAINER_NOICON);
            sb.append("';");
        } else {
            sb.append("\n");
            sb.append("toastr.options.containerClass = '");
            sb.append(TOAST_CONTAINER);
            sb.append("';");
        }
        if (duration != null) {
            sb.append(",\n");
            sb.append("    timeOut: ");
            sb.append(duration.longValue(FTimeUnit.MILLISECONDS));
            sb.append(",\n");
            sb.append("    extendedTimeOut: ");
            sb.append(duration.longValue(FTimeUnit.MILLISECONDS));
        }
        configurePosition(sb);
        if (!buttons) {
            sb.append(",\n");
            sb.append("    closeButton: false");
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
        if (position == ToastrPosition.bottom_right) {
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
            localizedMessage = new StringResourceModel(message, component, HtmlContext.getModel(component))
                    .setDefaultValue(message)
                    .getObject();
        } catch (final MissingResourceException e) {
            localizedMessage = message;
        }
        return localizedMessage.replace("'", "\\'").replace("\"", "&quot;").replace("\n", "<br>");
    }

    @Override
    public boolean isTemporary(final Component component) {
        return true;
    }

}
