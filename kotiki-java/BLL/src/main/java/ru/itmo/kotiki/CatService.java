package ru.itmo.kotiki;

import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.dao.CatDaoImpl;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

import java.util.List;

public class CatService {
    private final CatDao catDao = new CatDaoImpl();

    public CatService() {
    }

    public Cat findCat(int id) {
        return catDao.findById(id);
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void updateCat(Cat cat) {
        catDao.update(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public Owner findOwner(int id) {
        return catDao.findOwnerById(id);
    }

    public List<Cat> findAllCats() {
        return catDao.findAll();
    }
}
