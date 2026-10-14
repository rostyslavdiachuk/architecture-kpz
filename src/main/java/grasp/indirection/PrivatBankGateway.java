package grasp.indirection;

import java.util.HashMap;
import java.util.Map;

import grasp.indirection.VendorApis.PrivatBankHttpApi;

/**
 * Adapter: passes UAH straight through, maps the status string, and applies the
 * same per-invoice idempotency rule as every other gateway.
 */
public class PrivatBankGateway implements PaymentGateway {

    private final PrivatBankHttpApi api;
    private final Map<String, PaymentReceipt> settled = new HashMap<>();

    public PrivatBankGateway(PrivatBankHttpApi api) {
        this.api = api;
    }

    @Override
    public PaymentReceipt authorize(PaymentRequest request) {
        PaymentReceipt cached = settled.get(request.invoiceId());
        if (cached != null) {
            return cached;
        }
        String status = api.pay(request.payerReference(), request.amountUah(), "inv-" + request.invoiceId());
        PaymentReceipt receipt = new PaymentReceipt("APPROVED".equals(status), "inv-" + request.invoiceId());
        settled.put(request.invoiceId(), receipt);
        return receipt;
    }
}
