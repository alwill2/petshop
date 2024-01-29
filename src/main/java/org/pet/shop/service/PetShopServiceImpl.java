package org.pet.shop.service;

import org.pet.shop.domain.Pet;
import org.pet.shop.dto.PetDto;
import org.pet.shop.exception.PetShopExceptionBadRequest;
import org.pet.shop.exception.PetShopExceptionNotFound;
import org.pet.shop.mapper.PetMapper;
import org.pet.shop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetShopServiceImpl implements PetShopService{

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetRepository petRepository;
    @Override
    public PetDto create(PetDto petDto) {
        Pet newPet = petMapper.petDtoToPet(petDto);
        newPet = petRepository.save(newPet);
        return petMapper.petToPetDto(newPet);
    }

    @Override
    public PetDto updatePet(PetDto petDto) {

        if( petDto.getId() == null || !petRepository.findById(petDto.getId()).isPresent())
        {
            throw new PetShopExceptionBadRequest("Pet with Id: " +petDto.getId()  + ": does not exist");
        }
        Pet existingPet = petMapper.petDtoToPet(petDto);
        existingPet = petRepository.save(existingPet);
        return petMapper.petToPetDto(existingPet);
    }

    @Override
    public PetDto findById(String petId) {
        Optional<Pet> existingPet = petRepository.findById(petId);
        if(!existingPet.isPresent())
        {
            throw new PetShopExceptionNotFound("No Pets Found with Id : " +  petId);
        }
        return petMapper.petToPetDto(existingPet.get());
    }
}
