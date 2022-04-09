package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.beans.CatBean;
import ru.itmo.kotiki.service.CatServiceImpl;
import ru.itmo.kotiki.model.Cat;

import java.sql.Date;
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

    @GetMapping("{id}/")
    public ResponseEntity<?> getCatById(@PathVariable long id) {
        return new ResponseEntity<>(catService.findCat(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Cat> getCats() {
        return catService.findAllCats();
    }

    @PostMapping("/")
    public ResponseEntity<?> createCat(@RequestBody CatBean catBean) {
        Cat cat = new Cat(catBean.getName(), catBean.getBirthDate(), catBean.getBreed(), catBean.getColor());
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}/")
    public ResponseEntity<?> updateCat(@RequestBody CatBean catBean) {
        Cat cat = new Cat(catBean.getName(), catBean.getBirthDate(), catBean.getBreed(), catBean.getColor());
        catService.saveCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}/")
    public ResponseEntity<?> deleteCat(@RequestBody CatBean catBean) {
        Cat cat = new Cat(catBean.getName(), catBean.getBirthDate(), catBean.getBreed(), catBean.getColor());
        catService.deleteCat(cat);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
