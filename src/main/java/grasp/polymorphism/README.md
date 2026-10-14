# GRASP / Polymorphism

## Scenario

The registrar sends notifications (exam moved, grade posted, fee due) over
several channels: e-mail, SMS, push, Telegram — and now Viber.

## Smell

`NotificationCenter` picks behaviour with an `if (channel == …) … else if …`
chain, and the **same chain is repeated** in `send`, `formatPreview` and
`estimateCostUah`. Adding a channel means finding and editing all three; a missed
branch fails silently and inconsistently.

## Consequence in the demo

`Channel.VIBER` exists but no branch handles it: `send` drops the message,
`estimateCostUah` returns `0.00`, `formatPreview` returns `"???"`.

## Your task (live)

Replace the type code with polymorphic types:

```java
interface Channel {
    void deliver(String recipient, String text);
    String previewLabel(String recipient, String text);
    double costUah(String text);
}
```

`EmailChannel`, `SmsChannel`, `PushChannel`, `TelegramChannel`, then
`ViberChannel` — added with **no change** to `NotificationCenter`, which just
holds a `Map<..., Channel>` and delegates.

## Hints

- Same `switch`/`if-else` on a type in more than one method → polymorphism.
- This is the GRASP form of OCP; point that out in the lecture.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/polymorphism
```
