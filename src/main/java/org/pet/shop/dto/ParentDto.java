package org.pet.shop.dto;

import java.util.UUID;

public class ParentDto
{
    private UUID father;
    private UUID mother;

    public UUID getFather() {
        return father;
    }

    public void setFather(UUID father) {
        this.father = father;
    }

    public UUID getMother() {
        return mother;
    }

    public void setMother(UUID mother) {
        this.mother = mother;
    }
}
