package org.example.exercice4_recipes.service;

import org.example.exercice4_recipes.model.Recette;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecetteService {

    private final Map<Long, Recette> recettes = new HashMap<>();
    private long nextId = 1;

    public List<Recette> getAllRecettes() {
        return new ArrayList<>(recettes.values());
    }

    public Optional<Recette> getRecetteById(Long id) {
        return Optional.ofNullable(recettes.get(id));
    }

    public void addRecette(Recette recette) {
        recette.setId(nextId++);
        recettes.put(recette.getId(), recette);
    }

    public void updateRecette(Recette recette) {
        if (recettes.containsKey(recette.getId())) {
            recettes.put(recette.getId(), recette);
        }
    }

    public void deleteRecette(Long id) {
        recettes.remove(id);
    }
}
