import java.io.IOException;

public class Main {
    public static final int NACCOUNTS = 3;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        while (true) {
            int fromAccount = (int) (bank.accounts.length * Math.random());
            int toAccount = (int) (bank.accounts.length * Math.random());
            double amount = MAX_AMOUNT * Math.random();
            bank.transfer(fromAccount, toAccount, amount);
            Thread.sleep(500);
        }
    }
}
