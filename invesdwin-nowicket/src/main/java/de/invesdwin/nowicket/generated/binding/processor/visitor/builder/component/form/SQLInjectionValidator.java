package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import de.invesdwin.nowicket.util.Components;

/**
 * 
 * @see <a href="http://www.mvvm.ro/2011/03/sanitize-strings-against-sql-injection.html">Source</a>
 */
@NotThreadSafe
public class SQLInjectionValidator implements IValidator<Object> {

    private static final List<Pattern> PATTERNS;

    static {
        PATTERNS = new ArrayList<Pattern>();
        PATTERNS.add(Pattern.compile(".*-{2,}.*"));
        PATTERNS.add(Pattern.compile(".*([*]/|/[*]).*"));
        PATTERNS.add(Pattern.compile(
                ".*;(\\s)*(exec|execute|select|insert|update|delete|create|alter|drop|rename|truncate|backup|restore)\\s.*",
                Pattern.CASE_INSENSITIVE));
    }

    private final Component component;
    private final FormComponent<?> formComponent;

    public SQLInjectionValidator(final Component component) {
        this.component = component;
        this.formComponent = Components.asFormComponent(component);
    }

    @Override
    public void validate(final IValidatable<Object> validatable) {
        // Check value against pattern
        final Object value = Components.getValidatableValue(component, validatable);
        if (value != null) {
            for (final Pattern pattern : PATTERNS) {
                if (pattern.matcher(value.toString()).matches()) {
                    final ValidationError error = new ValidationError();
                    error.setVariable("label",
                            ModelUtilityValidator.surroundTitle(formComponent.getLabel().getObject()));
                    error.addKey(getClass().getSimpleName() + "." + "error");
                    validatable.error(error);
                }
            }
        }
    }
}