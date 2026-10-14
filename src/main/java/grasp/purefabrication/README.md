# GRASP / Pure Fabrication

## Scenario

A `Student` is created during admission, a domain rule decides dean's-list
eligibility, the record is stored, and a welcome e-mail goes out.

## Smell

`Student` — a domain entity — also owns:

- persistence: `saveToDatabase()` builds SQL by hand and uses a static
  `FakeConnection`;
- outbound e-mail: `emailWelcome()`.

Assigning "save yourself" to `Student` follows Information Expert too literally.
It gives the class three reasons to change, binds the domain model to a SQL
dialect, and means the pure rule `isEligibleForDeansList()` can't be touched
without the database.

## Consequence in the demo

Merely loading the `Student` class prints `FakeConnection: opened jdbc:...` —
the domain rule cannot be exercised in isolation.

## Your task (live)

Invent a class that is **not** a domain concept, created purely to give
persistence a cohesive, low-coupling home — a *pure fabrication*:

- `StudentRepository` — `save(Student)`, `findById(long)`, backed by a
  `Map<Long, Student>`.
- `WelcomeMailer` — `sendWelcome(Student)`.

`Student` keeps only state and domain behaviour; no `import java.sql`, no static
connection.

## Hints

- "Repository", "Service", "Factory", "Mapper" are typical pure fabrications —
  invented for design quality, not found in the domain vocabulary.
- The test for `isEligibleForDeansList()` should need nothing but a `Student`.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/purefabrication
```
