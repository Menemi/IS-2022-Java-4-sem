package ru.itmo.banks.Transactions;

import ru.itmo.banks.Accounts.Account;
import ru.itmo.banks.Exceptions.BanksException;

public class TransactionRemittance extends Transaction {
    public TransactionRemittance(Account sender, Account recipient, double transactionAmount, int id)
            throws BanksException {
        super(id, sender, recipient, transactionAmount);
        if (sender.getMaxRemittance() != -1 && transactionAmount > sender.getMaxRemittance()) {
            throw new BanksException(String.format("Your profile is doubtful you can't transfer more than %f",
                    sender.getMaxRemittance()));
        }

        if (sender.getBalance() < transactionAmount) {
            throw new BanksException(String.format("You have not enough money (%f)", sender.getBalance()));
        }

        sender.reduceMoney(transactionAmount);
        recipient.increaseMoney(transactionAmount);
    }
}
