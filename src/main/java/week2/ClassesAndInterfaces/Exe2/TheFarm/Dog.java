package week2.ClassesAndInterfaces.Exe2.TheFarm;

import java.util.concurrent.ThreadLocalRandom;

class Dog extends Animal implements AnimalInterface {

    public Dog(String gender, int id, int weight) {
        super(gender, id, weight);
    }

    public Dog() {
    }

    @Override
    public void move() {
        System.out.println("Dog " + super.getId() + " is Moving...");
    }

    @Override
    public Animal mate(Animal partner) {

        if (partner.getGender().equals(genders.MALE.toString())) {
            return new Dog(genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
        } else {
            return new Dog(genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "gender='" + super.getGender() + '\'' +
                ", id=" + super.getId() +
                ", weight=" + super.getWeight() +
                '}' + '\n';
    }
}
