package week3.Exceptions;

import java.util.Map;

public class Client {
    public static void main(String[] args) {
        ReadConfigFile myService = new ReadConfigFile();
        Map<String, String> myData;

        myData = myService.loadDataFromFile();
        System.out.println(myData);
    }
}


