package ru.itmo.kotiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

//    public OwnerService(OwnerRepository ownerRepository) {
//        this.ownerRepository = ownerRepository;
//    }

    // todo: точно также, как тут
    public Optional<Owner> findOwner(long id) {
        return ownerRepository.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    // todo: == save
//    public void updateOwner(Owner owner) {
//        ownerRepository.update(owner);
//    }

    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

    // todo: пофиксить хуйню
    public Optional<Cat> findCat(long id) {
        return ownerRepository.findCatById(id);
    }

    public void removeCat(Owner owner, Cat cat) {
        owner.removePet(cat);
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }
}
