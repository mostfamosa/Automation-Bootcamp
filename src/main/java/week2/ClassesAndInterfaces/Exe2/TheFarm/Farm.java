package week2.ClassesAndInterfaces.Exe2.TheFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Farm {
    private final List<Animal> animalsInFarm = new ArrayList<>();

    Farm() {

        /** Adding a few random animals at the beginning */
        animalsInFarm.add(new Dog(Animal.genders.FEMALE.toString(), 1, ThreadLocalRandom.current().nextInt(100)));
        animalsInFarm.add(new Cat(Animal.genders.MALE.toString(), 2, ThreadLocalRandom.current().nextInt(100)));
        animalsInFarm.add(new Cat(Animal.genders.FEMALE.toString(), 3, ThreadLocalRandom.current().nextInt(100)));
        animalsInFarm.add(new Cow(Animal.genders.FEMALE.toString(), 4, ThreadLocalRandom.current().nextInt(100)));
        animalsInFarm.add(new Horse(Animal.genders.MALE.toString(), 5, ThreadLocalRandom.current().nextInt(100)));
    }

    /***
     * creating a random animal according to the requested animal type.
     * @param animalType of the requested animal to create
     * @return an animal with the requested type
     */
    Animal acquire(String animalType) {
        switch (animalType) {
            case "DOG":
                /** checking the possibility of mating existing animals of the requested type*/
                for (Animal animal : animalsInFarm) {
                    if (animal.getGender().equals(Animal.genders.MALE.toString())) {
                        Dog newDog = new Dog(Animal.genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                        animalsInFarm.add(newDog);
                        return newDog;
                    }
                }
                Dog newDog = new Dog(Animal.genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                animalsInFarm.add(newDog);
                return newDog;

            case "CAT":
                /** checking the possibility of mating existing animals of the requested type*/
                for (Animal animal : animalsInFarm) {
                    if (animal.getGender().equals(Animal.genders.MALE.toString())) {
                        Cat newCat = new Cat(Animal.genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                        animalsInFarm.add(newCat);
                        return newCat;
                    }
                }
                Cat newCat = new Cat(Animal.genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                animalsInFarm.add(newCat);
                return newCat;

            case "COW":
                /** checking the possibility of mating existing animals of the requested type*/
                for (Animal animal : animalsInFarm) {
                    if (animal.getGender().equals(Animal.genders.MALE.toString())) {
                        Cow newCow = new Cow(Animal.genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                        animalsInFarm.add(newCow);
                        return newCow;
                    }
                }
                Cow newCow = new Cow(Animal.genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                animalsInFarm.add(newCow);
                return newCow;


            case "HORSE":
                /** checking the possibility of mating existing animals of the requested type*/
                for (Animal animal : animalsInFarm) {
                    if (animal.getGender().equals(Animal.genders.MALE.toString())) {
                        Horse newHorse = new Horse(Animal.genders.FEMALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                        animalsInFarm.add(newHorse);
                        return newHorse;
                    }
                }
                Horse newHorse = new Horse(Animal.genders.MALE.toString(), ThreadLocalRandom.current().nextInt(50), ThreadLocalRandom.current().nextInt(100));
                animalsInFarm.add(newHorse);
                return newHorse;
            default:
                throw new IllegalArgumentException(String.format("Animal %s Is Not Supported!\n", animalType));
        }
    }


    void mateAnimalInFarm(Animal animalToMate) {

        switch (animalToMate.getClass().getSimpleName()) {
            case "Dog":
                Dog dog = new Dog();
                animalsInFarm.add(dog.mate(animalToMate));
                break;
            case "Cat":
                Cat cat = new Cat();
                animalsInFarm.add(cat.mate(animalToMate));
                break;
            case "Cow":
                Cow cow = new Cow();
                animalsInFarm.add(cow.mate(animalToMate));
                break;
            case "Horse":
                Horse horse = new Horse();
                animalsInFarm.add(horse.mate(animalToMate));
                break;
            default:
                throw new IllegalArgumentException("Animal " + animalToMate.getClass().getSimpleName() + " Is Not Supported!\n");
        }
    }

    /***
     * function that removes animal from the farm according to the id
     * @param id of the animal that we want to remove it
     */
    void removeFromFarm(int id) {
        boolean isRemoved = false;
        for (Animal animal : this.animalsInFarm) {
            if (animal.getId() == id) {
                isRemoved = true;
                this.animalsInFarm.remove(animal);
                System.out.println("The Animal with id = " + id + " is removed from the farm!");
                break;
            }
        }
        if (!isRemoved)
            System.out.println("The Animal with id = " + id + " is NOT FOUND!");
    }

    List<Animal> getAnimalsInFarm() {
        return animalsInFarm;
    }

    @Override
    public String toString() {
        StringBuilder res = null;
        res = new StringBuilder("\nAnimals In The Farm:\n*******************\n");
        res.append(animalsInFarm.get(0));
        for (int i = 1; i < animalsInFarm.size(); i++) {
            res.append(animalsInFarm.get(i));
        }
        res.append("*******************\n");
        return res.toString();
    }
}
