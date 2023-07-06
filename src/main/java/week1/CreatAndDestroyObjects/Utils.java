package week1.CreatAndDestroyObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    /***
     * a function to write into file.
     * @param filename the file to write into.
     * @param data the data we need to write.
     */
    public static void writeToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt"))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }
}
