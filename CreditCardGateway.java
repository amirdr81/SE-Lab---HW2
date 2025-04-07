import java.util.Date;
import java.util.Map;

public class CreditCardGateway extends BaseGateway {

    public CreditCardGateway(ConfigurationManager configManager) {
        super("CreditCardGateway", configManager);
    }

    @Override
    public Map<String, String> processPayment(double amount, String currency,
            Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        String endpoint = configManager.getProperty("credit_card_endpoint");
        log("اتصال به API کارت اعتباری در " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        log("پردازش پرداخت کارت اعتباری برای " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }

    @Override
    public Map<String, String> refundPayment(String transactionId) {
        log("بازپرداخت تراکنش کارت اعتباری: " + transactionId);
        return Map.of("status", "refunded", "transaction_id", transactionId);
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        log("دریافت وضعیت تراکنش کارت اعتباری: " + transactionId);
        return "SUCCESS"; // مثال ساده
    }
}
