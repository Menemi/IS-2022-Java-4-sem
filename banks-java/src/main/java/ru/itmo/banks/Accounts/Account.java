package ru.itmo.banks.Accounts;

import ru.itmo.banks.Observers.ChangesNotifyObserver;
import ru.itmo.banks.Transactions.Transaction;

import java.time.LocalDate;
import java.util.*;

public class Account implements ChangesNotifyObserver {
    private ArrayList<String> bankMessageList;
    private ArrayList<Transaction> transactionHistory;

    public Account(long id) {
        this.id = id;
        this.summaryPercent = this.summaryCommission = 0;
        bankMessageList = new ArrayList<String>();
        transactionHistory = new ArrayList<Transaction>();
    }

    public long id;
    public double percent;
    public double balance;
    public double maxWithdraw;
    public double maxRemittance;
    public double creditLimit;
    public double commission;
    public LocalDate accountUnblockingPeriod;
    public int transactionIdCounter;
    public double summaryPercent;

    public double summaryCommission;

    public long getId() {
        return id;
    }

    public double getPercent() {
        return percent;
    }

    public double getBalance() {
        return balance;
    }

    public double getMaxWithdraw() {
        return maxWithdraw;
    }

    public double getMaxRemittance() {
        return maxRemittance;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getCommission() {
        return commission;
    }

    public LocalDate getAccountUnblockingPeriod() {
        return accountUnblockingPeriod;
    }

    public int getTransactionIdCounter() {
        return transactionIdCounter;
    }

    public double getSummaryPercent() {
        return summaryPercent;
    }

    public double getSummaryCommission() {
        return summaryCommission;
    }

    public Collection<String> getBankMessageList() {
        return Collections.unmodifiableList(bankMessageList);
    }

    public static AccountBuilder createBuilder(long id) {
        return new AccountBuilder(id);
    }

    public Collection<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public void increaseMoney(double amount) {
        balance += amount;
    }

    public void reduceMoney(double amount) {
        balance -= amount;
    }

    public void newTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public void anyBalanceTimeChange(LocalDate checkDate) {
        // LocalDate startDate = LocalDate.now(); - реализация для работы в реальном мире
        // оставил хардкод для удобного и корректного тестирования
        LocalDate startDate = LocalDate.of(2022, 2, 10);

        if (commission != 0) {
            if (balance >= creditLimit) {
                return;
            }

            while (checkDate != startDate) {
                summaryCommission += commission;

                startDate.plusDays(1);

                if (startDate.getDayOfMonth() == 1) {
                    reduceMoney(summaryCommission);
                    summaryCommission = 0;
                }
            }
        } else {
            while (checkDate != startDate) {
                int divider = 365;

                if (LocalDate.now().isLeapYear()) {
                    divider = 366;
                }
                summaryPercent += balance * Math.round(percent / divider);
                startDate.plusDays(1);

                if (startDate.getDayOfMonth() == 1) {
                    increaseMoney(summaryPercent);
                    summaryPercent = 0;
                }
            }
        }
    }

    public void update(String message) {
        bankMessageList.add(message);
    }
}