package week2.CommonMethods.ContactExe;

import java.util.Objects;

public class Contact implements Comparable<Contact> , Cloneable{
    private Name name;
    private PhoneNumber phoneNumber;

    public Contact(Name name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && phoneNumber.equals(contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public int compareTo(Contact contact) {
        int result = this.name.compareTo(contact.name);
        if (result == 0) {
            return this.phoneNumber.compareTo(contact.phoneNumber);
        }
        return result;
    }


    @Override
    public Contact clone() {
        try {
            Contact cloneContract = (Contact) super.clone();
            cloneContract.name = this.name.clone();
            cloneContract.phoneNumber =this.phoneNumber.clone();
            return cloneContract;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }



}
