package Automation.src.week3.Methods;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Store {
    /***
     * function that assign a new coupon and add it to the user's list
     * @param user the user we want to assign coupon to.
     */
    public void assignCouponToUser(User user) {
        int limitDays = 7;
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();

        //random Date (from today to limitDays) the coupon
        Date date = new Date((now - aDay * 5) + aDay * ThreadLocalRandom.current().nextInt(limitDays));
        //random id to the coupon
        int id = ThreadLocalRandom.current().nextInt(999);
        //random value to the coupon
        int value = 50 + ThreadLocalRandom.current().nextInt(200);

        Coupon coupon = new Coupon(id, date, value);
        //add coupon to user's list
        user.getUserCoupons().add(coupon);
    }

    /***
     * function that use's a coupon of the user by the coupon's id.
     * @param user1 the user that is trying to use the coupon.
     * @param id the id of the coupon we need to use in the user coupon's list.
     * @return the coupon we are using.
     */
    public Coupon useCouponById(User user1, int id) {
        Coupon usedCoupon = new Coupon();
        if (user1.getUserCoupons().isEmpty())
            throw new NullPointerException("User has no Coupons!");

        ArrayList<Coupon> coupons = user1.getUserCoupons();

        for (Coupon c : coupons) {
            if (c.getId() == id) {
                //check validation
                if (isValid(c)) {
                    System.out.println("We Are Using The Coupon:\n" + c);

                } else {
                    System.out.println("This Coupon Is Out Of Date! :\n" + c);
                }
                usedCoupon = c;
                coupons.remove(c);
                return usedCoupon;

            }
        }
        System.out.println("The Coupon With Id: " + id + " is NOT FOUND!");
        return usedCoupon;
    }

    /***
     * function that use's the highest coupon of the user's list.
     * @param user1 the user that is trying to use the coupon.
     * @return the coupon we are using.
     */
    public Coupon useHeighestCoupon(User user1) {
        if (user1.getUserCoupons().isEmpty())
            throw new NullPointerException("User has no Coupons!");

        ArrayList<Coupon> coupons = user1.getUserCoupons();
        int max = 0;
        Coupon maxCoupon = coupons.get(0);
        for (Coupon c : coupons) {
            if (c.getValue() > max) {
                max = c.getValue();
                maxCoupon = c;
            }
        }
        //check validation
        if (isValid(maxCoupon)) {
            System.out.println("We Are Using The Highest Value Coupon:\n" + maxCoupon);
            coupons.remove(maxCoupon);

        } else {
            System.out.println("The Highest Value Coupon Is Out Of Date! :\n" + maxCoupon);
            coupons.remove(maxCoupon);
        }
       return maxCoupon;
    }
    /***
     * function that use's a random coupon of the user's list.
     * @param user1 the user that is trying to use the coupon.
     * @return the coupon we are using.
     */
    public Coupon useRandomCoupon(User user1) {
        if (user1.getUserCoupons().isEmpty())
            throw new NullPointerException("User has no Coupons!");

        ArrayList<Coupon> coupons = user1.getUserCoupons();

        Coupon c = coupons.get(ThreadLocalRandom.current().nextInt(coupons.size()));
        if (isValid(c)) {
            System.out.println("We Are Using Random Coupon:\n" + c);
            coupons.remove(c);
        } else {
            System.out.println("The Random Coupon Is Out Of Date!:\n" + c);
            coupons.remove(c);
        }
        return c;
    }
    /***
     * function that use's the close coupon (by date) of the user's list.
     * @param user1 the user that is trying to use the coupon.
     * @return the coupon we are using.
     */
    public Coupon useClosestCoupon(User user1) {
        if (user1.getUserCoupons().isEmpty())
            throw new NullPointerException("User has no Coupons!");

        long now = new Date().getTime();//today
        ArrayList<Coupon> coupons = user1.getUserCoupons();
        long minDifference = Long.MAX_VALUE;
        Coupon closeCoupon = coupons.get(0);
        for (Coupon c : coupons) {
            long difference = Math.abs(c.getExpiryDate().getTime() - now);
            if (difference < minDifference) {
                minDifference = difference;
                closeCoupon = c;
            }
        }

        if (isValid(closeCoupon)) {
            System.out.println("We Are Using The Closest Coupon:\n" + closeCoupon);
            coupons.remove(closeCoupon);
        } else {
            System.out.println("The Closest Coupon Is Out Of Date!:\n" + closeCoupon);
            coupons.remove(closeCoupon);
        }
        return closeCoupon;
    }

    public Boolean isValid(Coupon coupon) {
        long now = new Date().getTime();
        Date todaysDate = new Date(now);

        return !coupon.getExpiryDate().before(todaysDate);
    }


}
