package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.model.Owner;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan({"ru.itmo.kotiki.dao"})
public class OwnerService {
    private final OwnerDao ownerDao;

    @Autowired
    public OwnerService(OwnerDao ownerDao){
        this.ownerDao = ownerDao;
    }

    public Optional<Owner> findOwner(long id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }
}
