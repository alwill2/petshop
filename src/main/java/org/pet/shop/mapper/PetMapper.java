package org.pet.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.pet.shop.domain.Pet;
import org.pet.shop.dto.PetDto;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mappings({
            @Mapping(target = "parents.father", source = "petEntity.father"),
            @Mapping(target = "parents.mother", source = "petEntity.mother")
    })
    PetDto petToPetDto(Pet petEntity);

    @Mappings({
            @Mapping(target = "father", source = "petDto.parents.father"),
            @Mapping(target = "mother", source = "petDto.parents.mother")
    })
    Pet petDtoToPet(PetDto petDto);
}
