package de.invesdwin.nowicket.examples.guide.page.wicket.forminput;

import java.text.ParseException;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;
import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.component.modal.panel.ModalMessage;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.Arrays;

@GeneratedMarkup
@NotThreadSafe
public class FormInput extends AValueObject {

    @NotNull
    private String string = "test";
    @NotNull
    private Integer integer = 100;
    @NotNull
    private Double doublee = 20.5;
    @NotNull
    private Boolean booleann = false;
    @NotNull
    @Range(min = 0, max = 100)
    private Integer valueBetween0and100 = 50;
    @NotNull
    @URL
    private String url = "https://github.com/invesdwin/invesdwin-nowicket/";
    private String usPhoneNumber = "(123) 456-1234";
    private Integer selectANumberRadioChoicee = 1;
    private Integer selectANumberRadioGroup;
    private List<Integer> selectOneOrMoreNumbersCheckGroup;
    private List<String> yourFavoriteSites;
    /*
     * values that can be edited should not be internationalized, but we still show here how to i18n nested panel models
     */
    private final List<Line> listView = Arrays.asList(new Line("line.1"), new Line("line.2"), new Line("line.3"));
    private final Multiply multiply = new Multiply();

    public FormInput() {
        dirtyTracker().startTrackingChangesDirectly();
    }

    public String getString() {
        return string;
    }

    public void setString(final String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(final Integer integer) {
        this.integer = integer;
    }

    public Double getDoublee() {
        return doublee;
    }

    public void setDoublee(final Double doublee) {
        this.doublee = doublee;
    }

    public Boolean getBooleann() {
        return booleann;
    }

    public void setBooleann(final Boolean booleann) {
        this.booleann = booleann;
    }

    public Integer getValueBetween0and100() {
        return valueBetween0and100;
    }

    public void setValueBetween0and100(final Integer valueBetween0and100) {
        this.valueBetween0and100 = valueBetween0and100;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUsPhoneNumber() {
        return usPhoneNumber;
    }

    public void setUsPhoneNumber(final String usPhoneNumber) {
        this.usPhoneNumber = usPhoneNumber;
    }

    /**
     * Instead of using the MaskConverter in the Page, we just validate the value here with a custom validator.
     */
    public String validateUsPhoneNumber(final String newValue) {
        try {
            final MaskFormatter maskFormatter = new MaskFormatter("(###) ###-####");
            maskFormatter.setValueClass(String.class);
            maskFormatter.setAllowsInvalid(true);
            maskFormatter.setValueContainsLiteralCharacters(true);
            final Object obj = maskFormatter.stringToValue(newValue);
            if (obj != null) {
                return null;
            }
        } catch (final ParseException e) {
            //ignore
        }
        //so we can provide a different message than "is not a valid string"
        return "does not match the mask";
    }

    public Integer getSelectANumberRadioChoicee() {
        return selectANumberRadioChoicee;
    }

    public void setSelectANumberRadioChoicee(final Integer selectANumberRadioChoicee) {
        this.selectANumberRadioChoicee = selectANumberRadioChoicee;
    }

    public List<Integer> getSelectANumberRadioChoiceeChoice() {
        return Arrays.asList(1, 2, 3);
    }

    public Integer getSelectANumberRadioGroup() {
        return selectANumberRadioGroup;
    }

    public void setSelectANumberRadioGroup(final Integer selectANumberRadioGroup) {
        this.selectANumberRadioGroup = selectANumberRadioGroup;
    }

    public List<Integer> getSelectANumberRadioGroupChoice() {
        return Arrays.asList(1, 2, 3);
    }

    public List<Integer> getSelectOneOrMoreNumbersCheckGroup() {
        return selectOneOrMoreNumbersCheckGroup;
    }

    public void setSelectOneOrMoreNumbersCheckGroup(final List<Integer> selectOneOrMoreNumbersCheckGroup) {
        this.selectOneOrMoreNumbersCheckGroup = selectOneOrMoreNumbersCheckGroup;
    }

    public List<Integer> getSelectOneOrMoreNumbersCheckGroupChoice() {
        return Arrays.asList(1, 2, 3);
    }

    public List<String> getYourFavoriteSites() {
        return yourFavoriteSites;
    }

    public void setYourFavoriteSites(final List<String> yourFavoriteSites) {
        this.yourFavoriteSites = yourFavoriteSites;
    }

    public List<String> getYourFavoriteSitesChoice() {
        return Arrays.asList("9gag.com", "stackoverflow.com", "xkcd.com");
    }

    public List<Line> getListView() {
        return listView;
    }

    @BeanPathEndPoint
    public Multiply getMultiply() {
        return multiply;
    }

    public void save() {
        GuiService.get().showStatusMessage(i18n("saved"), toStringMultiline());
    }

    private String i18n(final String property) {
        return GuiService.i18n(FormInputPage.class, property);
    }

    /**
     * Forced makes the button work even if validation errors are on the page; See FormInputTest for a model test case
     * on this.
     */
    @Forced
    public void reset() {
        if (dirtyTracker().isDirty()) {
            final StringBuilder message = new StringBuilder();
            message.append(i18n("your.inputs.will.be.lost"));
            message.append(":<br><ul>");
            for (final String beanPath : dirtyTracker().getChangedBeanPaths()) {
                message.append("<li>");
                message.append(i18n(beanPath));
                message.append("</li>");
            }
            message.append("</ul>");
            message.append(i18n("press.cancel.to.keep.your.inputs"));
            message.append(".");
            GuiService.get().showModalPanel(new ModalMessage("warning", message.toString()) {

                @ModalCloser
                @Override
                public void ok() {
                    //reset by redirecting to a clean page
                    GuiService.get().showPage(new FormInput());
                    GuiService.get()
                            .showStatusMessage(new StatusMessageConfig().setType(StatusMessageType.success)
                                    .setTitle(i18n("reset"))
                                    .setMessage(i18n("reset.success")));
                }

                public String okTitle() {
                    //also internationalizing property here
                    return "reset";
                }

                @Override
                public boolean hideCancel() {
                    return false;
                }

                public String cancelTitle() {
                    return "cancel";
                }
            });
        } else {
            GuiService.get()
                    .showStatusMessage(new StatusMessageConfig().setType(StatusMessageType.error)
                            .setTitle(i18n("reset"))
                            .setMessage(i18n("reset.error")));
        }
    }

    @NotNull
    public UseLocale getUseLocale() {
        return UseLocale.valueOf(AWebSession.get().getLocale());
    }

    @Eager
    public void setUseLocale(final UseLocale useLocale) {
        AWebSession.get().setLocale(useLocale.getLocale());
        //disable dirty tracking for this property
        dirtyTracker().markClean(FormInputConstants.useLocale);
    }

}
