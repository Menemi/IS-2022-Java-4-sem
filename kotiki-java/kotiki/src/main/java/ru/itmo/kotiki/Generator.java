package ru.itmo.kotiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

@Component
public class Generator {
    @Autowired
    public Generator() {
    }

    public Owner dtoOwnerToOwner(OwnerDto ownerDto) {
        return new Owner(ownerDto.getName(), ownerDto.getBirthDate());
    }

    public Cat dtoCatToCat(CatDto catDto) {
        return new Cat(catDto.getName(), catDto.getBirthDate(), catDto.getBreed(), catDto.getColor());
    }

    public CatDto catToCatDto(Cat cat) {
        return new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
    }

    public OwnerDto ownerToOwnerDto(Owner owner) {
        return new OwnerDto(owner.getName(), owner.getBirthDate());
    }
}
