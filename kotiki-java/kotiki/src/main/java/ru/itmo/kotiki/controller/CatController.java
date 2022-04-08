package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.Generator;
import ru.itmo.kotiki.model.WebCat;
import ru.itmo.kotiki.service.CatServiceImpl;
import ru.itmo.kotiki.model.Cat;

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

    @GetMapping("{id}/")
    public ResponseEntity<?> getCatById(@PathVariable long id) {
        return new ResponseEntity<>(catService.findCat(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Cat> getCats() {
        var cats = catService.findAllCats();
        return catService.findAllCats();
//        return new ResponseEntity<>(catService.findAllCats(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCat(@RequestBody WebCat webCat) {
        Cat cat = generator.generateCat(webCat);
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}/")
    public ResponseEntity<?> updateCat(@RequestBody WebCat webCat) {
        Cat cat = generator.generateCat(webCat);
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}/")
    public ResponseEntity<?> deleteCat(@RequestBody WebCat webCat) {
        Cat cat = generator.generateCat(webCat);
        catService.deleteCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
