package week4.generics;

import week1.Generator;

import java.util.concurrent.Callable;

public class Client {
    public static void main(String[] args) throws Exception {

        retry(CallableService.doubleCallable, 5.1,20);
        retry(CallableService.stringCallable, "Mostafa",13);
        retry(CallableService.booleanCallable, true,3);
        retry(CallableService.integerCallable, 7,20);

    }

    public static <T> T retry(Callable<T> action, T expectedResult ,int maxRetryTimes) {
        int numberOfRetry = 0;

        while (true) {
            try {
                T a = action.call();
                if (a.equals(expectedResult)) {
                    System.out.println("Good Job! you found " + expectedResult);
                    System.out.println("******************************");
                    return a;
                } else if (numberOfRetry == maxRetryTimes) {
                    System.out.println("Maximum tries reached! : " + numberOfRetry);
                    System.out.println("Expected result was [" + expectedResult + "]");
                    System.out.println("You founded: {" + a + "}");
                    System.out.println("==============================");
                    return a;

                } else
                    numberOfRetry++;

                Thread.sleep(100);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
