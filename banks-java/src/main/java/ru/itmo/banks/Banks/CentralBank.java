package ru.itmo.banks.Banks;

import ru.itmo.banks.PercentOfTheAmount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CentralBank {
    private static CentralBank instance;
    private ArrayList<Bank> banks;

    protected CentralBank(String cBankName) {
        name = cBankName;
        banks = new ArrayList<Bank>();
    }

    public String name;

    public String getName() {
        return name;
    }

    public static CentralBank getInstance(String name) {
        if (instance == null) {
            instance = new CentralBank(name);
        }

        return instance;
    }

    public Bank createBank(
            String bankName,
            ArrayList<PercentOfTheAmount> percentsOfTheAmount,
            double fixedPercent,
            double maxWithdrawAmount,
            double maxRemittanceAmount,
            double creditLimit,
            double commission,
            LocalDate accountUnblockingPeriod) {
        var bank = new Bank(
                bankName,
                percentsOfTheAmount,
                fixedPercent,
                maxWithdrawAmount,
                maxRemittanceAmount,
                creditLimit,
                commission,
                accountUnblockingPeriod);
        banks.add(bank);
        return bank;
    }

    public Collection<Bank> getBanks()
    {
        return Collections.unmodifiableList(banks);
    }

    public void notifyObservers(LocalDate date)
    {
        for (Bank bank : banks)
        {
            bank.balanceUpdate(date);
        }
    }
}
