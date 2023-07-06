package week4.reflections;

import static week4.reflections.Utils.createInstance;

public class Client {
    public static void main(String[] args) {
        Teacher tzahi = new Teacher("Tzahi", "assaf");
        Student mostafa = new Student("Mostafa");

        System.out.println(createInstance(tzahi));
        System.out.println(createInstance(mostafa));

    }
}
