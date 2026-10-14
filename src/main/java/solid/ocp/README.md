# SOLID / OCP — Open/Closed Principle

## Scenario

The examination office turns a numeric score into a letter grade, GPA points and
a transcript label. Different courses use different grading schemes: standard,
pass/fail, honours (weighted), ECTS. This term a new one is needed — `THESIS`
for the master's-thesis defence.

## Smell

`FinalGradeCalculator` has the same `switch (scheme)` in three methods
(`letterGrade`, `gpaPoints`, `transcriptLabel`). The class is **open for
modification**, not extension: a new scheme forces edits in every switch. Worse,
the `default` arms are inconsistent — one returns `"N/A"`, one returns `0.0`
(silently corrupting the GPA), one throws.

## Consequence in the demo

Adding `THESIS` to the enum compiles fine, but the calculator produces a bogus
letter, a GPA-destroying `0.0`, and an exception — depending on which method you
call.

## Your task (live)

Turn `GradingScheme` into a strategy:

```java
public interface GradingScheme {
    String letterGrade(int score);
    double gpaPoints(int score);
    String label(int score);
}
```

Provide `StandardScheme`, `PassFailScheme`, `HonorsScheme`, `EctsScheme`, then
add `ThesisScheme` **without touching the calculator**. `FinalGradeCalculator`
becomes a one-liner that delegates (or disappears entirely).

## Hints

- Keep the demo's `main` almost unchanged — only the construction of the scheme
  differs.
- The three `default` arms becoming impossible is the point: every scheme now
  must answer every question.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/solid/ocp
```
