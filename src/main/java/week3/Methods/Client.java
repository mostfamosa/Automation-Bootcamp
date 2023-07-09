package week3.Methods;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        Store store = new Store();
        ArrayList<Coupon> myCoupons = new ArrayList<>();
        User user1 = new User("Tzahi", myCoupons);

        store.assignCouponToUser(user1);
        store.assignCouponToUser(user1);
        store.assignCouponToUser(user1);
        store.assignCouponToUser(user1);
        store.assignCouponToUser(user1);
        store.assignCouponToUser(user1);
        store.assignCouponToUser(user1);

        System.out.println(user1);

        store.useCouponById(user1,user1.getUserCoupons().get(1).getId());

        System.out.println(user1);

        store.useHeighestCoupon(user1);
        System.out.println(user1);

        store.useRandomCoupon(user1);
        System.out.println(user1);

        store.useClosestCoupon(user1);
        System.out.println(user1);
    }
}
