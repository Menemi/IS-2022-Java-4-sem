package ru.itmo.banks.BankMessages;

import ru.itmo.banks.Accounts.Account;

public class PercentChangeImpl implements BankMessage {
    public String messageToClient(Account account, double amount) {
        return String.format("Your percents on account %d was changed to %f%%", account.id, amount);
    }
}