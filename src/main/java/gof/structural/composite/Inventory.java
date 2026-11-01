package gof.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite smell: items and bags have nothing in common, so the client has to
 * check types and walk the structure by hand.
 *
 * <p>Both totals were written when a bag could only hold items. Each one looks
 * exactly one level deep, so a bag inside a bag is counted as an empty bag.
 */
public class Inventory {

    private final List<Object> entries = new ArrayList<>();

    public Inventory add(Object itemOrBag) {
        entries.add(itemOrBag);
        return this;
    }

    public int totalWeight() {
        int total = 0;
        for (Object entry : entries) {
            if (entry instanceof Item item) {
                total += item.weight();
            } else if (entry instanceof Bag bag) {
                total += bag.weight();
                for (Object inner : bag.contents()) {
                    if (inner instanceof Item item) {
                        total += item.weight();
                    }
                }
            }
        }
        return total;
    }

    public int totalValue() {
        int total = 0;
        for (Object entry : entries) {
            if (entry instanceof Item item) {
                total += item.value();
            } else if (entry instanceof Bag bag) {
                for (Object inner : bag.contents()) {
                    if (inner instanceof Item item) {
                        total += item.value();
                    }
                }
            }
        }
        return total;
    }
}
