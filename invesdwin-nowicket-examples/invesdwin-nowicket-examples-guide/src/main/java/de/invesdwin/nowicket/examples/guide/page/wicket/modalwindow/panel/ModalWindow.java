package de.invesdwin.nowicket.examples.guide.page.wicket.modalwindow.panel;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.time.date.FDate;

@NotThreadSafe
@GeneratedMarkup
public class ModalWindow extends AValueObject {

    private final int index;
    private String lastModalWindowResult;
    private FDate anExampleOfAComponentThatUsesHeaderContributors = new FDate();
    private final AtomicLong modalsOpened;
    private String result;

    public ModalWindow(final int index, final AtomicLong modalsOpened) {
        this.index = index;
        this.modalsOpened = modalsOpened;
        if (index > 0) {
            //here we show how easy it is to modify outer state from inside a modal
            modalsOpened.incrementAndGet();
        }
    }

    public String getModalWindowContent() {
        final StringBuilder sb = new StringBuilder("<b>Modals opened: ");
        sb.append(modalsOpened);
        sb.append("</b>");
        if (!hideCloseThisModalWithResultOk()) {
            sb.append("<br><br>Modal WINDOW content.");
        }
        return sb.toString();
    }

    public String getLastModalWindowResult() {
        return lastModalWindowResult;
    }

    public void openAnotherModalDialog() {
        GuiService.get().showModalPanel(new ModalWindow(index + 1, modalsOpened) {
            @ModalCloser
            @Override
            public void closeThisModalWithResultOk() {
                super.closeThisModalWithResultOk();
                /*
                 * here we pass the result to the caller, or more likely, the caller fetches it himself, a bit tricky
                 * here, but lastModalResult is from outer modal, getResult is from inner modal, though normally you
                 * would not nest the same model in itself, this is surely a more extreme example :)
                 */
                lastModalWindowResult = getResult();
            }

            @ModalCloser
            @Override
            public void closeThisModalWithResultCancel() {
                super.closeThisModalWithResultCancel();
                lastModalWindowResult = getResult();
            }
        });
    }

    /**
     * We change the title of the button dynamically
     */
    public String openAnotherModalDialogTitle() {
        if (hideCloseThisModalWithResultOk()) {
            return "Show modal dialog with panel";
        } else {
            //keep default button title
            return null;
        }
    }

    /**
     * Instead of calling the GuiService.hideModalPanel(), we can just annotate a button element with @ModalCloser to
     * close it automatically
     */
    @ModalCloser
    public void closeThisModalWithResultOk() {
        setResult("Ok");
    }

    private void setResult(final String result) {
        this.result = "Modal window " + index + " - close link " + result;
        if (Strings.isNotBlank(lastModalWindowResult)) {
            this.result += "<br>" + lastModalWindowResult;
        }
    }

    public boolean hideCloseThisModalWithResultOk() {
        return index == 0;
    }

    @ModalCloser
    public void closeThisModalWithResultCancel() {
        setResult("Cancel");
    }

    protected String getResult() {
        return result;
    }

    public boolean hideCloseThisModalWithResultCancel() {
        return hideCloseThisModalWithResultOk();
    }

    public String title() {
        return "This is modal window " + index;
    }

    public FDate getAnExampleOfAComponentThatUsesHeaderContributors() {
        return anExampleOfAComponentThatUsesHeaderContributors;
    }

    public void setAnExampleOfAComponentThatUsesHeaderContributors(
            final FDate anExampleOfAComponentThatUsesHeaderContributors) {
        this.anExampleOfAComponentThatUsesHeaderContributors = anExampleOfAComponentThatUsesHeaderContributors;
    }

    public boolean hideAnExampleOfAComponentThatUsesHeaderContributors() {
        return hideCloseThisModalWithResultOk();
    }

}
