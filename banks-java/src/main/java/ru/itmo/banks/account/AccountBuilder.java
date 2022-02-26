package ru.itmo.banks.account;

import java.time.LocalDate;

public class AccountBuilder {
    private final Account account;

    public AccountBuilder(long id) {
        account = new Account(id);
    }

    public AccountBuilder setPercent(double percent) {
        account.setPercent(percent);
        return this;
    }

    public AccountBuilder setDepositPercent(double percent) {
        account.setPercent(percent);
        return this;
    }

    public AccountBuilder setStartBalance(double startBalance) {
        account.increaseMoney(startBalance);
        return this;
    }

    public AccountBuilder setMaxWithdraw(double amount) {
        account.setMaxWithdraw(amount);
        return this;
    }

    public AccountBuilder setMaxRemittance(double amount) {
        account.setMaxRemittance(amount);
        return this;
    }

    public AccountBuilder setCreditLimit(double creditLimit) {
        account.setCreditLimit(creditLimit);
        account.increaseMoney(creditLimit);
        return this;
    }

    public AccountBuilder setCommission(double commission) {
        account.setCommission(commission);
        return this;
    }

    public AccountBuilder setAccountUnblockingPeriod(LocalDate date) {
        account.setAccountUnblockingPeriod(date);
        return this;
    }

    public Account build() {
        return account;
    }
}
