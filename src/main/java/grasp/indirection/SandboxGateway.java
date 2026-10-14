package grasp.indirection;

import common.Console;

/** Adapter for campus "test mode": approves everything, calls no vendor. */
public class SandboxGateway implements PaymentGateway {

    @Override
    public PaymentReceipt authorize(PaymentRequest request) {
        Console.note("sandbox: auto-approving " + request.invoiceId() + " for "
                + request.amountUah() + " UAH");
        return new PaymentReceipt(true, "sandbox-" + request.invoiceId());
    }
}
