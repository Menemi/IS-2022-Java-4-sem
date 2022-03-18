package ru.itmo.kotiki;

import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.dao.OwnerDaoImpl;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

import java.util.List;

public class OwnerService {
    private final OwnerDao ownerDao = new OwnerDaoImpl();

    public OwnerService() {
    }

    public Owner findOwner(int id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public Cat findCat(int id) {
        return ownerDao.findCatById(id);
    }

    public void removeCat(Owner owner, Cat cat) {
        owner.removePet(cat);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }
}
