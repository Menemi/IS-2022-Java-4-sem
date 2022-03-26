package ru.itmo.kotiki.dao;

import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

import java.util.List;

public interface OwnerDao {
    public Owner findById(int id);

    public void save(Owner owner);

    public void update(Owner owner);

    public void delete(Owner owner);

    public Cat findCatById(int id);

    public List<Owner> findAll();
}
