package ru.itmo.banks.Clients;

public class Client extends ClientBuilder {
    @Override
    public void setName(String name) {
        Person.name = name;
    }

    @Override
    public void setSurname(String surname) {
        Person.surname = surname;
    }

    @Override
    public void setAddress(String address) {
        Person.address = address;
        if (Person.passport != null) {
            Person.doubtful = false;
        }
    }

    @Override
    public void setPassport(Passport passport) {
        Person.passport = passport;
        if (Person.address != null) {
            Person.doubtful = false;
        }
    }
}
