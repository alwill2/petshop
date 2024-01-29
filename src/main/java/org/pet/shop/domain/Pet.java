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
    private Pet father;
    private Pet mother;


}
