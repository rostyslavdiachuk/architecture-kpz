package grasp.controller;

import common.Console;
import grasp.controller.RegistrationController.RegisterCommand;
import grasp.controller.RegistrationController.RegistrationResult;

/**
 * The other entry point -- and it can no longer drift, because it runs the exact
 * same controller call. It only maps the result to an HTTP-ish status.
 */
public class RestRegistrationHandler {

    private final RegistrationController controller;

    public RestRegistrationHandler(RegistrationController controller) {
        this.controller = controller;
    }

    public void post(String studentId, String courseCode) {
        RegistrationResult result = controller.registerStudentForCourse(
                new RegisterCommand(studentId, courseCode));
        if (result.success()) {
            Console.ok("rest: 200 " + result.message());
        } else {
            Console.fail("rest: 409 " + result.message());
        }
    }
}
