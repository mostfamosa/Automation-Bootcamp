package week1.CreatAndDestroyObjects;

import java.util.List;
import java.util.Random;

public class NameGenerator {
    private List<String> nameDictionary;

    public NameGenerator(List<String> nameDictionary) {
        this.nameDictionary = nameDictionary;
    }

    public String generateRandomName() {
        return Utils.getRandomName(this.nameDictionary);
    }

}
