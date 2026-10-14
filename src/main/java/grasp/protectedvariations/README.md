# GRASP / Protected Variations

## Scenario

A final score becomes a degree classification ("First Class", "Upper Second",
…) on the transcript, in the honours committee's letter, and in the scholarship
office's merit check. The thresholds are set by the ministry and revised every
few years — a known point of instability.

## Smell

Each consumer hard-codes its own copy of the threshold table.
`TranscriptPrinter` starts "First Class" at 90, `HonorsCommittee` at 91,
`ScholarshipCommittee` at 88 (a typo that now drives merit funding). Nothing
protects the consumers from the variation, and the copies have already drifted.

## Consequence in the demo

At a final score of **90**, the transcript prints "First Class" while the
honours committee says "Upper Second Class" — two official documents, two
answers.

## Your task (live)

Wrap the unstable rule behind a stable interface:

```java
interface GradeClassificationPolicy {
    String classify(int finalScore);
    String latinHonors(double gpa);
}
```

`Policy2024`, `Policy2026` implement it; the active policy is chosen in exactly
one place and injected. `TranscriptPrinter`, `HonorsCommittee` and
`ScholarshipCommittee` depend only on the interface — a future `Policy2027`
touches none of them.

## Hints

- Identify predicted points of variation; put a stable interface in front of
  each.
- This is the general principle behind OCP, polymorphism and indirection —
  worth stating explicitly in the lecture.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/protectedvariations
```
