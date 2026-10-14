package grasp.indirection;

import common.Console;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Two third-party payment APIs with nothing in common: different method names,
 * different money units, different result shapes, different (or absent)
 * idempotency support.
 */
public final class VendorApis {

    private VendorApis() {
    }

    /** LiqPay-style: amounts in kopiykas, returns a map, no dedup. */
    public static class LiqPayHttpApi {
        private long chargedKopiykas;

        public Map<String, Object> charge(String cardToken, long amountKopiykas) {
            chargedKopiykas += amountKopiykas;
            Console.note("LiqPay HTTP: charge card " + cardToken + " " + amountKopiykas + " kop");
            Map<String, Object> body = new HashMap<>();
            body.put("result", "ok");
            body.put("payment_id", "lp_" + System.nanoTime());
            return body;
        }

        public long totalChargedUah() {
            return chargedKopiykas / 100;
        }
    }

    /** PrivatBank-style: amounts in UAH, returns a status string, idempotent on request id. */
    public static class PrivatBankHttpApi {
        private long paidUah;
        private final Set<String> seenRequestIds = new HashSet<>();

        public String pay(String iban, long amountUah, String requestId) {
            if (!seenRequestIds.add(requestId)) {
                Console.note("PrivatBank HTTP: request " + requestId + " already settled -- returning cached result");
                return "APPROVED";
            }
            Console.note("PrivatBank HTTP: pay " + iban + " " + amountUah + " UAH (req " + requestId + ")");
            paidUah += amountUah;
            return "APPROVED";
        }

        public long totalPaidUah() {
            return paidUah;
        }
    }
}
