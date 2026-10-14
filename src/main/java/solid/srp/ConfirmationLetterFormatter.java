package solid.srp;

/** One responsibility: the wording of the confirmation letter. */
public class ConfirmationLetterFormatter {

    public String format(Student student, Course course, int tuitionUah) {
        return "Dear " + student.fullName() + ",\n"
                + "  You are now enrolled in " + course.code() + " \"" + course.title() + "\".\n"
                + "  Tuition due: " + tuitionUah + " UAH.\n"
                + "  Regards, Registrar's Office";
    }
}
