package week3.Exceptions;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadConfigFile {
    private static final String configFilePath = "src/main/resources/dbInfo.properties";
    private Map<String, String> myData = new HashMap<>();

    public ReadConfigFile() {
    }

    /***
     * function that returns the data in the Map .
     * @return the data in the Map 'myData'.
     */
    public Map<String, String> getMyData() {
        if (myData.isEmpty())
            throw new RuntimeException("Data Not initialized!");
        return myData;
    }

    /***
     * this function will try to read from the configuration file and save it in a map if it found,
     * and if the file is NOT found it will create a default file and save it in map.
     * @return the updated map that contains the data from the config file.
     */
    public Map<String, String> loadDataFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by the first occurrence of '='
                int equalsIndex = line.indexOf('=');
                if (equalsIndex != -1) {
                    String key = line.substring(0, equalsIndex).trim();
                    String value = line.substring(equalsIndex + 1).trim();
                    myData.put(key, value);
                }
            }
            return myData;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!\nThe service will provide you with new file :)");

            Map<String, String> configurationMap = new HashMap<>();
            configurationMap.put("DB_PASSWORD", "Mostafa5748974");
            configurationMap.put("DB_USER", "Mostafa Mossa");
            configurationMap.put("EMAIL", "Mostafa@gmail.com");

            //write to file the default data
            writeConfigurationFile(configurationMap, configFilePath);
            myData = configurationMap;


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myData;
    }

    /***
     * function that returns the data of the key in param.
     * @param myKey the key to find the data according to.
     * @return the data of the kay.
     */
    public String getDataByKey(String myKey) {
        if (myData.get(myKey).isEmpty())
            throw new NullPointerException("Key not found!");
        return myData.get(myKey);
    }

    /***
     * write the map data to configuration file
     * @param configurationMap map of the data
     * @param filePath configuration file path and name
     */
    public static void writeConfigurationFile(Map<String, String> configurationMap, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, String> entry : configurationMap.entrySet()) {
                String line = entry.getKey() + "=" + entry.getValue() + System.lineSeparator();
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
