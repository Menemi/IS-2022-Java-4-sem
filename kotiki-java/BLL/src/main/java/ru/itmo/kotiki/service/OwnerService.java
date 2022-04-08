package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerDao ownerDao;

    public Optional<Owner> findOwner(long id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public void removeCat(Owner owner, Cat cat) {
        owner.removePet(cat);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }
}
