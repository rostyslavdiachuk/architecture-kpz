package grasp.indirection;

import common.Console;
import grasp.indirection.VendorApis.LiqPayHttpApi;
import grasp.indirection.VendorApis.PrivatBankHttpApi;

import java.util.Map;

/**
 * GRASP / Indirection smell: billing talks straight to two payment vendors.
 *
 * <p>Vendor-specific details (money units, result parsing, idempotency) are
 * sprinkled through the billing method as {@code if (provider == ...)} branches.
 * The branches are not equivalent: the PrivatBank branch passes an invoice-based
 * request id (so a retry is a no-op), the LiqPay branch does not -- so a retry
 * charges again.
 */
public class TuitionBillingService {

    public enum Provider {LIQPAY, PRIVATBANK}

    private final LiqPayHttpApi liqPay;
    private final PrivatBankHttpApi privatBank;

    public TuitionBillingService(LiqPayHttpApi liqPay, PrivatBankHttpApi privatBank) {
        this.liqPay = liqPay;
        this.privatBank = privatBank;
    }

    public void billTuition(String invoiceId, String studentPayToken, long amountUah, Provider provider) {
        if (provider == Provider.LIQPAY) {
            Map<String, Object> response = liqPay.charge(studentPayToken, amountUah * 100);
            if ("ok".equals(response.get("result"))) {
                Console.ok("invoice " + invoiceId + " paid via LiqPay (" + response.get("payment_id") + ")");
            } else {
                Console.fail("invoice " + invoiceId + " LiqPay declined");
            }
        } else if (provider == Provider.PRIVATBANK) {
            String status = privatBank.pay(studentPayToken, amountUah, "inv-" + invoiceId);
            if ("APPROVED".equals(status)) {
                Console.ok("invoice " + invoiceId + " paid via PrivatBank");
            } else {
                Console.fail("invoice " + invoiceId + " PrivatBank status " + status);
            }
        } else {
            throw new IllegalArgumentException("unsupported provider " + provider);
        }
    }
}
