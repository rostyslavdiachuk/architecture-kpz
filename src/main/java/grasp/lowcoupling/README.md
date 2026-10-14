# GRASP / Low Coupling

## Scenario

`DegreeAuditService` decides whether a student has met the requirements to
graduate, then (if so) renders a diploma, e-mails the student and publishes an
event.

## Smell

The service is bound to **six** concrete infrastructure classes
(`PostgresCourseCatalog`, `LdapStudentDirectory`, `LegacyGradeMainframeClient`,
`EmailBlastService`, `PdfDiplomaRenderer`, `RegistrarEventBus`) and constructs
every one of them itself. On top of that it runs the degree rules directly on
`Student`'s raw fields — feature envy. Any change in any collaborator, or in the
definition of "passed", ripples into this class.

## Consequence in the demo

Just constructing the service starts a JDBC pool, an LDAP bind, a 3270 session, a
bulk mailer, a template loader and an AMQP connection. Replacing one of them (say
the catalog with a REST client) forces an edit here.

## Your task (live)

Cut the collaborators down to what the audit actually needs:

- `StudentAcademicRecord` — `passedCourses()`, `earnedCredits()`, `gpa()`,
  `hasOutstandingIncomplete()`. One role, however it is backed.
- `DegreeRequirements` — a list of `DegreeRequirement` objects, each of which
  checks *itself* against the record (`RequiredCoursesRequirement`,
  `MinGpaRequirement`, `MinCreditsRequirement`).
- Diploma / mail / events move behind small interfaces, injected in.

`DegreeAuditService` ends up depending on 2 abstractions and holding no `new`.

## Hints

- Count the concrete types a class names; that is roughly its coupling.
- "Does this class ask another object for data so it can make a decision the
  other object could make?" → move the decision.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/lowcoupling
```
