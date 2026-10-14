package grasp.purefabrication;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import common.Console;

/**
 * A pure fabrication: "repository" is not a word from the university's business
 * vocabulary. It was invented purely to give persistence a home with high
 * cohesion (only storage concerns) and low coupling (the domain does not depend
 * on a database). Here it is backed by a {@link Map}; in production it would
 * hold a real connection.
 */
public class StudentRepository {

    private final Map<Long, Student> table = new HashMap<>();

    public void save(Student student) {
        table.put(student.id(), student);
        Console.note("repository: stored student " + student.id());
    }

    public Optional<Student> findById(long id) {
        return Optional.ofNullable(table.get(id));
    }
}
