package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.Generator;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.service.CatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cats")
public class CatController {
    // http requests:
    // get - получает,
    // post - создаёт,
    // put / patch - изменяет,
    // delete - удаляет

    @Autowired
    private final Generator generator = new Generator();
    @Autowired
    private CatService catService;

    @GetMapping("/get/{id}")
    public CatDto getCatById(@PathVariable int id) {
        Cat cat = catService.findCat(id);
        return generator.catToCatDto(cat);
    }

    @GetMapping("/get")
    public List<CatDto> getCats() {
        List<Cat> cats = catService.findAllCats();
        List<CatDto> catsDto = new ArrayList<>();

        for (Cat cat : cats) {
            catsDto.add(generator.catToCatDto(cat));
        }

        return catsDto;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCat(@RequestBody CatDto catDto) {
        Cat cat = generator.dtoCatToCat(catDto);
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public void updateCat(@PathVariable int id, String name) {
        Cat cat = catService.findCat(id);

        if (!Objects.equals(cat, new Cat())) {
            cat.setName(name);
            catService.saveCat(cat);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable int id) {
        catService.deleteCat(catService.findCat(id));
    }
}