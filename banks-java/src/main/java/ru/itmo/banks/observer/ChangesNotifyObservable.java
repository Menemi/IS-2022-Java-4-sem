package ru.itmo.banks.observer;

import ru.itmo.banks.account.Account;
import ru.itmo.banks.bankMessage.BankMessage;
import ru.itmo.banks.exception.BanksException;

import java.util.Map;

public interface ChangesNotifyObservable {
    void registerObserver(Account account, ChangesNotifyObserver accountsObserver) throws BanksException;

    void removeObserver(Account account, ChangesNotifyObserver accountsObserver) throws BanksException;

    void notifyObservers(Map<Account, ChangesNotifyObserver> observers, double amount, BankMessage message);
}
