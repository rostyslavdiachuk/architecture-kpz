# SOLID / DIP — Dependency Inversion Principle

## Scenario

`ScholarshipService` decides who receives a merit-and-need scholarship, records
the award, and e-mails the student.

## Smell

The high-level policy `new`s its own low-level details in its constructor:

```java
this.database = new PostgresScholarshipDatabase();
this.email    = new SmtpEmailClient();
this.clock    = new SystemClock();
```

The policy now *depends on* Postgres, SMTP and the wall clock. There is no seam:
you cannot substitute an in-memory store, a console mailer, or a fixed date.

## Consequence in the demo

Just calling `new ScholarshipService()` prints "connecting to Postgres" and
"opening SMTP session". The eligibility rule — the part worth testing — is
unreachable without the real infrastructure.

## Your task (live)

Invert the dependencies: define abstractions the service owns, inject them.

```java
interface ScholarshipRepository { List<ScholarshipApplication> findPending(); void update(ScholarshipApplication a); }
interface NotificationGateway   { void notify(String to, String subject, String body); }
interface Clock                 { LocalDate today(); }

ScholarshipService(ScholarshipRepository repo, NotificationGateway gateway, Clock clock) { ... }
```

Provide `InMemoryScholarshipRepository`, `ConsoleNotificationGateway`,
`FixedClock`; keep the Postgres/SMTP classes as *adapters* implementing the same
interfaces. Wire the pieces in `DipDemo.main`.

## Hints

- Both the policy and the details should depend on the interface; the policy
  should not mention `Postgres` or `Smtp` at all.
- The demo becomes runnable with zero infrastructure.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/solid/dip
```
