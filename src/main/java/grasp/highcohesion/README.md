# GRASP / High Cohesion

## Scenario

A back-office component handles: registering students, running lecturer payroll,
shelving returned library books (with fines), scheduling classrooms, and
producing a fire-safety report.

## Smell

`UniversityAdminFacade` does all five. Its fields — a student map, a payslip
list, shelved books, room bookings, an extinguisher count — are never used
together; each method touches one. The class has five unrelated reasons to
change, and testing one job means dragging in the rest.

There is even an accidental coupling: `nextSequenceNumber` is reused as student
id, booking reference **and** payslip number.

## Consequence in the demo

Payslips come out numbered `PS-1003`, `PS-1004` because student registration and
room booking already consumed `1000`–`1002` from the shared counter. Change the
payslip numbering rule and student ids shift with it.

## Your task (live)

Split into cohesive services, each owning only its own state:

- `RegistrationService` — the student map + its own id sequence
- `PayrollService` — payslips + its own sequence
- `LibraryService` — shelved books + fine rule
- `ClassroomScheduler` — bookings
- `FacilitiesReportService` — safety data (reads a scheduler if it needs room use)

## Hints

- A quick cohesion check: do the fields form one cluster all methods use, or
  several clusters each used by one method?
- Shared mutable counters across unrelated features are a red flag.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/highcohesion
```
