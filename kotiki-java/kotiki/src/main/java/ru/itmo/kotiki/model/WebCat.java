package ru.itmo.kotiki.model;


import java.sql.Date;

public class WebCat {
    private final String name;
    private final Date birthDate;
    private final Breed breed;
    private final Color color;

    public WebCat(String name, Date birthDate, Breed breed, Color color) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Breed getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }
}
