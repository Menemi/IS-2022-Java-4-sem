package ru.itmo.banks.Transactions;

import ru.itmo.banks.Accounts.Account;

public class TransactionReplenishment extends Transaction {
    public TransactionReplenishment(Account sender, Account recipient, double amount, int id) {
        super(id, sender, recipient, amount);
        recipient.increaseMoney(amount);
    }
}
