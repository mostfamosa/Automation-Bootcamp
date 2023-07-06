package week2.CommonMethods.ContactExe;

public class Client {
    public static void main(String[] args) {

        Name name1 = new Name("Mostafa", "Mossa", Name.Prefix.Mr);
        Name name2 = new Name("Mostafa", "Mossaaaaaaa", Name.Prefix.Mr);

        PhoneNumber phoneNumber1 = new PhoneNumber("+972", "520868619");
        PhoneNumber phoneNumber2 = new PhoneNumber("+972", "520868619");

        Contact contact1 = new Contact(name1, phoneNumber1);
        Contact contact2 = new Contact(name2, phoneNumber2);

        System.out.println(name1.compareTo(name1.clone()));

        System.out.println(name1);
        System.out.println(name2);

        System.out.println("************************");

        System.out.println(contact1);
        System.out.println(contact1.clone());

        System.out.println("************************");

        System.out.println(contact2);
        System.out.println(contact2.hashCode());
    }
}
