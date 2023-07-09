package week4.lambdaAndStreams;

import week4.lambdaAndStreams.market.Stock;

public class Client {
    public static void main(String[] args) {
        Stock s = new Stock();
        System.out.println(s);
        System.out.println("**********************************************************\n");
        System.out.println(s.getExpiredItems());
        System.out.println("**********************************************************");
        System.out.println(s.getItemByName("Assaf"));
        System.out.println("**********************************************************");
        System.out.println(s.getClosestExpiredItems());
        System.out.println("**********************************************************");
        System.out.println(s.sortedAlphabetically());
        System.out.println("**********************************************************");
        System.out.println(s.getItemsNameAboveWeight(20));
        System.out.println("**********************************************************");
        System.out.println(s.sumStockItems());
        


    }
}
