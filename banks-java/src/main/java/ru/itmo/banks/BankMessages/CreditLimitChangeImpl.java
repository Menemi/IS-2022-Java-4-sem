package ru.itmo.banks.BankMessages;

import ru.itmo.banks.Accounts.Account;

public class CreditLimitChangeImpl implements BankMessage {
    @Override
    public String messageToClient(Account account, double amount) {
        return String.format("Your credit limit on account %d was changed to %f", account.id, amount);
    }
}
