package com.tarkvaramehed.projekt.tarkvarameesteprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    Category category;
    List<SubCategory> subCategories;
}
