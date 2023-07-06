package week1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generator {
    //unique list of number
    static ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();

    public static String generateRandomName(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static int generateRandomId() {
        Random rnd = new Random();
        return rnd.nextInt(101);
    }

    public static boolean generateRandomBoolean() {
        Random rnd = new Random();
        return rnd.nextBoolean();
    }


    public static ArrayList<String> generateRandomRealNames(int limit) {

        //unique String
        String[] realNames={"Shai","Mostafa","khader","Assaf","Sagi","Lion","Lior","Jamal","Moshe","David","Michel","Joey","Barney","Ted","James","Samer","Zeev"};

        ArrayList<Integer> indexOfNames = generateRandomUniqueNumbers(realNames.length-1, limit);

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            result.add(realNames[indexOfNames.get(i)]);
        }
        return result;
    }

    public static ArrayList<Integer> generateRandomUniqueNumbers(int limit,int howMany) {
        for (int i = 1; i < limit; i++)
            listOfNumbers.add(i);
        Collections.shuffle(listOfNumbers);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <howMany ; i++) {
            result.add(listOfNumbers.get(i));
        }
        listOfNumbers.clear();
        return result;
    }

    public static ArrayList<Integer> generateUniqueIndexes(int limit,int howMany) {
        for (int i = 1; i < limit; i++)
            listOfNumbers.add(i);
        Collections.shuffle(listOfNumbers);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <howMany ; i++) {
            result.add(listOfNumbers.get(i));
        }
        listOfNumbers.clear();
        return result;
    }

    public static int generateRandomGrade() {
        Random rnd = new Random();
        return rnd.nextInt(101);
    }

}
