import java.time.LocalDateTime;

public class BankTransferPayment extends Payment {
    private String bankAccountNumber;

    public BankTransferPayment(double amount, String currency, String bankAccountNumber) {
        super(amount, currency);
        this.bankAccountNumber = bankAccountNumber;
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
        if (!paymentDetails.containsKey("account_number")) {
            return false;
        }
        return true;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Bank Account Number: " + bankAccountNumber);
    }
}
