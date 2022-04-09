package ru.itmo.kotiki.service;

import ru.itmo.kotiki.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner findOwner(int id);
    void saveOwner(Owner owner);
    void deleteOwner(Owner owner);
    List<Owner> findAllOwners();
}
