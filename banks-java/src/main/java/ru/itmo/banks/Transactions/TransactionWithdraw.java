package ru.itmo.banks.Transactions;

import ru.itmo.banks.Accounts.Account;
import ru.itmo.banks.Exceptions.BanksException;

public class TransactionWithdraw extends Transaction {
    public TransactionWithdraw(Account sender, Account recipient, double amount, int id) throws BanksException {
        super(id, sender, recipient, amount);
        if (recipient.getMaxWithdraw() != -1 && amount > recipient.getMaxWithdraw()) {
            throw new BanksException(String.format("Your profile is doubtful you can't withdraw more than %f",
                    recipient.getMaxWithdraw()));
        }

        if (recipient.getBalance() + recipient.getCreditLimit() < amount) {
            throw new BanksException(String.format("You have not enough money (balance: %f; credit limit: %f)",
                    recipient.getBalance(), recipient.getCreditLimit()));
        }

        recipient.reduceMoney(amount);
    }
}
