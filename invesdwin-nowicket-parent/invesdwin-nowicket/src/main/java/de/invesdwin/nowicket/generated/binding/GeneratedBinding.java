package de.invesdwin.nowicket.generated.binding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;

import de.invesdwin.nowicket.generated.binding.processor.HtmlProcessor;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.DefaultBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallbackFactory;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.exception.ISubmitButtonExceptionHandler;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.internal.InterceptingBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.internal.BindingVisitor;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class GeneratedBinding implements Serializable {

    private final HtmlContext context;
    private final List<BindingInterceptor> bindingInterceptors = new ArrayList<BindingInterceptor>();
    private ISubmitButtonExceptionHandler submitButtonExceptionHandler;
    private ISubmitButtonCallbackFactory submitButtonCallbackFactory;
    private boolean useSqlInjectionValidation;
    private InterceptingBindingBuilder bindingBuilder;

    public GeneratedBinding(final Page page) {
        this((MarkupContainer) page);
    }

    public GeneratedBinding(final Panel panel) {
        this((MarkupContainer) panel);
    }

    private GeneratedBinding(final MarkupContainer markupContainer) {
        this.submitButtonExceptionHandler = GeneratedBindingDefaults.get().getDefaultSubmitButtonExceptionHandler();
        this.submitButtonCallbackFactory = GeneratedBindingDefaults.get().getDefaultSubmitButtonCallbackFactory();
        this.useSqlInjectionValidation = GeneratedBindingDefaults.get().isUseSqlInjectionValidation();
        this.context = new HtmlContext(markupContainer, this);
    }

    public GeneratedBinding withSubmitButtonExceptionHandler(
            final ISubmitButtonExceptionHandler submitButtonExceptionHandler) {
        Assertions.assertThat(submitButtonExceptionHandler).isNotNull();
        Assertions.assertThat(bindingBuilder)
                .as("%s can only be added before bind() call", ISubmitButtonExceptionHandler.class.getSimpleName())
                .isNull();
        this.submitButtonExceptionHandler = submitButtonExceptionHandler;
        return this;
    }

    public ISubmitButtonExceptionHandler getSubmitButtonExceptionHandler() {
        return submitButtonExceptionHandler;
    }

    public GeneratedBinding withModelButtonCallbackFactory(
            final ISubmitButtonCallbackFactory submitButtonCallbackFactory) {
        Assertions.assertThat(submitButtonCallbackFactory).isNotNull();
        Assertions.assertThat(bindingBuilder)
                .as("%s can only be added before bind() call", ISubmitButtonCallbackFactory.class.getSimpleName())
                .isNull();
        this.submitButtonCallbackFactory = submitButtonCallbackFactory;
        return this;
    }

    public ISubmitButtonCallbackFactory getSubmitButtonCallbackFactory() {
        return submitButtonCallbackFactory;
    }

    public GeneratedBinding withBindingInterceptor(final BindingInterceptor bindingInterceptor) {
        Assertions.assertThat(bindingInterceptor).isNotNull();
        Assertions.assertThat(bindingBuilder)
                .as("%s can only be added before bind() call", BindingInterceptor.class.getSimpleName())
                .isNull();
        bindingInterceptors.add(bindingInterceptor);
        return this;
    }

    public boolean isUseSqlInjectionValidation() {
        return useSqlInjectionValidation;
    }

    public GeneratedBinding withUseSqlInjectionValidation(final boolean useSqlInjectionValidation) {
        this.useSqlInjectionValidation = useSqlInjectionValidation;
        return this;
    }

    public List<BindingInterceptor> getBindingInterceptors() {
        return bindingInterceptors;
    }

    public HtmlContext getContext() {
        return context;
    }

    public IBindingBuilder getBindingBuilder() {
        Assertions.assertThat(bindingBuilder)
                .as("%s is only available during and after bind() call", IBindingBuilder.class.getSimpleName())
                .isNotNull();
        return bindingBuilder;
    }

    public void bind() {
        Assertions.assertThat(this.bindingBuilder).isNull();
        final List<IBindingBuilder> bindingBuilders = new ArrayList<IBindingBuilder>();
        bindingBuilders.addAll(getBindingInterceptors());
        final BindingInterceptor defaultBindingInterceptor = GeneratedBindingDefaults.get()
                .getDefaultBindingInterceptor();
        if (defaultBindingInterceptor != null) {
            bindingBuilders.add(defaultBindingInterceptor);
        }
        bindingBuilders.add(new DefaultBindingBuilder());
        this.bindingBuilder = new InterceptingBindingBuilder(bindingBuilders);
        new HtmlProcessor(context, new BindingVisitor(context)).process();
        GuiService.get().processInitializationFinished(context.getMarkupContainer());
    }

}
