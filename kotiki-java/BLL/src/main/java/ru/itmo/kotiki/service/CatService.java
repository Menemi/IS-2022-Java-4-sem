package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.model.Cat;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    @Autowired
    private CatDao catDao;

    public Optional<Cat> findCat(long id) {
        return catDao.findById(id);
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<Cat> findAllCats() {
        return catDao.findAll();
    }
}
