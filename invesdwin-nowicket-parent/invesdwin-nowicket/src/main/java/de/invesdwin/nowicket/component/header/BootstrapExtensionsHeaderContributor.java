package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.invesdwin.nowicket.component.header.offline.OfflineHeaderContributor;
import de.invesdwin.nowicket.component.modal.header.BootstrapModalHeaderContributor;
import de.invesdwin.nowicket.component.pnotify.header.PNotifyHeaderContributor;

@Immutable
public class BootstrapExtensionsHeaderContributor implements IHeaderContributor {

    private final BootstrapSettings bootstrapSettings;
    private boolean offlineJS = true;
    private boolean btnPrimaryEnterBinding = true;
    private boolean setDefaultFocusInitially = true;
    private boolean disableComponentsOnAjaxCall = true;
    private boolean allowCopyPasteOnDisabledInputs = true;
    private boolean updateFooterMarginOnResize = true;
    private EnableBootstrapTooltipsHeaderContributor enableBootstrapTooltips = new EnableBootstrapTooltipsHeaderContributor();

    public BootstrapExtensionsHeaderContributor(final BootstrapSettings bootstrapSettings) {
        this.bootstrapSettings = bootstrapSettings;
    }

    public BootstrapExtensionsHeaderContributor withBtnPrimaryEnterBinding(final boolean btnPrimaryEnterBinding) {
        this.btnPrimaryEnterBinding = btnPrimaryEnterBinding;
        return this;
    }

    public boolean isBtnPrimaryEnterBinding() {
        return btnPrimaryEnterBinding;
    }

    public BootstrapExtensionsHeaderContributor withOfflineJS(final boolean offlineJS) {
        this.offlineJS = offlineJS;
        return this;
    }

    public boolean isOfflineJS() {
        return offlineJS;
    }

    public BootstrapExtensionsHeaderContributor withSetDefaultFocusInitially(final boolean setDefaultFocusInitially) {
        this.setDefaultFocusInitially = setDefaultFocusInitially;
        return this;
    }

    public boolean isSetDefaultFocusInitially() {
        return setDefaultFocusInitially;
    }

    public BootstrapExtensionsHeaderContributor withAllowCopyPasteOnDisabledInputs(
            final boolean allowCopyPasteOnDisabledInputs) {
        this.allowCopyPasteOnDisabledInputs = allowCopyPasteOnDisabledInputs;
        return this;
    }

    public boolean isAllowCopyPasteOnDisabledInputs() {
        return allowCopyPasteOnDisabledInputs;
    }

    public BootstrapExtensionsHeaderContributor withDisableComponentsOnAjaxCall(
            final boolean disableComponentsOnAjaxCall) {
        this.disableComponentsOnAjaxCall = disableComponentsOnAjaxCall;
        return this;
    }

    public boolean isDisableComponentsOnAjaxCall() {
        return disableComponentsOnAjaxCall;
    }

    public BootstrapExtensionsHeaderContributor withUpdateFooterMarginOnResize(
            final boolean updateFooterMarginOnResize) {
        this.updateFooterMarginOnResize = updateFooterMarginOnResize;
        return this;
    }

    public boolean isUpdateFooterMarginOnResize() {
        return updateFooterMarginOnResize;
    }

    public void withEnableBootstrapTooltips(final EnableBootstrapTooltipsHeaderContributor enableBootstrapTooltips) {
        this.enableBootstrapTooltips = enableBootstrapTooltips;
    }

    public EnableBootstrapTooltipsHeaderContributor getEnableBootstrapTooltips() {
        return enableBootstrapTooltips;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        //bootstrap needs to be before bootstrap-modal
        response.render(CssHeaderItem.forReference(bootstrapSettings.getCssResourceReference()));
        response.render(JavaScriptHeaderItem.forReference(bootstrapSettings.getJsResourceReference()));
        renderHeadAfterBootstrap(response);
        BootstrapModalHeaderContributor.INSTANCE.renderHead(response);
        EnableTooltipsOnDisabledButtonsCssReference.INSTANCE.renderHead(response);
        //fix layout in IE8
        response.render(RespondJavaScriptReference.headerItem());

        //order is relevant here since scripts might depend on changes they do individually to the components
        if (disableComponentsOnAjaxCall) {
            DisableComponentsOnAjaxCallJsReference.INSTANCE.renderHead(response);
        }
        if (allowCopyPasteOnDisabledInputs) {
            AllowCopyPasteOnDisabledInputsJsReference.INSTANCE.renderHead(response);
        }
        if (btnPrimaryEnterBinding) {
            BtnPrimaryEnterBindingJsReference.INSTANCE.renderHead(response);
        }
        if (offlineJS) {
            OfflineHeaderContributor.INSTANCE.renderHead(response);
        }
        if (setDefaultFocusInitially) {
            SetDefaultFocusInitiallyJsReference.INSTANCE.renderHead(response);
        }
        if (updateFooterMarginOnResize) {
            UpdateFooterMarginOnResizeJsReference.INSTANCE.renderHead(response);
        }
        if (enableBootstrapTooltips != null) {
            enableBootstrapTooltips.renderHead(response);
        }

        //misc
        PNotifyHeaderContributor.INSTANCE.renderHead(response);
        response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
        response.render(CssHeaderItem.forReference(OpenWebIconsCssReference.instance()));
        AjaxIndicatorAppenderCssReference.INSTANCE.renderHead(response);
        AdditionalBootstrapClassesCssReference.INSTANCE.renderHead(response);
        response.render(JavaScriptHeaderItem.forReference(ModernizrMinJavaScriptReference.instance()));
        response.render(CssHeaderItem.forReference(BootstrapFixesCssReference.INSTANCE));
    }

    /**
     * You can put Themes here that will be rendered before bootstrap modal, but after bootstrap itself.
     */
    protected void renderHeadAfterBootstrap(final IHeaderResponse response) {}
}
