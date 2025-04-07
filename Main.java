import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> config = Map.of(
                "credit_card_endpoint", "https://api.creditcard.com/process",
                "digital_wallet_endpoint", "https://api.digitalwallet.com/process",
                "bank_transfer_endpoint", "https://api.banktransfer.com/process");

        Map<String, String> customer = Map.of("name", "Kian Izadpanah", "email", "kianizadpanah@gmail.com");
        Map<String, String> creditPaymentDetails = Map.of("card_number", "123456789012", "expiry", "12/25", "cvv",
                "123");


        PaymentGateway creditGateway = new CreditCardGateway(config);
        PaymentProcessor processor = new PaymentProcessor(creditGateway);
        Map<String, String> result = processor.executePayment(100, "USD", customer, creditPaymentDetails);
        System.out.println("نتیجه پرداخت کارت اعتباری: " + result);

        PaymentGateway walletGateway = new DigitalWalletGateway(config);
        processor = new PaymentProcessor(walletGateway);
        Map<String, String> walletPaymentDetails = Map.of("wallet_id", "WALLET123");
        result = processor.executePayment(50, "USD", customer, walletPaymentDetails);
        System.out.println("نتیجه پرداخت کیف پول دیجیتال: " + result);
    }
}
