package ru.itmo.banks.transaction;

import ru.itmo.banks.account.Account;

import java.time.LocalDate;

public abstract class Transaction {
    private boolean statusCanceled;

    protected int id;
    protected double amount;
    protected Account sender;
    protected Account recipient;
    protected LocalDate transactionTime;

    public Transaction(int id, Account sender, Account recipient, double amount) {
        this.id = id;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
        transactionTime = LocalDate.now();
        statusCanceled = false;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Account getSender() {
        return sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public LocalDate getTransactionTime() {
        return transactionTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCanceled() {
        return statusCanceled;
    }

    public void cancle() {
        statusCanceled = true;
    }
}
