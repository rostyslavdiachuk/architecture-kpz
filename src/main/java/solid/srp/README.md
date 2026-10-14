# SOLID / SRP — Single Responsibility Principle

## Scenario

The registrar's office enrolls a student in a course. Along the way the system
loads the student record, checks prerequisites and the per-term credit cap,
prices the tuition, writes a confirmation letter, e-mails it, and records an
audit line.

## Smell

`StudentEnrollmentService.enroll(...)` does **all of it in one method**:

- persistence — `studentTable.get / put`
- academic rules — prerequisites, 30-credit cap
- pricing — tuition + senior-student discount
- presentation — the confirmation letter text
- I/O — "SMTP" send
- audit — the `auditTable` line

Each concern answers to a different stakeholder, so the file has ~6 reasons to
change and any edit risks the others.

## Consequence in the demo

The `auditTable.add(...)` call sits **inside** the letter-formatting block, which
only runs on the happy path. Two rejected enrollments therefore leave **no audit
record** — the registrar cannot see that anyone was turned away.

## Your task (live)

Split the responsibilities into collaborators and reduce the service to an
orchestrator:

- `StudentRepository` — the in-memory table
- `EnrollmentPolicy` — prerequisite + credit-cap checks
- `TuitionCalculator` — price + discount
- `ConfirmationLetterFormatter` — the letter text
- `EmailNotifier` — the send
- `AuditLog` — always called, on every attempt

## Hints

- Start by extracting `AuditLog` and calling it on **every** path — that fixes
  the visible bug immediately.
- `enroll` should end up reading like a table of contents: validate, price,
  persist, notify, audit.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/solid/srp
```
