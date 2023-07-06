package week1.CreatAndDestroyObjects;

import java.util.ArrayList;
import static Automation.src.week1.Generator.generateUniqueIndexes;

public class NameGenerator {
    private String[] names;

    public NameGenerator(String[] names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }

    public ArrayList<String> generateRandomRealNames(int limit) {

        ArrayList<Integer> indexOfNames = generateUniqueIndexes(this.getNames().length-1, limit);

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            result.add(this.names[indexOfNames.get(i)]);
        }
        return result;
    }

}
