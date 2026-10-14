# SOLID / ISP — Interface Segregation Principle

## Scenario

The system models people at the university — students, lecturers, department
heads. An end-of-term job needs "something that can submit grades"; the campus
portal needs "something that can request a transcript".

## Smell

There is one interface, `UniversityMember`, with every action anyone can perform:
`teachCourse`, `submitGrades`, `holdOfficeHours`, `superviseThesis`,
`enrollInCourse`, `payTuition`, `requestTranscript`, `approveDepartmentBudget`.

- `Student` implements it and throws from the five teaching/budget methods.
- `Lecturer` implements it and throws from the three student methods.
- Every client must wrap every call in `try/catch`.

## Consequence in the demo

`TermClosingJob.collectGrades` blows up on every student; the portal's transcript
loop blows up on every lecturer.

## Your task (live)

Segregate into role interfaces and let each type implement only what fits:

```java
interface Teacher            { void teachCourse(String c); void submitGrades(String c); void holdOfficeHours(); }
interface ThesisSupervisor   { void superviseThesis(String studentId); }
interface Learner            { void enrollInCourse(String c); void payTuition(int uah); }
interface TranscriptRequester{ void requestTranscript(); }
interface BudgetApprover     { void approveDepartmentBudget(int uah); }
```

`Student implements Learner, TranscriptRequester`.
`Lecturer implements Teacher, ThesisSupervisor` (a head also `BudgetApprover`).
`TermClosingJob` takes `List<Teacher>`; the portal takes `List<TranscriptRequester>`.

## Hints

- Every `throw new UnsupportedOperationException` here marks a method that should
  be on a different interface.
- Clients get narrower parameter types and the `try/catch` disappears.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/solid/isp
```
