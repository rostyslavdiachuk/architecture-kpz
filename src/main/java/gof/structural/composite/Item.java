package gof.structural.composite;

/** The leaf: a single thing the hero carries. */
public record Item(String name, int weight, int value) implements InventoryEntry {
}
