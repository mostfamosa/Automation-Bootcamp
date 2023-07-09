package week4.lambdaAndStreams.market;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

class Item {
    public enum type {
        IPHONE,
        ANDROID,
        PC,
        SMARTWATCH,
        TV;

        static type getRandomType() {
            return values()[ThreadLocalRandom.current().nextInt(values().length)];
        }

    }

    private String name;
    private type itemType;
    private Date expirationDate;
    private int weight;

    Item(String name, Date expirationDate, int weight, type itemType) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.weight = weight;
        this.itemType = itemType;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    type getItemType() {
        return itemType;
    }

    void setItemType(type itemType) {
        this.itemType = itemType;
    }

    Date getExpirationDate() {
        return expirationDate;
    }

    void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "Item{" +
                "name='" + name + '\'' +
                ", itemType=" + itemType +
                ", weight=" + weight +
                ", expirationDate=" + ft.format(expirationDate) +
                '}' + '\n';
    }
}
