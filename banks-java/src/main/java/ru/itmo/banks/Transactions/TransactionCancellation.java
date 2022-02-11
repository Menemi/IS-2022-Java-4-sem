package ru.itmo.banks.Transactions;

import ru.itmo.banks.Exceptions.BanksException;

public class TransactionCancellation extends Transaction {
    public TransactionCancellation(Transaction transaction) throws BanksException {
        super(transaction.id, transaction.sender, transaction.recipient, transaction.amount);
        if (transaction.isCanceled()) {
            throw new BanksException("This transaction has already been canceled");
        }

        if (transaction.sender != null) {
            transaction.recipient.reduceMoney(transaction.amount);
            transaction.sender.increaseMoney(transaction.amount);
            return;
        }

        if (transaction.amount > 0) {
            transaction.recipient.increaseMoney(transaction.amount);
        } else {
            transaction.recipient.reduceMoney(transaction.amount);
        }

        transaction.cancle();
    }
}
