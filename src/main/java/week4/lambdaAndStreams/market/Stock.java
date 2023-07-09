package week4.lambdaAndStreams.market;

import week1.Generator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Stock {
    private List<Item> itemsList = new ArrayList<>();

    public Stock() {
        for (int i = 0; i < 10; i++) {
            generateRandomItem();
        }
        itemsList = itemsList.stream().sorted(Comparator.comparing(Item::getExpirationDate)).toList();
    }

    //Generate item and add to list.
    private void generateRandomItem() {
        String itemName = Generator.generateRandomRealNames(1).get(0);
        Date randomDate = Generator.generateRandomeDate(5, 9);
        int randomWeight = ThreadLocalRandom.current().nextInt(100);
        itemsList.add(new Item(itemName, randomDate, randomWeight, Item.type.getRandomType()));
    }

    //Get a list of expired items.
    public List<Item> getExpiredItems() {
        Date now = new Date(new Date().getTime());
        return itemsList.stream().filter(item -> item.getExpirationDate().before(now)).toList();
    }

    //Get the item with the closest expiry date.
    public Item getClosestExpiredItems() {
        Date now = new Date(new Date().getTime());
        return itemsList.stream()
                .filter(item -> item.getExpirationDate().after(now))
                .min(Comparator.comparing(Item::getExpirationDate))
                .orElse(null);
    }

    //Get a list of items sorted alphabetically.
    public List<Item> sortedAlphabetically() {
        return itemsList.stream().sorted(Comparator.comparing(Item::getName)).toList();
    }

    //Get one item by name.
    public Item getItemByName(String itemName) {
        return itemsList.stream()
                .filter(item -> itemName.equals(item.getName()))
                .findFirst().orElse(null);
    }

    //Get a list of names of items above a certain weight.
    public List<String> getItemsNameAboveWeight(int weight) {
        return itemsList.stream()
                .filter(Item -> Item.getWeight() > weight)
                .map(Item::getName).toList();
    }

    //Get a hash map of <type, Integer> to sum all the items according to their type.
    public Map<Item.type, Integer> sumStockItems() {
        Map<Item.type, Integer> res = new HashMap<>();
        itemsList.forEach(entry -> res.put(entry.getItemType(), (int) itemsList.stream().filter(item -> item.getItemType().equals(entry.getItemType())).count()));
        return res;
    }

    @Override
    public String toString() {
        return "itemsList=\n" + itemsList;

    }
}
