package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.Generator;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.WebOwner;
import ru.itmo.kotiki.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    private final Generator generator = new Generator();

    @GetMapping("/get/owner{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable long id) {
        return new ResponseEntity<>(ownerService.findOwner(id), HttpStatus.OK);
    }

    @GetMapping("/get/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<>(ownerService.findAllOwners(), HttpStatus.OK);
    }

    @PostMapping("/create/owner{id}")
    public ResponseEntity<?> createOwner(@RequestBody WebOwner webOwner) {
        Owner owner = generator.generateOwner(webOwner);
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/put/owner{id}")
    public ResponseEntity<?> updateOwner(@RequestBody WebOwner webOwner) {
        Owner owner = generator.generateOwner(webOwner);
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/owner{id}")
    public ResponseEntity<?> deleteOwner(@RequestBody WebOwner webOwner) {
        Owner owner = generator.generateOwner(webOwner);
        ownerService.deleteOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}