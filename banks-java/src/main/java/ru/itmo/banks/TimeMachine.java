package ru.itmo.banks;

import ru.itmo.banks.bank.CentralBank;

import java.time.LocalDate;

public class TimeMachine {
    public void timeRewind(CentralBank centralBank, LocalDate dateToRewind) {
        centralBank.notifyObservers(dateToRewind);
    }
}
