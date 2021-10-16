package de.invesdwin.nowicket.generated.binding.processor.context;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallbackFactory;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.exception.ISubmitButtonExceptionHandler;
import de.invesdwin.nowicket.generated.markup.processor.ModelObjectProcessor;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelObjectContext;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class HtmlContext implements Serializable {

    private static final MetaDataKey<HtmlContext> META_DATA_KEY = new MetaDataKey<HtmlContext>() {
    };

    private final GeneratedBinding parent;
    private transient Resource htmlFile;
    private transient ModelObjectContext modelObjectContext;
    private final HtmlElementRegistry elementRegistry;
    private final MarkupContainer markupContainer;
    private final ComponentRegistry componentRegistry;
    private final String modelNameSuffix;
    private IModel<Object> rootObjectModel;

    public HtmlContext(final MarkupContainer markupContainer, final GeneratedBinding parent) {
        Assertions.assertThat(markupContainer.getDefaultModelObject()).isNotNull();
        this.markupContainer = markupContainer;
        this.parent = parent;
        this.elementRegistry = new HtmlElementRegistry();
        this.componentRegistry = new ComponentRegistry();
        this.modelNameSuffix = ModelClassContext
                .extractModelNameSuffix(markupContainer.getDefaultModelObject().getClass());
        if (markupContainer.getMetaData(META_DATA_KEY) != null) {
            throw newHtmlContextAlreadyPresentException(markupContainer);
        }
        markupContainer.setMetaData(META_DATA_KEY, this);
    }

    private IllegalStateException newHtmlContextAlreadyPresentException(final MarkupContainer markupContainer) {
        return new IllegalStateException(HtmlContext.class.getSimpleName() + " is already present on "
                + MarkupContainer.class.getSimpleName() + " [" + markupContainer.getClass().getSimpleName()
                + "]. Did you call " + GeneratedBinding.class.getSimpleName() + " twice? " //
                + "\nIf it was called by a base class, please consider that this framework can only generate the binding once per "
                + MarkupContainer.class.getSimpleName() + ". "
                + "\nPlease favor composition over inheritance to achieve your desired code reuse scenario using wicket "
                + Panel.class.getSimpleName() + "s. " //
                + "\nJust ensure that in an inheritance hierarchy, the " + GeneratedBinding.class.getSimpleName()
                + " is executed only once. " //
                + "\nWorst case, just bind a generated " + Panel.class.getSimpleName()
                + " manually in your base class, so you can run the " + GeneratedBinding.class.getSimpleName()
                + " in the outer most class without conflicts.");
    }

    public MarkupContainer getMarkupContainer() {
        return markupContainer;
    }

    public Document getHtmlDocument() {
        try {
            final InputStream in = getHtmlFile().getInputStream();
            final String html = IOUtils.toString(in);
            in.close();
            return Jsoup.parse(html);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource getHtmlFile() {
        if (htmlFile == null) {
            //maybe the markup is inherited without any changes. so have to search for the html file
            Class<?> parentClass = getMarkupContainer().getClass();
            while (parentClass != null && (htmlFile == null || !htmlFile.exists())) {
                String relModelPath = parentClass.getName().replace(".", "/");
                if (Strings.isNotBlank(modelNameSuffix)) {
                    relModelPath = Strings.removeEnd(relModelPath, modelNameSuffix);
                }
                final String htmlFilePath = relModelPath + ".html";
                htmlFile = new ClassPathResource("/" + htmlFilePath);
                parentClass = parentClass.getSuperclass();
            }
            Assertions.assertThat(htmlFile.exists())
                    .as("No HTML file found for [%s]", getMarkupContainer().getClass().getName())
                    .isTrue();
        }
        return htmlFile;
    }

    public ModelObjectContext getModelObjectContext() {
        if (modelObjectContext == null) {
            modelObjectContext = new ModelObjectContext(() -> getMarkupContainer().getDefaultModelObject());
            //populate model element registry
            new ModelObjectProcessor(modelObjectContext).process();
        }
        return modelObjectContext;
    }

    public HtmlElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    public ComponentRegistry getComponentRegistry() {
        return componentRegistry;
    }

    public static HtmlContext get(final Component component) {
        Component parent = component;
        while (parent != null) {
            final HtmlContext context = parent.getMetaData(META_DATA_KEY);
            if (context != null) {
                return context;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public static HtmlContext getRoot(final Component component) {
        HtmlContext lastContext = null;
        Component parent = component;
        while (parent != null) {
            final HtmlContext context = parent.getMetaData(META_DATA_KEY);
            if (context != null) {
                lastContext = context;
            }
            parent = parent.getParent();
        }
        return lastContext;
    }

    public static IModel<?> getModel(final Component component) {
        final HtmlContext context = get(component);
        if (context == null) {
            return null;
        } else {
            return context.getMarkupContainer().getDefaultModel();
        }
    }

    public ISubmitButtonExceptionHandler getSubmitButtonExceptionHandler() {
        return parent.getSubmitButtonExceptionHandler();
    }

    public ISubmitButtonCallbackFactory getSubmitButtonCallbackFactory() {
        return parent.getSubmitButtonCallbackFactory();
    }

    public IBindingBuilder getBindingBuilder() {
        return parent.getBindingBuilder();
    }

    public IModel<Object> getRootObjectModel() {
        if (rootObjectModel == null) {
            rootObjectModel = new LoadableDetachableModel<Object>() {
                @Override
                protected Object load() {
                    return getModelObjectContext().getModelObject();
                }
            };
        }
        return rootObjectModel;
    }

}
