import java.util.ArrayList;

public class BankManager {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        SavingsAccount savings = new SavingsAccount("SA001", 5000.00, 0.025);
        CheckingAccount checking = new CheckingAccount("CA001", 3000.00, 500.00);
        
        accounts.add(savings);
        accounts.add(checking);

        System.out.println("=== Initial Account Details ===");
        for (BankAccount account : accounts) {
            System.out.println(account.getAccountDetails());
        }

        System.out.println("\n=== Performing Transactions ===");
        
        savings.deposit(200);
        
        checking.withdraw(3200); 
        for (BankAccount account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).applyInterest(); 
                System.out.println("Interest applied to Savings.");
            }
        }

        System.out.println("\n=== Final Account Details ===");
        for (BankAccount account : accounts) {
            System.out.println(account.getAccountDetails());
        }
    }
}
