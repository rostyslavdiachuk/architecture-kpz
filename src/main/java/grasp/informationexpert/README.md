# GRASP / Information Expert

## Scenario

A student's GPA is needed in two places: the printed transcript and the
scholarship office's eligibility check.

## Smell

`Enrollment` holds the grade and the course (with its credits) but has no
behaviour — it is anemic. So the GPA formula lives *outside* the data, in
`TranscriptReportService` and again in `ScholarshipEligibilityService`. The two
copies drifted: one weights courses by credits, the other averages them
unweighted.

## Consequence in the demo

For the same student the transcript service reports **3.71** and the scholarship
service **3.33**. With a 3.60 cutoff, the transcript implies "eligible" while the
office says "denied".

## Your task (live)

Give the responsibility to the information expert — the object that already holds
what the calculation needs:

- `Enrollment.qualityPoints()` → `grade.points() * course.credits()` (Enrollment
  knows both).
- A `Transcript` that owns the list of enrollments exposes `gpa()`.
- `TranscriptReportService` and `ScholarshipEligibilityService` call
  `transcript.gpa()` — one formula, one number.

## Hints

- Ask "who already has the data this needs?" — put the method there.
- After the move, both services shrink to a single line and cannot disagree.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/informationexpert
```
