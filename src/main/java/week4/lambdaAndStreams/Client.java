package week4.lambdaAndStreams;

import week4.lambdaAndStreams.market.Stock;

public class Client {
    public static void main(String[] args) {
        Stock s = new Stock();
        //print stock items
        System.out.println(s);
        System.out.println("**********************************************************\nList of expired items:");

        //Get a list of expired items.
        System.out.println(s.getExpiredItems());
        System.out.println("**********************************************************\nFind an item by name = \'Assaf\':");

        //Get the item with the closest expiry date.
        System.out.println(s.getClosestExpiredItems());
        System.out.println("**********************************************************\nSorted list alphabetically (By: Item Name):");

        //Get a list of items sorted alphabetically.
        System.out.println(s.sortedAlphabetically());
        System.out.println("**********************************************************\nList of item\'s name with a weight > 20:");

        //Get one item by name.
        System.out.println(s.getItemByName("Assaf"));
        System.out.println("**********************************************************\nFind the closest item to expire:");

        //Get a list of names of items above a certain weight - the list should be of names and not of items.
        System.out.println(s.getItemsNameAboveWeight(20));
        System.out.println("**********************************************************\nInventory Count:");

        //Get a hash map of <type, Integer> to sum all the items according to their type.
        System.out.println(s.sumStockItems());
    }
}
