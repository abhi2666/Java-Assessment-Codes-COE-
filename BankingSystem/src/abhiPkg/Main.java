package abhiPkg;

public class Main {

	public static void main(String[] args) {
		 // create two accounts 
		Account ac1 = new Account("LAPD42069", 5000);
		Account ac2 = new Account("SPD4503DD", 6000);
		//put these accounts into bank
		Bank b = new Bank();
		b.addAccount(ac1);
		b.addAccount(ac2);
		// get the accounts in the bank
		String testAccNumber = "pikachu";
		Account res = b.getAccount(testAccNumber); // not accout with such account number exists 
		if(res==null) {
			System.out.println(testAccNumber + " account does not exist !!");
		}
		else {
			System.out.println("Account balance is --> " + res.getBalance());
		} 
		System.out.println("Before transfer -- \n" + ac1.getBalance() +"  "+ ac2.getBalance());
		b.transferFunds(ac1.getAccountNumber(), ac2.getAccountNumber(), 200); // from , to , howmuch
		//check balance of both the accounts 
		System.out.println("After transfer -- \n" + ac1.getBalance() + "  "+ ac2.getBalance());
	}

}
