package ru.itmo.kotiki.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cat> pets;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    public Owner() {
    }

    public Owner(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        pets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Cat> getPets() {
        return Collections.unmodifiableList(pets);
    }

    public void addPet(Cat pet) {
        if (pet != null && !pets.contains(pet)) {
            pet.setOwner(this);
            pets.add(pet);
        }
    }

    public void removePet(Cat pet) {
        if (pet != null && pets.contains(pet)) {
            pets.remove(pet);
        }
    }
}
