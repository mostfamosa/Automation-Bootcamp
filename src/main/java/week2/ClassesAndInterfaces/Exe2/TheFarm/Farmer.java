package week2.ClassesAndInterfaces.Exe2.TheFarm;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
    public enum animalTypes {DOG, CAT, COW, HORSE}

    private Farm farm;
    private List<Animal> animalsOfFarmer = new ArrayList<>();

    public Farmer() {
        this.farm = new Farm();
    }

    /***
     * a function that get an animal type and return a new animal.
     * @param animalType the type of animal you want to create.
     * @return
     */
    public Animal acquire(String animalType) {
        return farm.acquire(animalType);
    }

    /***
     * a function that create a partner animal from the opposite sex.
     * @param animalToMate an animal we need to find him a partner from the opposite sex
     */
    public void mateAnimalInFarm(Animal animalToMate) {
        farm.mateAnimalInFarm(animalToMate);
    }

    /***
     * remove the animal according to the id from the farm.
     * @param id an id for the animal you wish to remove from the farm.
     */
    public void removeFromFarm(int id) {
        farm.removeFromFarm(id);
    }

    /***
     * a function that print all the animals in the farm.
     */
    public void showAnimalsInFarm() {
        System.out.println(farm);
    }

    /***
     * function that makes the animal move according to his id
     * @param id of the animal that going to move
     */
    public void makeItMove(int id) {
        List<Animal> myAnimals = farm.getAnimalsInFarm();
        for (Animal a : myAnimals) {
            if (a.getId() == id)
                a.move();
        }
    }

    /***
     * function that returns an animal for the farmer
     * @param id of the requested animal by the farmer
     */
    public Animal getAnimalById(int id) {
        List<Animal> farmAnimals = new ArrayList<>();
        farmAnimals = farm.getAnimalsInFarm();
        for (Animal a : farmAnimals) {
            if (a.getId() == id) {
                animalsOfFarmer.add(a);
                return a;
            }
        }
        System.out.println("Animal Not Found!");
        return null;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "animalsOfFarmer=" + animalsOfFarmer +
                '}';
    }
}
