package ru.itmo.banks.bankMessage;

import ru.itmo.banks.account.Account;

public class CreditLimitChangeImpl implements BankMessage {
    @Override
    public String messageToClient(Account account, double amount) {
        return String.format("Your credit limit on account %d was changed to %f", account.getId(), amount);
    }
}
