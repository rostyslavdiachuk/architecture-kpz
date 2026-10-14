package solid.srp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** One responsibility: storing and retrieving students. */
public class StudentRepository {

    private final Map<String, Student> table = new HashMap<>();

    public void save(Student student) {
        table.put(student.id(), student);
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(table.get(id));
    }
}
