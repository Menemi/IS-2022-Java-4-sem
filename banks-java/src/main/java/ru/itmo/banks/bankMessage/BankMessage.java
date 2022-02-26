package ru.itmo.banks.bankMessage;

import ru.itmo.banks.account.Account;

public interface BankMessage {
    String messageToClient(Account account, double amount);
}
