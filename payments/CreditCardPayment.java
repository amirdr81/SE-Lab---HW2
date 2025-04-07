import java.time.LocalDateTime;

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
        double amount = this.getAmount();
        String currency = this.getCurrency();

        if (amount <= 0) {
            return false;
        }
        if (!currency.equals("USD") && !currency.equals("EUR") && !currency.equals("GBP")) {
            return false;
        }
        if (!customerInfo.containsKey("email")) {
            return false;
        }
        if (paymentDetails.getOrDefault("card_number", "").length() < 12) {
            return false;
        }
        return true;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiry Date: " + expiryDate);
    }
}