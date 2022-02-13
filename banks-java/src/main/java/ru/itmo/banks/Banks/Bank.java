package ru.itmo.banks.Banks;

import ru.itmo.banks.Accounts.Account;
import ru.itmo.banks.BankMessages.BankMessage;
import ru.itmo.banks.BankMessages.CreditLimitChangeImpl;
import ru.itmo.banks.BankMessages.PercentChangeImpl;
import ru.itmo.banks.BankMessages.RemittanceLimitChangeImpl;
import ru.itmo.banks.Clients.Person;
import ru.itmo.banks.Exceptions.BanksException;
import ru.itmo.banks.Observers.ChangesNotifyObservable;
import ru.itmo.banks.Observers.ChangesNotifyObserver;
import ru.itmo.banks.PercentOfTheAmount;
import ru.itmo.banks.Transactions.*;

import java.time.LocalDate;
import java.util.*;

public class Bank implements ChangesNotifyObservable {
    private static int bankIdCounter = 1;
    private static long accountIdCounter = 1000000000;
    private ArrayList<Account> clientsAccounts;
    private HashMap<Account, ChangesNotifyObserver> clientsAccountObservers;
    private ArrayList<PercentOfTheAmount> percentsOfTheAmount;
    private double fixedPercent;
    private double maxWithdrawAmount;
    private double maxRemittanceAmount;
    private double creditLimit;
    private double commission;
    private LocalDate accountUnblockingPeriod;

    public Bank(
            String bankName,
            ArrayList<PercentOfTheAmount> percentsOfTheAmount,
            double fixedPercent,
            double maxWithdrawAmount,
            double maxRemittanceAmount,
            double creditLimit,
            double commission,
            LocalDate accountUnblockingPeriod) {
        id = bankIdCounter++;
        name = bankName;
        this.percentsOfTheAmount = percentsOfTheAmount;
        clientsAccountObservers = new HashMap<Account, ChangesNotifyObserver>();
        clientsAccounts = new ArrayList<Account>();
        this.fixedPercent = fixedPercent;
        this.maxWithdrawAmount = maxWithdrawAmount;
        this.maxRemittanceAmount = maxRemittanceAmount;
        this.creditLimit = creditLimit;
        this.commission = commission;
        this.accountUnblockingPeriod = accountUnblockingPeriod;
    }

    public int id;

    public String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getClientsAccounts() {
        return Collections.unmodifiableList(clientsAccounts);
    }

    public Map<Account, ChangesNotifyObserver> getNotifiedAccounts() {
        return Collections.unmodifiableMap(clientsAccountObservers);
    }

    public Account сreateCreditAccount(Person person, double startBalance) {
        var account = Account.createBuilder(accountIdCounter++)
                .setStartBalance(startBalance)
                .setMaxWithdraw(maxWithdrawAmount)
                .setMaxRemittance(maxRemittanceAmount)
                .setCreditLimit(creditLimit)
                .setCommission(commission)
                .build();

        clientsAccounts.add(account);
        person.addNewAccount(account);

        return account;
    }

    public Account сreateDebitAccount(Person person, double startBalance) {
        var account = Account.createBuilder(accountIdCounter++)
                .setPercent(fixedPercent)
                .setStartBalance(startBalance)
                .setMaxWithdraw(maxWithdrawAmount)
                .setMaxRemittance(maxRemittanceAmount)
                .build();

        clientsAccounts.add(account);
        person.addNewAccount(account);

        return account;
    }

    public Account сreateDepositAccount(Person person, double startBalance) throws BanksException {
        var account = Account.createBuilder(accountIdCounter++)
                .setPercent(fixedPercent)
                .setDepositPercent(updateDepositPercent(startBalance))
                .setStartBalance(startBalance)
                .setMaxWithdraw(maxWithdrawAmount)
                .setMaxRemittance(maxRemittanceAmount)
                .setAccountUnblockingPeriod(accountUnblockingPeriod)
                .build();

        clientsAccounts.add(account);
        person.addNewAccount(account);

        return account;
    }

    public void setCreditLimit(double amount) {
        var oldCreditLimit = creditLimit;
        creditLimit = amount;
        HashMap<Account, ChangesNotifyObserver> observersList = new HashMap<Account, ChangesNotifyObserver>();
        ArrayList<Account> creditAccounts = new ArrayList<Account>();
        for (Account account : clientsAccounts) {
            if (account.creditLimit != 0) {
                creditAccounts.add(account);
            }
        }

        for (Account account : creditAccounts) {
            account.reduceMoney(oldCreditLimit);
            account.increaseMoney(creditLimit);
            account.creditLimit = creditLimit;
        }

        for (Account observer : clientsAccountObservers.keySet()) {
            if (observer.creditLimit != 0) {
                observersList.put(observer, clientsAccountObservers.get(observer));
            }
        }

        BankMessage message = new CreditLimitChangeImpl();
        notifyObservers(observersList, creditLimit, message);
    }

    public void setFixedPercent(double amount) {
        fixedPercent = amount;
        HashMap<Account, ChangesNotifyObserver> observersList = new HashMap<Account, ChangesNotifyObserver>();
        ArrayList<Account> debitOrDepositAccounts = new ArrayList<Account>();
        for (Account account : clientsAccounts) {
            if (account.percent != 0) {
                account.percent = fixedPercent;
            }
        }

        for (Account observer : clientsAccountObservers.keySet()) {
            if (observer.percent != 0) {
                observersList.put(observer, clientsAccountObservers.get(observer));
            }
        }

        BankMessage message = new PercentChangeImpl();
        notifyObservers(observersList, fixedPercent, message);
    }

    public void setMaxRemittanceAmount(double amount) {
        maxRemittanceAmount = amount;
        BankMessage message = new RemittanceLimitChangeImpl();
        for (Account account : clientsAccounts) {
            account.maxRemittance = maxRemittanceAmount;
        }

        notifyObservers(clientsAccountObservers, maxRemittanceAmount, message);
    }

    public void replenishment(Account account, double amount) throws BanksException {
        var transaction = new TransactionReplenishment(null, account, amount, account.transactionIdCounter++);
        account.newTransaction(transaction);

        if (account.accountUnblockingPeriod != LocalDate.MIN) {
            updateDepositPercent(account.balance);
        }
    }

    public void withdraw(Account account, double amount) throws BanksException {
        if (account.accountUnblockingPeriod.isAfter(LocalDate.now())) {
            throw new BanksException(String.format("Unblocking period (%s) did not come, you can't withdraw any money",
                    account.accountUnblockingPeriod));
        }

        var transaction = new TransactionWithdraw(null, account, amount, account.transactionIdCounter++);
        account.newTransaction(transaction);

        if (account.accountUnblockingPeriod != LocalDate.MIN) {
            updateDepositPercent(account.balance);
        }
    }

    public void remittance(Account sender, Account recipient, double amount)
            throws BanksException {
        if (sender.accountUnblockingPeriod.isAfter(LocalDate.now())) {
            throw new BanksException(String.format("Unblocking period (%s) did not come, you can't transfer any money",
                    sender.accountUnblockingPeriod));
        }

        var transaction = new TransactionRemittance(sender, recipient, amount, sender.transactionIdCounter++);
        sender.newTransaction(transaction);

        if (sender.accountUnblockingPeriod != LocalDate.MIN) {
            updateDepositPercent(sender.balance);
        }

        if (recipient.accountUnblockingPeriod != LocalDate.MIN) {
            updateDepositPercent(recipient.balance);
        }
    }

    public void cancellation(Account account, Transaction oldTransaction) throws BanksException {
        var transaction = new TransactionCancellation(oldTransaction);
        account.newTransaction(transaction);

        if (account.accountUnblockingPeriod != LocalDate.MIN) {
            updateDepositPercent(account.balance);
        }
    }

    public void balanceUpdate(LocalDate date) {
        for (Account observer : clientsAccounts) {
            observer.anyBalanceTimeChange(date);
        }
    }

    public void registerObserver(Account account, ChangesNotifyObserver accountsObserver) throws BanksException {
        for (ChangesNotifyObserver accountObserver : clientsAccountObservers.values()) {
            if (accountObserver == accountsObserver) {
                throw new BanksException("Account has already been added to notified accounts list");
            }
        }

        clientsAccountObservers.put(account, accountsObserver);
    }

    public void removeObserver(Account account, ChangesNotifyObserver accountsObserver) throws BanksException {
        for (ChangesNotifyObserver accountObserver : clientsAccountObservers.values()) {
            if (accountObserver == accountsObserver) {
                clientsAccountObservers.remove(account);
                return;
            }
        }

        throw new BanksException("Account has already been added to notified accounts list");
    }

    public void notifyObservers(HashMap<Account, ChangesNotifyObserver> observers, double amount, BankMessage message) {
        for (Account observer : observers.keySet()) {
            observer.update(message.messageToClient(observer, amount));
        }
    }

    private double updateDepositPercent(double balance) throws BanksException {
        for (PercentOfTheAmount percent : percentsOfTheAmount) {
            if (percent.lowerBound <= balance && percent.upperBound >= balance) {
                return percent.percent;
            }
        }

        throw new BanksException("Bank error, try again later");
    }
}