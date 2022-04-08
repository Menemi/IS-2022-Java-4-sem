package ru.itmo.kotiki;

import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.WebCat;
import ru.itmo.kotiki.model.WebOwner;

public class Generator {
    public Generator() {
    }

    public Owner generateOwner(WebOwner webOwner) {
        return new Owner(webOwner.getName(), webOwner.getBirthDate());
    }

    public Cat generateCat(WebCat webCat) {
        return new Cat(webCat.getName(), webCat.getBirthDate(), webCat.getBreed(), webCat.getColor());
    }
}
