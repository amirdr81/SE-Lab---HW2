import java.util.Date;
import java.util.Map;

public class BankTransferGateway extends BaseGateway {

    public BankTransferGateway(Map<String, String> config) {
        super("BankTransferGateway", config);
    }

    @Override
    public Map<String, String> processPayment(double amount, String currency,
            Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        log("اتصال به API انتقال بانکی در " + config.get("bank_transfer_endpoint"));
        String transactionId = "BT" + new Date().getTime();
        log("پردازش انتقال بانکی برای " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }

    @Override
    public Map<String, String> refundPayment(String transactionId) {
        log("بازپرداخت تراکنش انتقال بانکی: " + transactionId);
        return Map.of("status", "refunded", "transaction_id", transactionId);
    }

    @Override
    public String getTransactionStatus(String transactionId) {
        log("دریافت وضعیت تراکنش انتقال بانکی: " + transactionId);
        return "SUCCESS";
    }
}
