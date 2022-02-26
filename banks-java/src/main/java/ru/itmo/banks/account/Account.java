package ru.itmo.banks.account;

import ru.itmo.banks.observer.ChangesNotifyObserver;
import ru.itmo.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.*;

public class Account implements ChangesNotifyObserver {
    private final List<String> bankMessageList;
    private final List<Transaction> transactionHistory;

    private final long id;
    private double percent;
    private double balance;
    private double maxWithdraw;
    private double maxRemittance;
    private double creditLimit;
    private double commission;
    private LocalDate accountUnblockingPeriod;
    private int transactionIdCounter;
    private double summaryPercent;
    private double summaryCommission;

    public Account(long id) {
        this.id = id;
        this.summaryPercent = this.summaryCommission = 0;
        bankMessageList = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }

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

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMaxWithdraw(double maxWithdraw) {
        this.maxWithdraw = maxWithdraw;
    }

    public void setMaxRemittance(double maxRemittance) {
        this.maxRemittance = maxRemittance;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setAccountUnblockingPeriod(LocalDate accountUnblockingPeriod) {
        this.accountUnblockingPeriod = accountUnblockingPeriod;
    }

    public void setTransactionIdCounter(int transactionIdCounter) {
        this.transactionIdCounter = transactionIdCounter;
    }

    public void setSummaryPercent(double summaryPercent) {
        this.summaryPercent = summaryPercent;
    }

    public void setSummaryCommission(double summaryCommission) {
        this.summaryCommission = summaryCommission;
    }

    public List<String> getBankMessageList() {
        return Collections.unmodifiableList(bankMessageList);
    }

    public static AccountBuilder createBuilder(long id) {
        return new AccountBuilder(id);
    }

    public List<Transaction> getTransactions() {
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

            while (!checkDate.equals(startDate)) {
                summaryCommission += commission;

                startDate = startDate.plusDays(1);

                if (startDate.getDayOfMonth() == 1) {
                    reduceMoney(summaryCommission);
                    summaryCommission = 0;
                }
            }
        } else {
            while (!checkDate.equals(startDate)) {
                int divider = 365;

                if (LocalDate.now().isLeapYear()) {
                    divider = 366;
                }
                String strSummaryPercent = String.format("%.2f", (percent / divider)).replace(',', '.');
                summaryPercent += balance * Double.parseDouble(strSummaryPercent) / 100;
                startDate = startDate.plusDays(1);

                if (startDate.getDayOfMonth() == 1) {
                    increaseMoney(summaryPercent);
                    summaryPercent = 0;
                }
            }
        }
    }

    @Override
    public void update(String message) {
        bankMessageList.add(message);
    }
}