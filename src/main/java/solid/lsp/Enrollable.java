package solid.lsp;

import java.util.List;

/**
 * The narrow contract the {@link RegistrationBatch} actually needs. Only
 * sections that can genuinely enroll a student implement it, and the contract is
 * exact: {@link #enroll} adds the student to {@link #roster()} or throws
 * {@link SectionFullException} -- nothing else.
 */
public interface Enrollable {

    String code();

    void enroll(Student student);

    List<Student> roster();
}
