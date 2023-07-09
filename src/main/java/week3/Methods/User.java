package week3.Methods;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Coupon> userCoupons;

    public User(String name, ArrayList<Coupon> userCoupons) {
        this.name = name;
        this.userCoupons = userCoupons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Coupon> getUserCoupons() {
        return userCoupons;
    }

    public void setUserCoupons(ArrayList<Coupon> userCoupons) {
        this.userCoupons = userCoupons;
    }

    @Override
    public String toString() {
        String breaker = "-------------------------";
        StringBuilder myString = new StringBuilder(breaker+"\nname = '" + name + '\'' + "\n" +
                "User's Coupons: \n");
        for (Coupon c : userCoupons) {
            myString.append(c).append("\n");
        }
        return myString.append(breaker).append("\n").toString();
    }
}
