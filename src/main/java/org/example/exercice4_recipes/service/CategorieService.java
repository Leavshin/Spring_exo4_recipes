package org.example.exercice4_recipes.service;

import org.example.exercice4_recipes.model.Categorie;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategorieService {

    private final Map<Long, Categorie> categories = new HashMap<>();
    private long nextId = 1;

    public List<Categorie> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public Optional<Categorie> getCategorieById(Long id) {
        return Optional.ofNullable(categories.get(id));
    }

    public void addCategorie(Categorie categorie) {
        categorie.setId(nextId++);
        categories.put(categorie.getId(), categorie);
    }

    public void updateCategorie(Categorie categorie) {
        if (categories.containsKey(categorie.getId())) {
            categories.put(categorie.getId(), categorie);
        }
    }

    public void deleteCategorie(Long id) {
        categories.remove(id);
    }
}
