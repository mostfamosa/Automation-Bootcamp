package week1.CreatAndDestroyObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static int generateRandomNumber(int upperBound) {
        return ThreadLocalRandom.current().nextInt(upperBound);
    }

    public static String getRandomName(List<String> names) {
        return names.get(generateRandomNumber(names.size()));
    }
}