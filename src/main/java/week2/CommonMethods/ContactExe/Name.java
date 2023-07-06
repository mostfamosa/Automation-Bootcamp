package week2.CommonMethods.ContactExe;

import java.util.Objects;

public class Name implements Comparable<Name>, Cloneable {



    enum Prefix {Mr, Ms}

    private String firstName;
    private String lastName;
    private Prefix prefix;


    public Name(String firstName, String lastName, Prefix prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.prefix = prefix;
    }


    public String getFirstNAme() {
        return firstName;
    }

    public void setFirstNAme(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) && lastName.equals(name.lastName) && prefix == name.prefix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, prefix);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", prefix=" + prefix +
                '}';
    }


    @Override
    public int compareTo(Name name) {
        int result = this.firstName.compareTo(name.firstName);
        /* check if the first names are equal*/
        if (result == 0) {
            result = this.lastName.compareTo(name.lastName);
            /* check if the last names are equal*/
            if (result == 0) {
                return this.prefix.compareTo(name.prefix);
            }
        }
        return result;
    }

    @Override
    public Name clone() {
        try {
            Name clone = (Name) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /*copyOf is less complexity and more flexibility.*/
    public static Name copyOf(Name name){
        return new Name(name.firstName, name.lastName, name.prefix);
    }


}
