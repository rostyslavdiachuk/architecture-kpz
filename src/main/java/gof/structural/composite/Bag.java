package gof.structural.composite;

import java.util.ArrayList;
import java.util.List;

/** The composite: asks its children, each of which can be an item or another bag. */
public class Bag implements InventoryEntry {

    private final String name;
    private final int ownWeight;
    private final List<InventoryEntry> contents = new ArrayList<>();

    public Bag(String name, int ownWeight) {
        this.name = name;
        this.ownWeight = ownWeight;
    }

    public Bag add(InventoryEntry entry) {
        contents.add(entry);
        return this;
    }

    public String name() {
        return name;
    }

    @Override
    public int weight() {
        return ownWeight + contents.stream().mapToInt(InventoryEntry::weight).sum();
    }

    @Override
    public int value() {
        return contents.stream().mapToInt(InventoryEntry::value).sum();
    }
}
