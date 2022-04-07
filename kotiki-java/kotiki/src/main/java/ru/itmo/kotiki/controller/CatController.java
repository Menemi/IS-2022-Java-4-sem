package ru.itmo.kotiki.controller;

import org.hibernate.type.CharArrayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.CatService;
import ru.itmo.kotiki.model.Cat;

import java.util.Map;

@RestController
public class CatController {
    // http requests:
    // get - получает,
    // post - создаёт,
    // put / patch - изменяет,
    // delete - удаляет

    @Autowired
    private CatService catService;

//    public CatController(CatService catService) {
//        this.catService = catService;
//    }

    @PostMapping("/api/kotiki/new")
    public ResponseEntity<?> createCat(@RequestBody Cat cat) {
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/test/{str}")
    public ResponseEntity<?> test(@PathVariable String str) {
        return new ResponseEntity<>(Map.of("String", str), HttpStatus.OK);
    }
}
