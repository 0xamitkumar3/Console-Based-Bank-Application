package service;

import domain.Account;
import domain.Transaction;
import java.util.List;

public interface BankService {
    String openAccount(String name, String email, String accountType);
    List<Account> listAccounts();

    void deposit(String accountNumber, Double amount, String note);

    void widthraw(String accountNumber, Double amount, String withdrawl);

    void transfer(String from, String to, Double amount, String transfer);

    List<Transaction> getStatement(String account);

    List<Account> searchAccountsByCustomerName(String q);
}