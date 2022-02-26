package ru.itmo.banks.transaction;

import ru.itmo.banks.account.Account;

public class TransactionReplenishment extends Transaction {
    public TransactionReplenishment(Account sender, Account recipient, double amount, int id) {
        super(id, sender, recipient, amount);
        recipient.increaseMoney(amount);
    }
}
