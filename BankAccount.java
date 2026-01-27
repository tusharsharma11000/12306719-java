// Task 1
class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

   
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }

   
    public String getAccountDetails() {
        return "Account Number: " + accountNumber + "\n"
             + "Account Holder: " + accountHolderName + "\n"
             + "Balance: $" + String.format("%.2f", balance);
    }


    public static void main(String[] args) {

        BankAccount account = new BankAccount("ACC1001", "Alice Smith");

        account.deposit(1000);
        account.withdraw(250);

        System.out.println(account.getAccountDetails());
    }
}
// Task 2
class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double interestRate) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest =  balance * interestRate;
        balance += interest;
    }

    @Override
    public String getAccountDetails() {
        return "Savings Account #" + getAccountNumber()
                + ", Balance: $" + String.format("%.2f", balance)
                + ", Rate: " + (interestRate * 100) + "%";
    }
}

class CheckingAccount extends BankAccount {

    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolderName, double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (balance - amount < -overdraftLimit) {
            throw new IllegalArgumentException("Overdraft limit exceeded.");
        }

        balance -= amount;

        if (balance < 0) {
            System.out.println("Overdraft used. Current balance: $" 
                    + String.format("%.2f", balance));
        }
    }

    @Override
    public String getAccountDetails() {
        return "Checking Account #" + getAccountNumber()
                + ", Balance: $" + String.format("%.2f", balance)
                + ", Limit: $" + String.format("%.2f", overdraftLimit);
    }
}

class abc {
    public static void main(String[] args) {

        SavingsAccount sa = new SavingsAccount("SAV123", "Alice Smith", 0.02);
        sa.deposit(1000);
        sa.applyInterest();
        System.out.println(sa.getAccountDetails());

        CheckingAccount ca = new CheckingAccount("CHK678", "Bob Johnson", 500);
        ca.deposit(200);
        ca.withdraw(250);
        System.out.println(ca.getAccountDetails());
    }
}


