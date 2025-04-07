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

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String expiryDate;

    public CreditCardPayment(double amount, String currency, String cardNumber, String expiryDate) {
        super(amount, currency);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean validatePayment() {
        // منطق اعتبارسنجی کارت اعتباری
        return cardNumber.matches("\\d{16}") && expiryDate.matches("\\d{2}/\\d{2}");
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiry Date: " + expiryDate);
    }
}

