import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ConfigurationManager configManager = new ConfigurationManager("config.properties");
        PaymentGateway creditGateway = new CreditCardGateway(configManager);
        PaymentProcessor processor = new PaymentProcessor(creditGateway);

        Map<String, String> customer = Map.of("name", "John Doe", "email", "john@example.com");
        Map<String, String> creditPaymentDetails = Map.of(
                "card_number", "123456789012",
                "expiry", "12/25",
                "cvv", "123");

        Map<String, String> result = processor.executePayment(100, "USD", customer, creditPaymentDetails);
        System.out.println("نتیجه پرداخت کارت اعتباری: " + result);
    }
}
