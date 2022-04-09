package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.Generator;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.service.OwnerServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerServiceImpl ownerService;

    private final Generator generator = new Generator();

    @GetMapping("{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable long id) {
        return new ResponseEntity<>(ownerService.findOwner(id), HttpStatus.OK);
    }

    @GetMapping("")
    public List<Owner> getOwners() {
        return ownerService.findAllOwners();
    }

    @PostMapping("")
    public ResponseEntity<?> createOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = generator.generateOwner(ownerDto);
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = generator.generateOwner(ownerDto);
        ownerService.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = generator.generateOwner(ownerDto);
        ownerService.deleteOwner(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}