package de.invesdwin.nowicket.generated.binding;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.MetaDataKey;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.DefaultSubmitButtonCallbackFactory;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallbackFactory;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.exception.DefaultSubmitButtonExceptionHandler;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.exception.ISubmitButtonExceptionHandler;

@ThreadSafe
public final class GeneratedBindingDefaults {

    private static final MetaDataKey<GeneratedBindingDefaults> META_DATA_KEY = new MetaDataKey<GeneratedBindingDefaults>() {
    };
    private static GeneratedBindingDefaults staticInstance = new GeneratedBindingDefaults();

    private BindingInterceptor defaultBindingInterceptor;
    private ISubmitButtonExceptionHandler defaultSubmitButtonExceptionHandler = new DefaultSubmitButtonExceptionHandler();
    private ISubmitButtonCallbackFactory defaultSubmitButtonCallbackFactory = new DefaultSubmitButtonCallbackFactory();
    private String defaultModelClassNameSuffix = null;

    private GeneratedBindingDefaults() {}

    public static synchronized GeneratedBindingDefaults get() {
        try {
            final ABaseWebApplication application = ABaseWebApplication.get();
            GeneratedBindingDefaults instance = application.getMetaData(META_DATA_KEY);
            if (instance == null) {
                if (staticInstance != null) {
                    instance = (GeneratedBindingDefaults) staticInstance.clone();
                } else {
                    instance = new GeneratedBindingDefaults();
                }
                application.setMetaData(META_DATA_KEY, instance);
            }
            return instance;
        } catch (final Throwable t) {
            //no application running, use static reference
            if (staticInstance == null) {
                staticInstance = new GeneratedBindingDefaults();
            }
            return staticInstance;
        }
    }

    public synchronized ISubmitButtonExceptionHandler getDefaultSubmitButtonExceptionHandler() {
        return defaultSubmitButtonExceptionHandler;
    }

    /**
     * Null resets to default.
     */
    public synchronized void setDefaultSubmitButtonExceptionHandler(
            final ISubmitButtonExceptionHandler defaultButtonExceptionHandler) {
        if (defaultButtonExceptionHandler == null) {
            this.defaultSubmitButtonExceptionHandler = new DefaultSubmitButtonExceptionHandler();
        } else {
            this.defaultSubmitButtonExceptionHandler = defaultButtonExceptionHandler;
        }
    }

    public synchronized void setDefaultBindingInterceptor(final BindingInterceptor defaultBindingInterceptor) {
        this.defaultBindingInterceptor = defaultBindingInterceptor;
    }

    public synchronized BindingInterceptor getDefaultBindingInterceptor() {
        return defaultBindingInterceptor;
    }

    public synchronized ISubmitButtonCallbackFactory getDefaultSubmitButtonCallbackFactory() {
        return defaultSubmitButtonCallbackFactory;
    }

    /**
     * Null resets to default.
     */
    public synchronized void setDefaultSubmitButtonCallbackFactory(
            final ISubmitButtonCallbackFactory defaultModelButtonCallbackFactory) {
        if (defaultModelButtonCallbackFactory == null) {
            this.defaultSubmitButtonCallbackFactory = new DefaultSubmitButtonCallbackFactory();
        } else {
            this.defaultSubmitButtonCallbackFactory = defaultModelButtonCallbackFactory;
        }
    }

    public void setDefaultModelNameSuffix(final String defaultModelClassNameSuffix) {
        this.defaultModelClassNameSuffix = defaultModelClassNameSuffix;
    }

    public String getDefaultModelClassNameSuffix() {
        return defaultModelClassNameSuffix;
    }
}
