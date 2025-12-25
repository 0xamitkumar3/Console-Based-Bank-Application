package app;
import service.BankService;
import service.impl.BankServiceImpl;
import java.util.Scanner;
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();
        System.out.println("Welcome to Console Bank");
        boolean running = true;
        while(running){
            System.out.println("""
                1) Open Account
                2) Deposit
                3) Withdraw
                4) Transfer
                5) Account Statement
                6) List Accounts
                7) Search Accounts by Customer Name
                0) Exit
            """);
            System.out.print("CHOOSE: ");
            String choice = sc.nextLine().trim();
            System.out.println("Choice: "+choice);

            switch (choice){
                case "1" -> openAccount(sc, bankService);
                case "2" -> deposit(sc, bankService);
                case "3" -> withdraw(sc, bankService);
                case "4" -> transfer(sc, bankService);
                case "5" -> statement(sc, bankService);
                case "6" -> listAccounts(sc, bankService);
                case "7" -> searchAccounts(sc, bankService);
                case "0" -> running = false;
            }
        }
    }

    private static void openAccount(Scanner sc, BankService bankService) {
        System.out.print("Customer Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Customer Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Account Type (SAVINGS/CURRENT): ");
        String type = sc.nextLine().trim();
        System.out.println("Initial deposit (optional, blank for 0): ");
        String amountStr = sc.nextLine().trim();
        if(amountStr.isBlank()) amountStr = "0";
        Double initial = Double.valueOf(amountStr);
        String accountNumber = bankService.openAccount(name, email, type);
        if(initial > 0)
            bankService.deposit(accountNumber, initial, "Initial Deposit");
        System.out.println("Account opened: "+accountNumber);

    }

    private static void deposit(Scanner sc, BankService bankService) {
        System.out.print("Account Number: ");
        String accountNumber = sc.nextLine().trim();
        System.out.print("Amount: ");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.deposit(accountNumber, amount, "Deposit");
        System.out.println("Deposited: "+amount);
    }

    private static void withdraw(Scanner sc, BankService bankService) {
        System.out.print("Account Number: ");
        String accountNumber = sc.nextLine().trim();
        System.out.print("Amount: ");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.widthraw(accountNumber, amount, "Withdrawl");
        System.out.println("Withdrawn: "+amount);
    }

    private static void transfer(Scanner sc, BankService bankService) {
        System.out.print("From Account: ");
        String from = sc.nextLine().trim();
        System.out.print("To account: ");
        String to = sc.nextLine().trim();
        System.out.print("Amount: ");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.transfer(from, to, amount, "Transfer");
        System.out.println("Transferred: "+amount);
    }

    private static void statement(Scanner sc, BankService bankService) {
        System.out.print("Account Number: ");
        String account = sc.nextLine().trim();
        bankService.getStatement(account).forEach(t -> {
            System.out.println(t.getTimestamp() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getNote());
        });
    }

    private static void listAccounts(Scanner sc, BankService bankService) {
        bankService.listAccounts().forEach(a -> {
            System.out.println(a.getAccountNumber() + " | " + a.getAccountType() +" | " + a.getBalance());
        });
    }

    private static void searchAccounts(Scanner sc, BankService bankService) {
        System.out.print("Customer name contains: ");
        String q = sc.nextLine().trim();
        bankService.searchAccountsByCustomerName(q).forEach(
                account -> System.out.println(account.getAccountNumber() + " | " + account.getAccountType() + " | " + account.getBalance())
        );
    }
}