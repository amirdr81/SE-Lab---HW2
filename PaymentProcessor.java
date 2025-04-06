import java.util.Map;

public class PaymentProcessor {
    private PaymentGateway gateway;

    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public Map<String, String> executePayment(double amount, String currency,
            Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        return gateway.processPayment(amount, currency, customerInfo, paymentDetails);
    }
}
