package ru.itmo.banks;

import ru.itmo.banks.Banks.CentralBank;

import java.time.LocalDate;

public class TimeMachine {
    public void TimeRewind(CentralBank centralBank, LocalDate dateToRewind) {
        centralBank.notifyObservers(dateToRewind);
    }
}
