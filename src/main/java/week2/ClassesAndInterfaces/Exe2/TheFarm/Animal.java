package week2.ClassesAndInterfaces.Exe2.TheFarm;

import java.util.Objects;

abstract class Animal implements AnimalInterface {
    enum genders {MALE, FEMALE}
    private String gender;
    private int id;
    private int weight;

    public Animal(String gender, int id, int weight) {
        this.gender = gender;
        this.id = id;
        this.weight = weight;
    }

    public Animal() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public Animal mate(Animal partner) {
        return null;
    }

    @Override
    public void move() {
        System.out.println("Animal is Moving...");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && weight == animal.weight && gender.equals(animal.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, id, weight);
    }


    @Override
    public String toString() {
        return "Animal{" +
                "gender='" + gender + '\'' +
                ", id=" + id +
                ", weight=" + weight +
                '}'+'\n';
    }
}
