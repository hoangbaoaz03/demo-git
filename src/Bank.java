import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private Condition condition;
    private Lock lock;
    public final double[] accounts;
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }
    public synchronized void transfer(int from, int to, double amount) {
        lock.lock();
        try {
            while (accounts[from] < amount) {
                try {
                    System.out.println(Thread.currentThread().getName() + "  " + from + " is not enough");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            notifyAll();
        } finally {
            lock.unlock();
        }
    }
    public double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }
}
