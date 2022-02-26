package ru.itmo.banks.transaction;

import ru.itmo.banks.exception.BanksException;

public class TransactionCancellation extends Transaction {
    public TransactionCancellation(Transaction transaction) throws BanksException {
        super(transaction.getId(), transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
        if (transaction.isCanceled()) {
            throw new BanksException("This transaction has already been canceled");
        }

        if (transaction.getSender() != null) {
            transaction.getRecipient().reduceMoney(transaction.getAmount());
            transaction.getSender().increaseMoney(transaction.getAmount());
            return;
        }

        if (transaction.getAmount() > 0) {
            transaction.getRecipient().increaseMoney(transaction.getAmount());
        } else {
            transaction.getRecipient().reduceMoney(transaction.getAmount());
        }

        transaction.cancle();
    }
}
