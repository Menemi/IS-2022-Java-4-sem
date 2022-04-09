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

    public Owner generateOwner(OwnerDto ownerDto) {
        return new Owner(ownerDto.getName(), ownerDto.getBirthDate());
    }

    public Cat generateCat(CatDto catDto) {
        return new Cat(catDto.getName(), catDto.getBirthDate(), catDto.getBreed(), catDto.getColor());
    }
}
