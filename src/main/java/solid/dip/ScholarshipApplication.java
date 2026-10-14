package solid.dip;

public class ScholarshipApplication {

    private final String studentId;
    private final String email;
    private final double gpa;
    private final int monthlyFamilyIncomeUah;
    private boolean awarded;

    public ScholarshipApplication(String studentId, String email, double gpa, int monthlyFamilyIncomeUah) {
        this.studentId = studentId;
        this.email = email;
        this.gpa = gpa;
        this.monthlyFamilyIncomeUah = monthlyFamilyIncomeUah;
    }

    public String studentId() {
        return studentId;
    }

    public String email() {
        return email;
    }

    public double gpa() {
        return gpa;
    }

    public int monthlyFamilyIncomeUah() {
        return monthlyFamilyIncomeUah;
    }

    public boolean awarded() {
        return awarded;
    }

    public void markAwarded() {
        this.awarded = true;
    }
}
