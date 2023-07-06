package week2.ClassesAndInterfaces.Exe2.TheFarm;

import java.util.concurrent.ThreadLocalRandom;

class Cat extends Animal implements AnimalInterface {

    public Cat(String gender, int id, int weight) {
        super(gender, id, weight);
    }

    public Cat() {

    }

    @Override
    public Animal mate(Animal partner) {
        if (partner.getGender().equals(genders.MALE.toString())) {
            return new Cat(genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
        } else {
            return new Cat(genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
        }
    }

    @Override
    public void move() {
        System.out.println("Cat " + super.getId() + " is Moving...");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "gender='" + super.getGender() + '\'' +
                ", id=" + super.getId() +
                ", weight=" + super.getWeight() +
                '}' + '\n';
    }
}
