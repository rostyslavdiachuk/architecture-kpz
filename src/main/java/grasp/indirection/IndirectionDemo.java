package grasp.indirection;

import common.Console;
import grasp.indirection.TuitionBillingService.Provider;
import grasp.indirection.VendorApis.LiqPayHttpApi;
import grasp.indirection.VendorApis.PrivatBankHttpApi;

public class IndirectionDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Indirection -- billing calls two payment vendors directly");

        LiqPayHttpApi liqPay = new LiqPayHttpApi();
        PrivatBankHttpApi privatBank = new PrivatBankHttpApi();
        TuitionBillingService billing = new TuitionBillingService(liqPay, privatBank);

        Console.step("Bill invoice INV-100 (12000 UAH) via PrivatBank, then retry after a timeout");
        billing.billTuition("INV-100", "UA00PRIV0001", 12_000, Provider.PRIVATBANK);
        billing.billTuition("INV-100", "UA00PRIV0001", 12_000, Provider.PRIVATBANK); // retry

        Console.step("Bill invoice INV-101 (12000 UAH) via LiqPay, then retry after a timeout");
        billing.billTuition("INV-101", "card_tok_777", 12_000, Provider.LIQPAY);
        billing.billTuition("INV-101", "card_tok_777", 12_000, Provider.LIQPAY); // retry

        Console.header("Result");
        Console.note("PrivatBank total: " + privatBank.totalPaidUah() + " UAH  (retry used the "
                + "same request id -> no double charge... by luck of that branch)");
        Console.fail("LiqPay total: " + liqPay.totalChargedUah() + " UAH  -- the retry charged "
                + "the student twice; that branch never learned about idempotency");
        Console.note("Refactor task: put a PaymentGateway intermediary between billing and "
                + "the vendors; LiqPayGateway / PrivatBankGateway / SandboxGateway adapters "
                + "normalise units, results and idempotency. Billing depends only on the "
                + "intermediary.");
    }
}
