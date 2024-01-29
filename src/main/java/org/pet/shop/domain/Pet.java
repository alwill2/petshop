package org.pet.shop.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pet")
public class Pet
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String type;
    private String color;
    @JoinColumn(name = "father")
    @ManyToOne
    private Pet father;
    @JoinColumn(name = "mother" )
    @ManyToOne
    private Pet mother;

    public Pet() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Pet getFather() {
        return father;
    }

    public void setFather(Pet father) {
        this.father = father;
    }

    public Pet getMother() {
        return mother;
    }

    public void setMother(Pet mother) {
        this.mother = mother;
    }
}
