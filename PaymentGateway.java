import java.util.Map;

public interface PaymentGateway {
    Map<String, String> processPayment(double amount, String currency,
            Map<String, String> customerInfo, Map<String, String> paymentDetails);

    Map<String, String> refundPayment(String transactionId);

    String getTransactionStatus(String transactionId);
}
