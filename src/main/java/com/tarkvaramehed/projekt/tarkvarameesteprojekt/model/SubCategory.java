package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubCategory {

    List<Product> products;
}
