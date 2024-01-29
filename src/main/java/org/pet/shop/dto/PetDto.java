package org.pet.shop.dto;

public class PetDto
{
    private String name;
    private String type;
    private String color;
    private ParentDto parents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ParentDto getParents() {
        return parents;
    }

    public void setParents(ParentDto parents) {
        this.parents = parents;
    }
}
