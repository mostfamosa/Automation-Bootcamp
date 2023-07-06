package week1.JavaBasics;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        divideBy(arr, 2);

        Map<Integer, User> myUsers = new HashMap<>();
        myUsers = User.getRandomUsers(5);
        User.showUsers(myUsers);
        System.out.println("number of activated users:" + User.activatedUsers(myUsers));


        Stack myStack = new Stack();
        myStack.push(5);
        myStack.push(3);
        myStack.push(4);
        System.out.println("pop: " + myStack.pop());
        System.out.println("pop: " + myStack.pop());
        myStack.push(2);
        System.out.println(myStack.toString());
        System.out.println(myStack.peek());

    }

    public static void divideBy(int[] arr, int num) {
        System.out.println("numbers divided by " + num + ": ");

        ///////////////////FOR//////////////////
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num == 0) {
                System.out.println(arr[i]);
            }
        }

        //////////////////FOR EACH////////////////

//        for (int number:arr) {
//            if(number % num == 0 ) {
//                System.out.println(number);
//            }
//        }

        //////////////////WHILE////////////////
//        int i =0;
//        while(i<arr.length){
//            if(arr[i]%num==0){
//                System.out.println(arr[i]);
//            }
//            i++;
//        }

    }
}
