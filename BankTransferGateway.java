import java.util.Date;
import java.util.Map;

public class BankTransferGateway extends BaseGateway {

    public BankTransferGateway(ConfigurationManager configManager) {
        super("BankTransferGateway", configManager);
    }

    @Override
    public Map<String, String> processPayment(double amount, String currency,
            Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        String endpoint = configManager.getProperty("bank_transfer_endpoint");
        log("Connecting to Bank Transfer API at " + endpoint);
        String transactionId = "BT" + new Date().getTime();
        log("Processing bank transfer payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }

    @Override
    public Map<String, String> refundPayment(String transactionId) {
        log("Refunding bank transfer transaction: " + transactionId);
        return Map.of("status", "refunded", "transaction_id", transactionId);
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        log("Getting bank transfer transaction status for: " + transactionId);
        return "SUCCESS"; // Example status
    }
}
