package solid.srp;

import common.Console;

/** One responsibility: delivering a message to a student. */
public class EmailNotifier {

    public void send(String to, String body) {
        Console.step("SMTP -> " + to + "\n" + indent(body));
    }

    private static String indent(String block) {
        StringBuilder sb = new StringBuilder();
        for (String line : block.split("\n")) {
            sb.append("      | ").append(line).append('\n');
        }
        return sb.toString().stripTrailing();
    }
}
