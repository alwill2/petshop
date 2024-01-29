package org.pet.shop.mapper.util;

import org.pet.shop.domain.Pet;
import org.pet.shop.dto.PetDto;
import org.pet.shop.mapper.PetMapper;

public class PetMapperImpl implements PetMapper {
    @Override
    public PetDto petToPetDto(final Pet petEntity) {
        if(petEntity == null){
            return null;
        }
        PetDto petDto = new PetDto();
        petDto.setName(petEntity.getName());
        petDto.setColor(petEntity.getColor());
        petDto.setType(petEntity.getType());
        petDto.setId(petEntity.getId());
        return petDto;
    }

    @Override
    public Pet petDtoToPet(final PetDto petDto) {
        if(petDto == null)
        {
            return  null;
        }
        Pet pet = new Pet();
        pet.setColor(petDto.getColor());
        pet.setType(petDto.getType());
        pet.setName(petDto.getName());
        pet.setId(petDto.getId());
        return pet;
    }
}
