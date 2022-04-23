package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final Generator generator = new Generator();
    @Autowired
    private CatService catService;

    @GetMapping("/get/{id}")
    public CatDto getCatById(@PathVariable int id) {
        Cat cat = catService.findCat(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        if (username.equals("admin") || Objects.equals(catService.findCat(id).getOwner().getName(), username)) {
            return generator.catToCatDto(cat);
        }

        return new CatDto();
    }

    @GetMapping("/get")
    public List<CatDto> getCats() {
        List<CatDto> catsDto = new ArrayList<>();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        for (Cat cat : catService.findAllCats()) {
            if (cat.getOwner() == null && !username.equals("admin")) {
                continue;
            }

            if (username.equals("admin") || Objects.equals(cat.getOwner().getName(), username)) {
                catsDto.add(generator.catToCatDto(cat));
            }
        }

        return catsDto;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCat(@RequestBody CatDto catDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        if (username.equals("admin")) {
            Cat cat = generator.dtoCatToCat(catDto);
            catService.saveCat(cat);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/update/{id}")
    public void updateCat(@PathVariable int id, String name) {
        Cat cat = catService.findCat(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if (username.equals("admin") || Objects.equals(catService.findCat(id).getOwner().getName(), username)) {
            cat.setName(name);
            catService.saveCat(cat);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable int id) {
        Cat cat = catService.findCat(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if (username.equals("admin")) {
            catService.deleteCat(cat);
        }
    }
}