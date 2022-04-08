package ru.itmo.kotiki.model;


import java.sql.Date;

public class WebOwner {
    private final String name;
    private final Date birthDate;

    public WebOwner(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
