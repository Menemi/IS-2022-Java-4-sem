package ru.itmo.banks.bankMessage;

import ru.itmo.banks.account.Account;

public class RemittanceLimitChangeImpl implements BankMessage {
    @Override
    public String messageToClient(Account account, double amount) {
        return String.format("Your remittance limit on account %d was changed to %f. If you are the verified user, " +
                "you can skip this message", account.getId(), amount);
    }
}
