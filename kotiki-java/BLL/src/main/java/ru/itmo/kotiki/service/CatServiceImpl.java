package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.dao.UserDao;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatServiceImpl implements CatService {
    private final CatDao catDao;
    private final UserDao userDao;

    @Autowired
    public CatServiceImpl(CatDao catDao, UserDao userDao) {
        this.catDao = catDao;
        this.userDao = userDao;
    }

    public Cat findCat(int id) {
        Cat cat = catDao.getById(id);
        var user = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.equals(cat.getOwner(), null)) {
            if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN)) {
                return cat;
            }

            return new Cat();
        }

        User owner = userDao.getById(cat.getOwner().getId());

        if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN) ||
                Objects.equals(owner.getUsername(), user.getName())) {
            return cat;
        }

        return new Cat();
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<Cat> findAllCats() {
        List<Cat> cats = new ArrayList<>();
        var user = SecurityContextHolder.getContext().getAuthentication();
        for (Cat cat : catDao.findAll()) {
            if (Objects.equals(cat.getOwner(), null)) {
                if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN)) {
                    cats.add(cat);
                }

                continue;
            }

            User owner = userDao.getById(cat.getOwner().getId());

            if (Objects.equals(userDao.findUserByUsername(user.getName()).getRole(), Role.ROLE_ADMIN) ||
                    Objects.equals(owner.getUsername(), user.getName())) {
                cats.add(cat);
            }
        }

        return cats;
    }
}
