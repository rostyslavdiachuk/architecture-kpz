package grasp.indirection;

import java.util.HashMap;
import java.util.Map;

import grasp.indirection.VendorApis.LiqPayHttpApi;

/**
 * Adapter: converts UAH to kopiykas, reads LiqPay's map result, and -- in the
 * one place it belongs -- makes the call idempotent per invoice.
 */
public class LiqPayGateway implements PaymentGateway {

    private final LiqPayHttpApi api;
    private final Map<String, PaymentReceipt> settled = new HashMap<>();

    public LiqPayGateway(LiqPayHttpApi api) {
        this.api = api;
    }

    @Override
    public PaymentReceipt authorize(PaymentRequest request) {
        PaymentReceipt cached = settled.get(request.invoiceId());
        if (cached != null) {
            return cached;
        }
        Map<String, Object> response = api.charge(request.payerReference(), request.amountUah() * 100);
        PaymentReceipt receipt = new PaymentReceipt(
                "ok".equals(response.get("result")),
                String.valueOf(response.get("payment_id")));
        settled.put(request.invoiceId(), receipt);
        return receipt;
    }
}
