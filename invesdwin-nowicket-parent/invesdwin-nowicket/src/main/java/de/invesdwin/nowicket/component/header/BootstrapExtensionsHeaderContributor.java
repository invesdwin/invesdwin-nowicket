package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.core.settings.BootstrapSettings;
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
    private boolean enableTableFixedHead = true;
    private EnableBootstrapTooltipsHeaderContributor enableBootstrapTooltips = new EnableBootstrapTooltipsHeaderContributor();

    public BootstrapExtensionsHeaderContributor(final BootstrapSettings bootstrapSettings) {
        this.bootstrapSettings = bootstrapSettings;
    }

    public BootstrapExtensionsHeaderContributor setBtnPrimaryEnterBinding(final boolean btnPrimaryEnterBinding) {
        this.btnPrimaryEnterBinding = btnPrimaryEnterBinding;
        return this;
    }

    public boolean isBtnPrimaryEnterBinding() {
        return btnPrimaryEnterBinding;
    }

    public BootstrapExtensionsHeaderContributor setOfflineJS(final boolean offlineJS) {
        this.offlineJS = offlineJS;
        return this;
    }

    public boolean isOfflineJS() {
        return offlineJS;
    }

    public BootstrapExtensionsHeaderContributor setDefaultFocusInitially(final boolean setDefaultFocusInitially) {
        this.setDefaultFocusInitially = setDefaultFocusInitially;
        return this;
    }

    public boolean isSetDefaultFocusInitially() {
        return setDefaultFocusInitially;
    }

    public BootstrapExtensionsHeaderContributor setAllowCopyPasteOnDisabledInputs(
            final boolean allowCopyPasteOnDisabledInputs) {
        this.allowCopyPasteOnDisabledInputs = allowCopyPasteOnDisabledInputs;
        return this;
    }

    public boolean isAllowCopyPasteOnDisabledInputs() {
        return allowCopyPasteOnDisabledInputs;
    }

    public BootstrapExtensionsHeaderContributor setDisableComponentsOnAjaxCall(
            final boolean disableComponentsOnAjaxCall) {
        this.disableComponentsOnAjaxCall = disableComponentsOnAjaxCall;
        return this;
    }

    public boolean isDisableComponentsOnAjaxCall() {
        return disableComponentsOnAjaxCall;
    }

    public BootstrapExtensionsHeaderContributor setUpdateFooterMarginOnResize(
            final boolean updateFooterMarginOnResize) {
        this.updateFooterMarginOnResize = updateFooterMarginOnResize;
        return this;
    }

    public boolean isUpdateFooterMarginOnResize() {
        return updateFooterMarginOnResize;
    }

    public BootstrapExtensionsHeaderContributor setEnableTableFixedHead(final boolean enableTableFixedHead) {
        this.enableTableFixedHead = enableTableFixedHead;
        return this;
    }

    public boolean isEnableTableFixedHead() {
        return enableTableFixedHead;
    }

    public BootstrapExtensionsHeaderContributor setEnableBootstrapTooltips(
            final EnableBootstrapTooltipsHeaderContributor enableBootstrapTooltips) {
        this.enableBootstrapTooltips = enableBootstrapTooltips;
        return this;
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
        if (enableTableFixedHead) {
            EnableTableFixedHeadJsReference.INSTANCE.renderHead(response);
        }
        if (enableBootstrapTooltips != null) {
            enableBootstrapTooltips.renderHead(response);
        }

        //misc
        PNotifyHeaderContributor.INSTANCE.renderHead(response);
        response.render(CssHeaderItem.forReference(FontAwesome4CssReference.instance()));
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
