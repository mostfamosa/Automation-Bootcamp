package week4.generics;

import week1.Generator;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CallableService {
    //callable for boolean
    static Callable<Boolean> booleanCallable = new Callable<>() {

        @Override
        public Boolean call() throws Exception {
            return ThreadLocalRandom.current().nextBoolean();
        }

    };

    //callable for Double
    static Callable<Double> doubleCallable = new Callable<>() {

        @Override
        public Double call() throws Exception {
            return ThreadLocalRandom.current().nextDouble(10);
        }

    };

    //callable for String
    static Callable<String> stringCallable = new Callable<>() {

        @Override
        public String call() throws Exception {
            return Generator.generateRandomRealNames(1).get(0);
        }

    };
    //callable for Integer
    static Callable<Integer> integerCallable = new Callable<>() {

        @Override
        public Integer call() {
            return ThreadLocalRandom.current().nextInt(20);
        }

    };

}
