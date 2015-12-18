package de.invesdwin.nowicket.generated.binding.processor.context;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.model.IModel;
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
import de.invesdwin.norva.beanpath.impl.object.IRootObjectReference;
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

    public HtmlContext(final MarkupContainer markupContainer, final GeneratedBinding parent) {
        Assertions.assertThat(markupContainer.getDefaultModelObject()).isNotNull();
        this.markupContainer = markupContainer;
        this.parent = parent;
        this.elementRegistry = new HtmlElementRegistry();
        this.componentRegistry = new ComponentRegistry();
        this.modelNameSuffix = ModelClassContext.extractModelNameSuffix(markupContainer.getDefaultModelObject()
                .getClass());
        markupContainer.setMetaData(META_DATA_KEY, this);
    }

    public MarkupContainer getMarkupContainer() {
        return markupContainer;
    }

    public Document getHtmlDocument() {
        try {
            final String html = IOUtils.toString(getHtmlFile().getInputStream());
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
            modelObjectContext = new ModelObjectContext(new IRootObjectReference() {
                @Override
                public Object getRootObject() {
                    return getMarkupContainer().getDefaultModelObject();
                }
            });
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

}
