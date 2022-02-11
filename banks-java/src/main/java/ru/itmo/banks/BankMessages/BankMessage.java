package ru.itmo.banks.BankMessages;

import ru.itmo.banks.Accounts.Account;

public interface BankMessage {
    String messageToClient(Account account, double amount);
}
