package grasp.informationexpert;

/** No GPA math here any more -- it asks the expert. */
public class TranscriptReportService {

    public double gpa(Student student) {
        return student.transcript().gpa();
    }
}
