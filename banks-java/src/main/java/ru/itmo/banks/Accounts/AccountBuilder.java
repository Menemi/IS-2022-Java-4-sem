package ru.itmo.banks.Accounts;

import java.time.LocalDate;

public class AccountBuilder {
    private final Account account;

    public AccountBuilder(long id) {
        account = new Account(id);
    }

    public AccountBuilder setPercent(double percent) {
        account.percent = percent;
        return this;
    }

    public AccountBuilder setDepositPercent(double percent) {
        account.percent = percent;
        return this;
    }

    public AccountBuilder setStartBalance(double startBalance) {
        account.increaseMoney(startBalance);
        return this;
    }

    public AccountBuilder setMaxWithdraw(double amount) {
        account.maxWithdraw = amount;
        return this;
    }

    public AccountBuilder setMaxRemittance(double amount) {
        account.maxRemittance = amount;
        return this;
    }

    public AccountBuilder setCreditLimit(double creditLimit) {
        account.creditLimit = creditLimit;
        account.increaseMoney(creditLimit);
        return this;
    }

    public AccountBuilder setCommission(double commission) {
        account.commission = commission;
        return this;
    }

    public AccountBuilder setAccountUnblockingPeriod(LocalDate date) {
        account.accountUnblockingPeriod = date;
        return this;
    }

    public Account build() {
        return account;
    }
}
