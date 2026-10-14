# GRASP / Creator

## Scenario

A student registers for a course. Somewhere an `Enrollment` object must be
created and the course roster kept up to date so capacity checks work.

## Smell

`EnrollmentManager.createEnrollment(student, course, semester)` does the `new
Enrollment(...)`. The manager neither aggregates enrollments nor holds the data
an enrollment needs — the `Course` does both. Because creation is detached from
the `Course`, "add it to the roster" is a second step, and it is easy to skip.

## Consequence in the demo

Three enrollments are created for a two-seat course. `EnrollmentManager` has all
three; `course.roster()` has **zero**; `course.remainingSeats()` still says `2`.
The course will keep accepting students forever.

## Your task (live)

Make the `Course` the creator — it contains the roster and holds the capacity
rule:

```java
public Enrollment enroll(Student student, Semester semester) {
    if (remainingSeats() <= 0) throw new IllegalStateException(code + " is full");
    Enrollment e = new Enrollment(student, this, semester);
    roster.add(e);
    return e;
}
```

Stop exposing the mutable `roster` list. `EnrollmentManager` either disappears or
just calls `course.enroll(...)`.

## Hints

- Assign creation to the class that aggregates, contains, or has the initializing
  data for the created object.
- Once `Course` creates the enrollment, the roster can never be out of sync.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/creator
```
