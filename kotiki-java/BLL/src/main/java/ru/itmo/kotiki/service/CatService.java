package ru.itmo.kotiki.service;

import ru.itmo.kotiki.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {
    Optional<Cat> findCat(long id);
    void saveCat(Cat cat);
    void deleteCat(Cat cat);
    List<Cat> findAllCats();
}
