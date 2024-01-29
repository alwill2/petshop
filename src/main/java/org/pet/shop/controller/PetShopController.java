package org.pet.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.pet.shop.dto.PetDto;
import org.pet.shop.service.PetShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetShopController
{
    @Autowired
    private PetShopService petshopService;

    @PostMapping
    @Operation(summary = "Create Pet", description = "Endpoint used to create a new pet")
    @ApiResponse(responseCode = "201", description = "Pet successfully created")
    public ResponseEntity<PetDto> create(@RequestBody final PetDto pet)
    {
        PetDto createdPet = petshopService.create(pet);
        return new ResponseEntity<>(createdPet,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get single Pet", description = "Endpoint used to get an existing pet using pet id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet found"),
            @ApiResponse(responseCode = "404", description = "Pet Not found")
    })
    public ResponseEntity<PetDto> findPet(@PathVariable(name = "id", required = true) final String petId)
    {
        PetDto pet = petshopService.findById(petId);
        return new ResponseEntity<>(pet,HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update a Pet", description = "Endpoint used to update an existing pet using pet. Pet Id is required")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet Updated"),
            @ApiResponse(responseCode = "400", description = "Pet Not found")
    })
    public ResponseEntity<PetDto> updatePet(@RequestBody final PetDto pet)
    {
        PetDto updatedPet = petshopService.updatePet(pet);
        return new ResponseEntity<>(updatedPet,HttpStatus.OK);
    }

}
