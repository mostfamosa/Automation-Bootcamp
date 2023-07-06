package week3.Enums;

public class Client {
    public enum Birds {
        FLAMINGO("Phownicopterus ruber") {
            @Override
            public void sing() {
                System.out.println("goose-like honks");
            }
        },
        BALD_EAGLE("Mostafa") {
            @Override
            public void sing() {
                System.out.println("kleek kik ik ik ik");
            }
        },
        CARDINAL("Cardinals cardinals") {
            @Override
            public void sing() {
                System.out.println("cheer-cheer-cheer");
            }
        },
        TURKEY("Meleagris gallopavo") {
            @Override
            public void sing() {
                System.out.println("gobble-gobble-gobble");
            }
        };

        Birds(String name) {
            this.name = name;
        }

        public final String name;

        public abstract void sing();

    }


    public static void main(String[] args) {

        System.out.println(Birds.BALD_EAGLE);
        System.out.println(Birds.BALD_EAGLE.name);
        Birds.BALD_EAGLE.sing();

        System.out.println("\n");

        System.out.println(Birds.FLAMINGO);
        System.out.println(Birds.FLAMINGO.name);
        Birds.FLAMINGO.sing();

        System.out.println("\n");

        System.out.println(Birds.CARDINAL);
        System.out.println(Birds.CARDINAL.name);
        Birds.CARDINAL.sing();

        System.out.println("\n");

        System.out.println(Birds.TURKEY);
        System.out.println(Birds.TURKEY.name);
        Birds.TURKEY.sing();

    }
}
