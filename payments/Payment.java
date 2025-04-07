import java.time.LocalDateTime;

public abstract class Payment {
    private double amount;
    private String currency;
    private LocalDateTime timestamp;

    // سازنده
    public Payment(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
        this.timestamp = LocalDateTime.now(); // زمان ثبت پرداخت
    }

    // متدهای getter
    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // متد انتزاعی برای اعتبارسنجی پرداخت
    public abstract boolean validatePayment();

    // متد عمومی برای نمایش جزئیات پرداخت
    public void printDetails() {
        System.out.println("Payment Details:");
        System.out.println("Amount: " + amount + " " + currency);
        System.out.println("Timestamp: " + timestamp);
    }
}
