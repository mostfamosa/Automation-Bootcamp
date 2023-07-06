package week1.JavaBasics;

import java.util.Arrays;

public class Stack {
    private int Sp = 0;
    private int[] arr = new int[1];


    public Stack() {
    }

    public int peek() {
        return arr[Sp-1];
    }


    public boolean push(int newNum) {
        if (this.Sp == 0) {
            arr[Sp] = newNum;
            Sp++;
            return true;
        } else if (this.arr.length < Sp-1) {
            arr[(Sp + 1)] = newNum;
            Sp++;
            return true;
        } else {
            int[] newArr = new int[Sp * 2];
            for (int i = 0; i < Sp; i++)
                newArr[i] = arr[i];
            newArr[Sp] = newNum;
            this.arr=newArr;
            Sp++;
            return true;
        }
    }

    public int pop(){
        int deletedValue;
        if(this.Sp==0){
            System.out.println("The Stack Is Already Empty!");
            return -1;
        }
        else {
            Sp--;
            deletedValue=this.arr[Sp];
            this.arr[Sp]=0;
            return deletedValue;
        }


    }




    @Override
    public String toString() {
        return "Stack{" +
                "Sp=" + Sp +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }

    public static void main(String[] args) {

    }

}
