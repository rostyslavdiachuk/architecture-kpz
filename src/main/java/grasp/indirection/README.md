# GRASP / Indirection

## Scenario

The bursar bills tuition through external payment providers — LiqPay and
PrivatBank today, more later.

## Smell

`TuitionBillingService.billTuition` talks straight to both vendor APIs. Their
differences — money units (kopiykas vs UAH), result shape (map vs status
string), idempotency (request id vs nothing) — leak into the billing method as
`if (provider == ...)` branches. The branches are not equivalent.

## Consequence in the demo

A network timeout triggers a retry of the same invoice. The PrivatBank branch
happens to pass an invoice-derived request id, so the retry is harmless. The
LiqPay branch does not — the student is **charged 24 000 UAH for a 12 000 UAH
invoice**.

## Your task (live)

Insert an intermediary so billing is decoupled from every vendor:

```java
interface PaymentGateway { PaymentReceipt authorize(PaymentRequest request); }
```

`LiqPayGateway`, `PrivatBankGateway`, `SandboxGateway` adapt each vendor —
converting units, normalising the receipt, and applying the invoice id as an
idempotency key **in one place**. `TuitionBillingService` holds a
`PaymentGateway` and no `if (provider…)`.

## Hints

- Indirection = introduce a mediating object so two elements need not know each
  other directly.
- The retry bug disappears because idempotency now lives in the adapter, not in
  each call site.

## Solution

```bash
git diff bad good -- src/main/java/ua/edu/chnu/grasp/indirection
```
