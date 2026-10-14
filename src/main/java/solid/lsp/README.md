# SOLID / LSP — Liskov Substitution Principle

## Scenario

A nightly `RegistrationBatch` enrolls students into course sections. It is
written once, against `CourseSection`, and treats every section identically —
on-campus, online, waitlisted.

## Smell

Two subclasses are not substitutable for their base:

- `SelfPacedOnlineSection.enroll(...)` throws `UnsupportedOperationException`
  (and so does `scheduleRoom`). It strengthens the precondition — "don't call
  this" — which the base never declared.
- `WaitlistedSection.enroll(...)` returns normally **without** adding the student
  and **without** throwing. It weakens the postcondition the base guarantees.

## Consequence in the demo

The batch — correct against the base contract — crashes on the online section
and silently loses a student on the waitlisted one.

## Your task (live)

- Introduce a narrow `Enrollable` abstraction (`enroll(Student)` +
  `roster()`), implemented only by sections that genuinely can enroll.
- Move `room` / `scheduleRoom` off the base into a `ScheduledSection` that has a
  room; `OnlineSection` does not extend it.
- `WaitlistedSection` must honour the contract: either add the student or throw
  a documented `Waitlisted` outcome the caller can see.
- `RegistrationBatch` depends on `Enrollable` and is now always correct.

## Hints

- If a subclass override is `throw new UnsupportedOperationException(...)`, the
  hierarchy is wrong — the method does not belong on the base.
- "Substitutable" means the batch should not be able to tell which concrete
  section it holds.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/solid/lsp
```
