package ru.itmo.banks.clients;

import ru.itmo.banks.account.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Person {
    private final List<Account> accountsList;

    private String name;
    private String surname;
    private String address;
    private Passport passport;
    private boolean doubtful;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setDoubtful(boolean doubtful) {
        this.doubtful = doubtful;
    }

    public void addNewAccount(Account account) {
        accountsList.add(account);
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableList(accountsList);
    }
}
