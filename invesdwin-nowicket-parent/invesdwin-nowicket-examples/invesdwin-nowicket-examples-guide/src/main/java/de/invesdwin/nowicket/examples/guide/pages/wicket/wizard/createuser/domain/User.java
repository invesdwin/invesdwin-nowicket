package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.domain;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

/**
 * We pretend this type comes from a different module where no annotations from invesdwin-nowicket are allowed.
 * 
 * @author subes
 *
 */
@NotThreadSafe
public class User extends AValueObject {

    private String username;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String department;
    private RoleSet roleSet;

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public RoleSet getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(final RoleSet roleSet) {
        this.roleSet = roleSet;
    }

}
