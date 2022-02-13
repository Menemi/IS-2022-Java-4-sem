package ru.itmo.banks.BankMessages;

import ru.itmo.banks.Accounts.Account;

public class RemittanceLimitChangeImpl implements BankMessage {
    @Override
    public String messageToClient(Account account, double amount) {
        return String.format("Your remittance limit on account %d was changed to %f. If you are the verified user, " +
                "you can skip this message", account.id, amount);
    }
}
