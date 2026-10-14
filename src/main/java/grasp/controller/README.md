# GRASP / Controller

## Scenario

"Register a student for a course" can be triggered two ways: an operator picks it
from a console menu, or a client calls `POST /api/registrations`.

## Smell

Each entry point *is* the use case. `ConsoleRegistrationUI.onRegisterMenuOption`
looks up the student and course, checks capacity, updates the roster, sends the
e-mail and writes the audit line. `RestRegistrationHandler.post` does the same
thing again — and, being a later copy, has drifted: no capacity check, no
e-mail, a different audit format.

## Consequence in the demo

Register one student per path: only the console path e-mails a confirmation, and
the audit trail contains two incompatible line formats (`REG S-41 -> CS210`
vs `enroll:CS210:S-42`).

## Your task (live)

Introduce a single controller for the system operation:

```java
class RegistrationController {
    RegistrationResult registerStudentForCourse(RegisterCommand cmd) { ... }
}
```

Both UIs shrink to: parse input → call
`controller.registerStudentForCourse(...)` → render the result. The
lookup/capacity/roster/e-mail/audit sequence exists **once**.

## Hints

- A UI method whose body is a business workflow is the tell.
- Pick a use-case controller (per operation or per use-case family), not a
  single "god" controller for the whole system.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/controller
```
