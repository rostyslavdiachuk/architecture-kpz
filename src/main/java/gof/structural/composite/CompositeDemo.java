package gof.structural.composite;

import common.Console;

public class CompositeDemo {

    private static final int CARRY_LIMIT = 15;
    private static final int REAL_WEIGHT = 19;
    private static final int REAL_VALUE = 1950;

    public static void main(String[] args) {
        Console.header("GoF / Composite -- the inventory checks types and looks one level deep");

        Bag pouch = new Bag("Pouch", 1)
                .add(new Item("Ruby", 1, 500))
                .add(new Item("Ruby", 1, 500))
                .add(new Item("Gold Bar", 5, 800));
        Bag backpack = new Bag("Backpack", 2)
                .add(new Item("Potion", 1, 50))
                .add(pouch);

        Console.step("The hero carries a Sword and a Backpack with a Potion and a Pouch of treasure");
        Inventory inventory = new Inventory()
                .add(new Item("Sword", 8, 100))
                .add(backpack);

        int weight = inventory.totalWeight();
        int value = inventory.totalValue();

        if (weight == REAL_WEIGHT) {
            Console.ok("weight: " + weight + (weight > CARRY_LIMIT ? " -- over the limit, the hero is slowed" : ""));
        } else {
            Console.fail("weight: " + weight + " instead of " + REAL_WEIGHT + " -- the hero is over the limit of "
                    + CARRY_LIMIT + " but runs at full speed");
        }
        if (value == REAL_VALUE) {
            Console.ok("the merchant offers " + value + " gold for everything");
        } else {
            Console.fail("the merchant offers " + value + " gold instead of " + REAL_VALUE
                    + " -- the rubies in the pouch are not counted");
        }

        Console.note("Refactor task: a common InventoryEntry interface with weight() and value(); "
                + "Item answers directly, Bag asks its children. No instanceof.");
    }
}
