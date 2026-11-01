package gof.structural.composite;

import common.Console;

public class CompositeDemo {

    private static final int CARRY_LIMIT = 15;
    private static final int REAL_WEIGHT = 19;
    private static final int REAL_VALUE = 1950;

    public static void main(String[] args) {
        Console.header("GoF / Composite -- an item and a bag answer the same questions");

        Bag pouch = new Bag("Pouch", 1)
                .add(new Item("Ruby", 1, 500))
                .add(new Item("Ruby", 1, 500))
                .add(new Item("Gold Bar", 5, 800));
        Bag backpack = new Bag("Backpack", 2)
                .add(new Item("Potion", 1, 50))
                .add(pouch);

        Console.step("The hero carries a Sword and a Backpack with a Potion and a Pouch of treasure");
        Bag inventory = new Bag("Inventory", 0)
                .add(new Item("Sword", 8, 100))
                .add(backpack);

        int weight = inventory.weight();
        int value = inventory.value();

        if (weight == REAL_WEIGHT) {
            Console.ok("weight: " + weight + (weight > CARRY_LIMIT ? " -- over the limit, the hero is slowed" : ""));
        } else {
            Console.fail("weight: " + weight + " instead of " + REAL_WEIGHT + "");
        }
        if (value == REAL_VALUE) {
            Console.ok("the merchant offers " + value + " gold for everything");
        } else {
            Console.fail("the merchant offers " + value + " gold instead of " + REAL_VALUE
                    + "");
        }

        Console.note("Bag asks its children and does not care what they are, "
                + "so any depth works, and there is no instanceof left.");
    }
}
