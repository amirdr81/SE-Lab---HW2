import java.time.LocalDateTime;

public class DigitalWalletPayment extends Payment {
    private String walletId;

    public DigitalWalletPayment(double amount, String currency, String walletId) {
        super(amount, currency);
        this.walletId = walletId;
    }

    @Override
    public boolean validatePayment() {
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
        if (!paymentDetails.containsKey("wallet_id")) {
            return false;
        }
        return true;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Wallet ID: " + walletId);
    }
}