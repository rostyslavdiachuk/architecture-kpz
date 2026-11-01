package gof.structural.composite;

import java.util.ArrayList;
import java.util.List;

/** A container with its own weight. It can hold items and other bags. */
public class Bag {

    private final String name;
    private final int weight;
    private final List<Object> contents = new ArrayList<>();

    public Bag(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Bag add(Object itemOrBag) {
        contents.add(itemOrBag);
        return this;
    }

    public String name() {
        return name;
    }

    public int weight() {
        return weight;
    }

    public List<Object> contents() {
        return contents;
    }
}
