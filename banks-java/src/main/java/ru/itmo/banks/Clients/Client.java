package ru.itmo.banks.Clients;

public class Client extends ClientBuilder {
    public void setName(String name) {
        Person.name = name;
    }

    public void setSurname(String surname) {
        Person.surname = surname;
    }

    public void setAddress(String address) {
        Person.address = address;
        if (Person.passport != null) {
            Person.doubtful = false;
        }
    }

    public void setPassport(Passport passport) {
        Person.passport = passport;
        if (Person.address != null) {
            Person.doubtful = false;
        }
    }
}
