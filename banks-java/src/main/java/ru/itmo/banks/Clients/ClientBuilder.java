package ru.itmo.banks.Clients;

public abstract class ClientBuilder {
    protected Person Person;

    public Person createNewClient(String name, String surname) {
        Person = new Person(name, surname);
        return Person;
    }

    public abstract void setName(String name);

    public abstract void setSurname(String surname);

    public abstract void setAddress(String address);

    public abstract void setPassport(Passport passport);

    public Person getPerson() {
        return Person;
    }

    private void setPerson(Person person) {
        Person = person;
    }
}
