package grasp.controller;

import common.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Small in-memory stand-ins shared by both entry points. */
public final class Infrastructure {

    private Infrastructure() {
    }

    public static class StudentDirectory {
        private final Map<String, Student> byId = new HashMap<>();

        public void add(Student s) {
            byId.put(s.id(), s);
        }

        public Student find(String id) {
            return byId.get(id);
        }
    }

    public static class CourseCatalog {
        private final Map<String, Course> byCode = new HashMap<>();

        public void add(Course c) {
            byCode.put(c.code(), c);
        }

        public Course find(String code) {
            return byCode.get(code);
        }
    }

    public static class EmailNotifier {
        public void send(String to, String body) {
            Console.step("SMTP -> " + to + " | " + body);
        }
    }

    public static class AuditLog {
        private final List<String> lines = new ArrayList<>();

        public void record(String line) {
            lines.add(line);
        }

        public List<String> lines() {
            return lines;
        }
    }
}
