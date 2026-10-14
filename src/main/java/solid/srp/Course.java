package solid.srp;

import java.util.List;

/** A course offered in the catalogue. */
public class Course {

    private final String code;
    private final String title;
    private final int credits;
    private final List<String> prerequisiteCodes;
    private final int pricePerCredit;

    public Course(String code, String title, int credits, List<String> prerequisiteCodes, int pricePerCredit) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.prerequisiteCodes = List.copyOf(prerequisiteCodes);
        this.pricePerCredit = pricePerCredit;
    }

    public String code() {
        return code;
    }

    public String title() {
        return title;
    }

    public int credits() {
        return credits;
    }

    public List<String> prerequisiteCodes() {
        return prerequisiteCodes;
    }

    public int pricePerCredit() {
        return pricePerCredit;
    }
}
