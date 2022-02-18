package ru.itmo.banks.Clients;

import ru.itmo.banks.Accounts.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Person {
    private final List<Account> accountsList;

    protected String name;
    protected String surname;
    protected String address;
    protected Passport passport;
    protected boolean doubtful;

    public Person(String name, String surname) {
        accountsList = new ArrayList<>();
        this.name = name;
        this.surname = surname;
        address = null;
        passport = null;
        doubtful = true;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Passport getPassport() {
        return passport;
    }

    public boolean isDoubtful() {
        return doubtful;
    }

    public void addNewAccount(Account account) {
        accountsList.add(account);
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableList(accountsList);
    }
}
