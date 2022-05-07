package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.dao.UserDao;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;

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
        User user = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.getRole() == Role.ROLE_ADMIN) {
            return catDao.getById(id);
        }

        for (Cat cat : catDao.findCatsByOwnerId(user.getOwner().getId())) {
            if (Objects.equals(cat.getId(), id)) {
                return cat;
            }
        }

        return null;
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<Cat> findAllCats() {
        User user = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.getRole() == Role.ROLE_ADMIN) {
            return catDao.findAll();
        }

        return catDao.findCatsByOwnerId(user.getOwner().getId());
    }
}
