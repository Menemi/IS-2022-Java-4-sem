package ru.itmo.kotiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.repository.CatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

//    public CatService(CatRepository catRepository) {
//        this.catRepository = catRepository;
//    }

    public Optional<Cat> findCat(long id) {
        return catRepository.findById(id);
    }

    public void saveCat(Cat cat) {
        catRepository.save(cat);
    }

//    public void updateCat(Cat cat) {
//        catRepository.update(cat);
//    }

    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }

    public Optional<Owner> findOwner(long id) {
        return catRepository.findOwnerById(id);
    }

    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }
}
