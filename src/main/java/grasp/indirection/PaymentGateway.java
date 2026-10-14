package grasp.indirection;

/**
 * The intermediary. Billing talks to this and nothing else; each vendor sits
 * behind an adapter that implements it.
 */
public interface PaymentGateway {

    record PaymentRequest(String invoiceId, String payerReference, long amountUah) {
    }

    record PaymentReceipt(boolean approved, String reference) {
    }

    PaymentReceipt authorize(PaymentRequest request);
}
