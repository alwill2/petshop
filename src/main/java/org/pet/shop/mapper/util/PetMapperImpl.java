package org.pet.shop.mapper.util;

import org.pet.shop.domain.Pet;
import org.pet.shop.dto.ParentDto;
import org.pet.shop.dto.PetDto;
import org.pet.shop.mapper.PetMapper;

public class PetMapperImpl implements PetMapper {
    @Override
    public PetDto petToPetDto(Pet petEntity) {
        if(petEntity == null){
            return null;
        }
        PetDto petDto = new PetDto();
        petDto.setName(petEntity.getName());
        petDto.setColor(petEntity.getColor());
        petDto.setType(petEntity.getType());
        petDto.setId(petEntity.getId());

        ParentDto parentDto = new ParentDto();
        parentDto.setFather(petEntity.getFather());
        parentDto.setMother(petEntity.getMother());

        petDto.setParents(parentDto);
        return petDto;
    }

    @Override
    public Pet petDtoToPet(PetDto petDto) {
        if(petDto == null)
        {
            return  null;
        }
        Pet pet = new Pet();
        pet.setColor(petDto.getColor());
        pet.setType(petDto.getType());
        pet.setName(petDto.getName());
        pet.setId(petDto.getId());
        pet.setFather(petDto.getParents().getFather());
        pet.setMother(petDto.getParents().getMother());
        return pet;
    }
}
