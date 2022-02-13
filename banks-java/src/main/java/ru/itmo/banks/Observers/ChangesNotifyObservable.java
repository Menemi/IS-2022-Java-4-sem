package ru.itmo.banks.Observers;

import ru.itmo.banks.Accounts.Account;
import ru.itmo.banks.BankMessages.BankMessage;
import ru.itmo.banks.Exceptions.BanksException;

import java.util.Map;

public interface ChangesNotifyObservable {
    void registerObserver(Account account, ChangesNotifyObserver accountsObserver) throws BanksException;

    void removeObserver(Account account, ChangesNotifyObserver accountsObserver) throws BanksException;

    void notifyObservers(Map<Account, ChangesNotifyObserver> observers, double amount, BankMessage message);
}
