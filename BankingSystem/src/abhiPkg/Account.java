package abhiPkg;

// this account class will be used by bank class to create different accounts with different balance
public class Account {
    private String accountNumber; //can be alphanumeric 
    private double balance;

    // initializing of a user account
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // only one thread can access this function at a time 
    public synchronized void deposit(double amount) {
    	balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
 }
  
    // only one thread can access this function at a time 
    public synchronized void withdraw(double amount) {
    	// checking if user has the required balance or not
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }
}
