package org.example.exercice4_recipes.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categorie {
    private Long id;

    @NotBlank(message = "Le nom de la catégorie ne peut pas être vide.")
    private String nom;

    @NotBlank(message = "La description ne peut pas être vide.")
    private String description;
}
