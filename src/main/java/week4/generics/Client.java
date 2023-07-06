package Automation.src.week4.generics;
import Automation.src.week1.Generator;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Client {
    public static void main(String[] args) throws Exception {
        Callable<Boolean> booleanCallable = new Callable<>() {

            @Override
            public Boolean call() throws Exception {
                return true;
            }

        };

        Callable<Double> doubleCallable = new Callable<>() {

            @Override
            public Double call() throws Exception {
                return ThreadLocalRandom.current().nextDouble(10);
            }

        };

        Callable<String> stringCallable = new Callable<>() {

            @Override
            public String call() throws Exception {
                return Generator.generateRandomRealNames(1).get(0);
            }

        };

        Callable<Integer> integerCallable = new Callable<>() {

            @Override
            public Integer call() {
                return ThreadLocalRandom.current().nextInt(20);
            }

        };

        System.out.println(retry(doubleCallable, 5.1));
        System.out.println(retry(stringCallable, "Mostafa"));
        System.out.println(retry(booleanCallable, true));
        System.out.println(retry(integerCallable, 7));

    }

    public static <T> T retry(Callable<T> action, T expectedResult) throws Exception {
        Integer numberOfRetry = 0;

        while (true) {
            T a = action.call();
            if (a.equals(expectedResult)) {
                System.out.println("Good Job! you found " + expectedResult);
                return a;
            } else if (numberOfRetry == 20) {
                System.out.println("Maximum tries reached! : " + numberOfRetry);
                return a;

            } else numberOfRetry++;

            Thread.sleep(100);
        }

    }
}
