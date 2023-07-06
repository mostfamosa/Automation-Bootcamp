package week2.ClassesAndInterfaces.Exe2.TheFarm;

import java.util.concurrent.ThreadLocalRandom;

class Cow extends Animal implements AnimalInterface {
    public Cow(String gender, int id, int weight) {
        super(gender, id, weight);
    }

    public Cow() {

    }

    @Override
    public void move() {
        System.out.println("Cow "+ super.getId() +" is Moving...");
    }

    @Override
    public Animal mate(Animal partner) {
        if (partner.getGender().equals(genders.MALE.toString())){
            return new Cow(genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50),ThreadLocalRandom.current().nextInt(100));
        }
        else{
            return new Cow(genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50),ThreadLocalRandom.current().nextInt(100));
        }
    }

    @Override
    public String toString() {
        return "Cow{" +
                "gender='" + super.getGender() + '\'' +
                ", id=" + super.getId() +
                ", weight=" + super.getWeight() +
                '}'+'\n';
    }
}
