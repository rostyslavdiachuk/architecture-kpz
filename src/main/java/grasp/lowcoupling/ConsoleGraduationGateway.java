package grasp.lowcoupling;

import java.util.List;

import common.Console;

/** Demo implementation: no diploma templates, no bulk mailer, no AMQP. */
public class ConsoleGraduationGateway implements GraduationGateway {

    @Override
    public void announceCleared(String studentName, String programme) {
        Console.step("diploma + mail + event: " + studentName + " cleared for " + programme);
    }

    @Override
    public void announceBlocked(String studentName, List<String> reasons) {
        Console.step("event: " + studentName + " blocked -- " + reasons);
    }
}
