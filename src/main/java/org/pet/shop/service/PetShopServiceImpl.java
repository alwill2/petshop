package org.pet.shop.service;

import org.pet.shop.domain.Pet;
import org.pet.shop.dto.PetDto;
import org.pet.shop.mapper.PetMapper;
import org.pet.shop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

        if( petDto.getId() == null || !petRepository.findById(UUID.fromString(petDto.getId())).isPresent())
        {
            throw new RuntimeException("Bad request");
        }
        Pet existingPet = petMapper.petDtoToPet(petDto);
        existingPet = petRepository.save(existingPet);
        return petMapper.petToPetDto(existingPet);
    }

    @Override
    public PetDto findById(String petId) {
        Optional<Pet> existingPet = petRepository.findById(UUID.fromString(petId));
        if(!existingPet.isPresent())
        {
            throw new RuntimeException("Not Found");
        }
        return petMapper.petToPetDto(existingPet.get());
    }
}
