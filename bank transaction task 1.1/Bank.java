import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private int accountCounter;

    // Parameterized Constructor
    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.accountCounter = 1000;
    }

    // ─── Account Management ───────────────────────────────────────────────────

    public Account createAccount(String ownerName, String email, String phone,
                                  String accountType, double initialDeposit) {
        accountCounter++;
        String customerId = "CUST" + accountCounter;
        String accountNumber = "ACC" + accountCounter;

        Customer customer = new Customer(customerId, ownerName, email, phone);
        Account account = new Account(accountNumber, accountType, initialDeposit, customer);
        accounts.add(account);

        System.out.println("\n  ✓ Account created successfully!");
        account.printSummary();
        return account;
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\n  No accounts found in the system.");
            return;
        }
        System.out.println("\n  ╔══════════════════════════════════════════════════╗");
        System.out.printf( "  ║  %-48s║%n", bankName + " - All Accounts");
        System.out.println("  ╠══════════════════════════════════════════════════╣");
        for (Account acc : accounts) {
            System.out.printf("  ║  %-12s | %-10s | %-10s | $%-8.2f║%n",
                    acc.getAccountNumber(),
                    acc.getAccountType(),
                    acc.getCustomer().getName().length() > 10
                            ? acc.getCustomer().getName().substring(0, 10)
                            : acc.getCustomer().getName(),
                    acc.getBalance());
        }
        System.out.println("  ╚══════════════════════════════════════════════════╝");
    }

    public String getBankName() { return bankName; }
    public int getTotalAccounts() { return accounts.size(); }
}
