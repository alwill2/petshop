package org.pet.shop.service;

import org.pet.shop.dto.PetDto;

public interface PetShopService {
    PetDto create(PetDto pet);

    PetDto updatePet(PetDto pet);

    PetDto findById();
}
