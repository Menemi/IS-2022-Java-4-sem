package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.model.Breed;
import ru.itmo.kotiki.model.Color;

import java.sql.Date;

public class CatDto {
    private String name;
    private Date birthDate;
    private Breed breed;
    private Color color;

    public CatDto() {
    }

    public CatDto(String name, Date birthDate, Breed breed, Color color) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatDto that = (CatDto) o;

        if (birthDate != that.birthDate || breed != that.breed || color != that.color) {
            return false;
        }

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + birthDate.getDay();
        result = prime * result + ((breed == null) ? 0 : breed.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CatBean{" +
                "name='" + name + '\'' +
                ", birth date=" + birthDate +
                ", breed=" + breed +
                ", color=" + color +
                '}';
    }
}
