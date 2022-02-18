package ru.itmo.banks.Banks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.itmo.banks.Accounts.Account;
import ru.itmo.banks.Clients.Client;
import ru.itmo.banks.Clients.ClientBuilder;
import ru.itmo.banks.Clients.Passport;
import ru.itmo.banks.Clients.Person;
import ru.itmo.banks.Exceptions.BanksException;
import ru.itmo.banks.PercentOfTheAmount;
import ru.itmo.banks.TimeMachine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BanksTest {
    private final TimeMachine timeMachine = new TimeMachine();
    private final CentralBank centralBank = CentralBank.getInstance("Central Bank");

    @Test
    public void WithdrawAndRemittanceLimitTest() throws BanksException {
        List<PercentOfTheAmount> depositPercentsList = new ArrayList<>();
        depositPercentsList.add(new PercentOfTheAmount(0, 50000, 3));
        depositPercentsList.add(new PercentOfTheAmount(50000.01, 100000, 6));
        depositPercentsList.add(new PercentOfTheAmount(100000.01, Float.MAX_VALUE, 9));

        Bank tinkoff = centralBank.createBank(
                "Tinkoff",
                depositPercentsList,
                3,
                10000,
                10000,
                10000,
                1000,
                LocalDate.of(2022, 1, 1));

        ClientBuilder clientBuilder = new Client();

        Person putin = clientBuilder.createNewClient("Vladimir", "Putin");
        clientBuilder.setAddress("ул. Ильинка, д. 23, 103132, Москва, Россия");
        clientBuilder.setPassport(new Passport(7777, 777777));
        clientBuilder = new Client();
        Person biden = clientBuilder.createNewClient("Joe", "Biden");
        clientBuilder.setPassport(new Passport(6666, 666666));

        Account putinDebit = tinkoff.сreateDebitAccount(putin, 100000);
        Account bidenDebit = tinkoff.сreateDebitAccount(biden, 200000);

        tinkoff.remittance(bidenDebit, putinDebit, 100);

        assertEquals(100100, putinDebit.getBalance());

        assertThrows(BanksException.class, () -> {
            tinkoff.withdraw(bidenDebit, 30000);
            tinkoff.remittance(bidenDebit, putinDebit, 30000);
        });
    }

    @Test
    public void TimeMachineTest() throws BanksException {

        List<PercentOfTheAmount> depositPercentsList = new ArrayList<>();
        depositPercentsList.add(new PercentOfTheAmount(0, 50000, 3));
        depositPercentsList.add(new PercentOfTheAmount(50000.01, 100000, 6));
        depositPercentsList.add(new PercentOfTheAmount(100000.01, Float.MAX_VALUE, 9));

        Bank tinkoff = centralBank.createBank(
                "Tinkoff",
                depositPercentsList,
                3,
                10000,
                10000,
                10000,
                1000,
                LocalDate.of(2022, 1, 1));

        ClientBuilder clientBuilder = new Client();

        Person putin = clientBuilder.createNewClient("Vladimir", "Putin");
        clientBuilder.setAddress("ул. Ильинка, д. 23, 103132, Москва, Россия");
        clientBuilder.setPassport(new Passport(7777, 777777));
        clientBuilder = new Client();
        Person biden = clientBuilder.createNewClient("Joe", "Biden");
        clientBuilder.setPassport(new Passport(6666, 666666));

        Account putinDebit = tinkoff.сreateDebitAccount(putin, 100000);
        Account bidenDebit = tinkoff.сreateDebitAccount(biden, 200000);
        Account putinCredit = tinkoff.сreateCreditAccount(putin, 100000);
        Account putinDeposit = tinkoff.сreateDepositAccount(putin, 100000);
        Account bidenDeposit = tinkoff.сreateDepositAccount(biden, 10000);

        LocalDate dateToRewind = LocalDate.of(2022, 3, 1);
        timeMachine.TimeRewind(centralBank, dateToRewind);

        assertEquals(putin.getAccounts().size(), 3);
        assertEquals(100190, putinDebit.getBalance());
        assertEquals(200380, bidenDebit.getBalance());
        assertEquals(110000, putinCredit.getBalance());
        assertEquals(100380, putinDeposit.getBalance());
        assertEquals(10019, bidenDeposit.getBalance());
    }

    @Test
    public void NotifiesTest() throws BanksException {
        List<PercentOfTheAmount> depositPercentsList = new ArrayList<>();
        depositPercentsList.add(new PercentOfTheAmount(0, 50000, 3));
        depositPercentsList.add(new PercentOfTheAmount(50000.01, 100000, 6));
        depositPercentsList.add(new PercentOfTheAmount(100000.01, Float.MAX_VALUE, 9));

        Bank tinkoff = centralBank.createBank(
                "Tinkoff",
                depositPercentsList,
                3,
                10000,
                10000,
                10000,
                1000,
                LocalDate.of(2022, 1, 1));

        ClientBuilder clientBuilder = new Client();

        Person putin = clientBuilder.createNewClient("Vladimir", "Putin");
        clientBuilder.setAddress("ул. Ильинка, д. 23, 103132, Москва, Россия");
        clientBuilder.setPassport(new Passport(7777, 777777));
        clientBuilder = new Client();
        Person biden = clientBuilder.createNewClient("Joe", "Biden");
        clientBuilder.setPassport(new Passport(6666, 666666));

        Account putinDebit = tinkoff.сreateDebitAccount(putin, 100000);
        Account bidenDebit = tinkoff.сreateDebitAccount(biden, 200000);
        Account putinCredit = tinkoff.сreateCreditAccount(putin, 100000);
        Account putinDeposit = tinkoff.сreateDepositAccount(putin, 100000);
        Account bidenDeposit = tinkoff.сreateDepositAccount(biden, 10000);

        tinkoff.registerObserver(putinDebit, putinDebit);
        tinkoff.registerObserver(putinCredit, putinCredit);
        tinkoff.registerObserver(bidenDeposit, bidenDeposit);

        tinkoff.setFixedPercent(3.5);
        tinkoff.setCreditLimit(11000);
        tinkoff.setMaxRemittanceAmount(11000);

        assertEquals(5, tinkoff.getClientsAccounts().size());
        assertEquals(3, tinkoff.getNotifiedAccounts().size());
        assertEquals(3.5, putinDebit.getPercent());
        assertEquals(11000, putinDebit.getMaxRemittance());
        assertEquals(2, putinDebit.getBankMessageList().size());
        assertEquals(3.5, bidenDebit.getPercent());
        assertEquals(0, bidenDebit.getBankMessageList().size());
    }
}
