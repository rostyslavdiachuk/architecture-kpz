package grasp.indirection;

import common.Console;
import grasp.indirection.VendorApis.LiqPayHttpApi;
import grasp.indirection.VendorApis.PrivatBankHttpApi;

public class IndirectionDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Indirection -- a PaymentGateway between billing and vendors");

        LiqPayHttpApi liqPayApi = new LiqPayHttpApi();
        PrivatBankHttpApi privatBankApi = new PrivatBankHttpApi();

        runWith("LiqPay", new LiqPayGateway(liqPayApi), "INV-101", "card_tok_777");
        runWith("PrivatBank", new PrivatBankGateway(privatBankApi), "INV-100", "UA00PRIV0001");

        Console.header("Result");
        Console.ok("LiqPay total: " + liqPayApi.totalChargedUah() + " UAH   (retry deduped by the adapter)");
        Console.ok("PrivatBank total: " + privatBankApi.totalPaidUah() + " UAH   (retry deduped by the adapter)");
        Console.ok("TuitionBillingService has no idea which vendor ran; adding SandboxGateway "
                + "or a third provider does not touch it.");
    }

    private static void runWith(String name, PaymentGateway gateway, String invoiceId, String payerRef) {
        TuitionBillingService billing = new TuitionBillingService(gateway);
        Console.step("Bill " + invoiceId + " (12000 UAH) via " + name + ", then retry after a timeout");
        billing.billTuition(invoiceId, payerRef, 12_000);
        billing.billTuition(invoiceId, payerRef, 12_000);
    }
}
