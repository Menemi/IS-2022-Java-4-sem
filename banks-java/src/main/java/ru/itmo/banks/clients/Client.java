package ru.itmo.banks.clients;

public class Client extends ClientBuilder {
    @Override
    public void setName(String name) {
        getPerson().setName(name);
    }

    @Override
    public void setSurname(String surname) {
        getPerson().setSurname(surname);
    }

    @Override
    public void setAddress(String address) {
        getPerson().setAddress(address);
        if (getPerson().getPassport() != null) {
            getPerson().setDoubtful(false);
        }
    }

    @Override
    public void setPassport(Passport passport) {
        getPerson().setPassport(passport);
        if (getPerson().getAddress() != null) {
            getPerson().setDoubtful(false);
        }
    }
}
