package org.example.exercice4_recipes.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Recette {
    private Long id;

    @NotBlank(message = "Le nom de la recette ne peut pas être vide.")
    private String nom;

    @NotNull(message = "La liste des ingrédients ne peut pas être vide.")
    private List<String> ingredients;

    @NotBlank(message = "Les instructions ne peuvent pas être vides.")
    private String instructions;

    @NotNull(message = "La catégorie ne peut pas être vide.")
    private Categorie categorie;
}
