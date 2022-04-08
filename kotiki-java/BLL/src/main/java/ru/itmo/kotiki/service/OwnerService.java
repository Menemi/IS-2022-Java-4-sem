package ru.itmo.kotiki.service;

import ru.itmo.kotiki.model.Owner;

import java.util.List;

public interface OwnerService {
    public void saveOwner(Owner owner);
    public void deleteOwner(Owner owner);
    public List<Owner> findAllOwners();
}
