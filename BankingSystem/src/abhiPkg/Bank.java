package abhiPkg;

import java.util.HashMap;
import java.util.Map;

//store different accounts 
public class Bank {
    private Map<String, Account> accounts; // accountnumber, account details 

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    /*
     * while transferring funds both the accounts should be locked meaning those accounts cannot participate in
     * another transaction
     */
    public synchronized void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
    	// getting the account details of both the user from the hash map
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);
        // checking if accounts with such account numbers exists or not
        if (fromAccount != null && toAccount != null) {
        	synchronized(fromAccount) {
        		 synchronized (toAccount) {
        			 if (fromAccount.getBalance() >= amount) {
        	                fromAccount.withdraw(amount);
        	                toAccount.deposit(amount);
        	                System.out.println("Transferred: " + amount + " from " + fromAccountNumber + " to " + toAccountNumber);
        	            } else {
        	                System.out.println("Transfer failed. Insufficient funds.");
        	            }
				}
        	}
        }
    }
}
