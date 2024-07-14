package abhiPkg;

public abstract class Transactions {
    protected Account account;
    protected double amount;

    public Transactions(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public abstract void execute();
}

class Deposit extends Transactions {
    public Deposit(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }
}

class Withdrawal extends Transactions {
    public Withdrawal(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void execute() {
        account.withdraw(amount);
    }
}
