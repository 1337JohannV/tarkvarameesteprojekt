package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ean;
    private String producer;
    private String origin;

    @OneToMany
    private List<Price> prices;

}