package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.Generator;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.WebOwner;
import ru.itmo.kotiki.service.OwnerServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerServiceImpl ownerService;

    private final Generator generator = new Generator();

    @GetMapping("{id}/")
    public ResponseEntity<?> getOwnerById(@PathVariable long id) {
        return new ResponseEntity<>(ownerService.findOwner(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<>(ownerService.findAllOwners(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createOwner(@RequestBody WebOwner webOwner) {
        Owner owner = generator.generateOwner(webOwner);
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}/")
    public ResponseEntity<?> updateOwner(@RequestBody WebOwner webOwner) {
        Owner owner = generator.generateOwner(webOwner);
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}/")
    public ResponseEntity<?> deleteOwner(@RequestBody WebOwner webOwner) {
        Owner owner = generator.generateOwner(webOwner);
        ownerService.deleteOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}