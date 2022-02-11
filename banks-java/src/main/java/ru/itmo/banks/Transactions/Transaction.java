package ru.itmo.banks.Transactions;

import ru.itmo.banks.Accounts.Account;

import java.time.LocalDate;

public abstract class Transaction {
    private boolean statusCanceled;

    public Transaction(int id, Account sender, Account recipient, double amount)
    {
        this.id = id;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
        transactionTime = LocalDate.now();
        statusCanceled = false;
    }

    public int id;
    public double amount;
    public Account sender;
    public Account recipient;
    public LocalDate transactionTime;

    public boolean isCanceled()
    {
        return statusCanceled;
    }

    public void cancle()
    {
        statusCanceled = true;
    }
}
