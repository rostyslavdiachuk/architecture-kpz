package solid.srp;

import java.util.ArrayList;
import java.util.List;

/** One responsibility: an append-only record of what the registrar's office did. */
public class AuditLog {

    private final List<String> lines = new ArrayList<>();

    public void record(String line) {
        lines.add(line);
    }

    public List<String> lines() {
        return List.copyOf(lines);
    }
}
