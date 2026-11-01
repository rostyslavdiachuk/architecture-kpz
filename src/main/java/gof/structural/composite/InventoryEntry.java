package gof.structural.composite;

/**
 * Composite: an item and a whole bag of items look the same from the outside.
 *
 * <p>Clients call {@link #weight()} or {@link #value()} and don't care which one
 * they have or how deep the tree goes.
 */
public interface InventoryEntry {

    int weight();

    int value();
}
