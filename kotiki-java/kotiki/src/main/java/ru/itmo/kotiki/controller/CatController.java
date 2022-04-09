package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.Generator;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.service.CatServiceImpl;
import ru.itmo.kotiki.model.Cat;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    // http requests:
    // get - получает,
    // post - создаёт,
    // put / patch - изменяет,
    // delete - удаляет

    @Autowired
    private CatServiceImpl catService;

    private final Generator generator = new Generator();

    @GetMapping("/get/{id}")
    public CatDto getCatById(@PathVariable int id) {
        return generator.catToCatDto(catService.findCat(id));
    }

    @GetMapping("/get")
    public List<CatDto> getCats() {
        List<CatDto> catsDto = new ArrayList<>();
        for (Cat cat : catService.findAllCats()) {
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

    @PutMapping("/update{id}")
    public ResponseEntity<?> updateCat(@RequestBody CatDto catDto) {
        Cat cat = generator.dtoCatToCat(catDto);
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable int id) {
        catService.deleteCat(catService.findCat(id));
    }
}
