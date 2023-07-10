package week4.lambdaAndStreams;

import week4.lambdaAndStreams.market.Stock;

public class Client {
    public static void main(String[] args) {
        Stock s = new Stock();
        System.out.println(s);
        System.out.println("**********************************************************\nList of expired items:");
        System.out.println(s.getExpiredItems());
        System.out.println("**********************************************************\nFind an item by name = \'Assaf\':");
        System.out.println(s.getItemByName("Assaf"));
        System.out.println("**********************************************************\nFind the closest item to expire:");
        System.out.println(s.getClosestExpiredItems());
        System.out.println("**********************************************************\nSorted list alphabetically (By: Item Name):");
        System.out.println(s.sortedAlphabetically());
        System.out.println("**********************************************************\nList of item\'s name with a weight > 20:");
        System.out.println(s.getItemsNameAboveWeight(20));
        System.out.println("**********************************************************\nInventory Count:");
        System.out.println(s.sumStockItems());
    }
}
