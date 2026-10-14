package grasp.informationexpert;

/** A recorded grade for one enrollment, 0..100. */
public record Grade(int score) {

    /** Four-point scale. */
    public double points() {
        if (score >= 90) return 4.0;
        if (score >= 80) return 3.0;
        if (score >= 70) return 2.0;
        if (score >= 60) return 1.0;
        return 0.0;
    }
}
