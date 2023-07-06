package week4.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static week1.Generator.generateRandomRealNames;

public class Utils {

    public static String generateRandomRealName() {
        return generateRandomRealNames(1).get(0);
    }

    public static Optional<Object> createInstance(Object o) {

        try {
            Class<?> aClass = o.getClass();
            Constructor[] constructors = aClass.getDeclaredConstructors();

            for (Constructor c : constructors) {
                if (c.getParameterCount() == 1) {
                    if (c.getParameterTypes()[0].equals(String.class)) {
                        Object newObject = constructors[0].newInstance(generateRandomRealName());
                        System.out.println("\nReturned New Instance Successfully!");
                        return Optional.of(newObject);
                    }
                }

            }
            System.out.println("There is no such constructor with one String parameter!");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
