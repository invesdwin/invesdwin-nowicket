package de.invesdwin.nowicket.component.modal.panel;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Strings;

@GeneratedMarkup
@NotThreadSafe
public class ModalMessage extends AValueObject {

    private final String title;
    private final String message;

    public ModalMessage(final String message) {
        this(null, message);
    }

    public ModalMessage(final String title, final String message) {
        this.title = title;
        //umlauts need to be converted before non printable ascii gets removed
        this.message = Strings.stripNonPrintableAscii(Strings.escapeHtmlSymbolsWithoutDestroyingMarkup(message.replace(
                "\n", "<br/>")));
    }

    public String getMessage() {
        return message;
    }

    @ModalCloser
    public void ok() {}

    public String title() {
        return title;
    }

}
