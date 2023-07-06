package week2.ClassesAndInterfaces.Exe2;

import week2.ClassesAndInterfaces.Exe2.TheFarm.Farmer;

public class Client {
    public static void main(String[] args) {

        Farmer farmer = new Farmer();

        farmer.showAnimalsInFarm();
        farmer.acquire(Farmer.animalTypes.CAT.toString());

        farmer.showAnimalsInFarm();

        farmer.makeItMove(2);
        farmer.makeItMove(1);

        farmer.mateAnimalInFarm(farmer.getAnimalById(1));

        farmer.showAnimalsInFarm();

        farmer.removeFromFarm(31);
        farmer.showAnimalsInFarm();

    }
}
