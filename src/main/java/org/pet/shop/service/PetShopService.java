package org.pet.shop.service;

import org.pet.shop.dto.PetDto;

public interface PetShopService {
    PetDto create(final PetDto pet);

    PetDto updatePet(final PetDto pet);

    PetDto findById(final String petId);
}
