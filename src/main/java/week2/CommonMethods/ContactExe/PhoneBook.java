package week2.CommonMethods.ContactExe;

import java.util.ArrayList;

public class PhoneBook {
    private ArrayList<Contact> contacts;
    private String name;

    PhoneBook() {

    }

    public PhoneBook(ArrayList<Contact> contacts, String name) {
        this.contacts = contacts;
        this.name = name;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n\nPhoneBook{" +
                ", name='" + name + '\'' + "\n" +
                "contacts=\n" + contacts +
                '}';
    }
}
