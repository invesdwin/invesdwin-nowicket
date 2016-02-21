package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.domain;

import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

/**
 * We pretend this type comes from a different module where no annotations from invesdwin-nowicket are allowed.
 * 
 * @author subes
 *
 */
@NotThreadSafe
public class RoleSet extends AValueObject {

    private String name;

    private Set<String> roles;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }

}
