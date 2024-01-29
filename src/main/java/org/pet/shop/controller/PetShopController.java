package org.pet.shop.controller;

import org.pet.shop.dto.PetDto;
import org.pet.shop.service.PetShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetShopController
{

    @Autowired
    private PetShopService petshopService;

    @PostMapping
    public ResponseEntity<PetDto> create(@RequestBody PetDto pet)
    {
        PetDto createdPet = petshopService.create(pet);
        return new ResponseEntity<>(createdPet,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> findPet(@PathVariable(name = "id", required = true) UUID petId)
    {
        PetDto pet = petshopService.findById();
        return new ResponseEntity<>(pet,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PetDto> updatePet(@RequestBody PetDto pet)
    {
        PetDto updatedPet = petshopService.updatePet(pet);
        return new ResponseEntity<>(updatedPet,HttpStatus.OK);
    }

}
