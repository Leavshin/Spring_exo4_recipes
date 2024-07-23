package org.example.exercice4_recipes.controller;

import jakarta.validation.Valid;
import org.example.exercice4_recipes.model.Categorie;
import org.example.exercice4_recipes.model.Recette;
import org.example.exercice4_recipes.service.CategorieService;
import org.example.exercice4_recipes.service.RecetteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recettes")
public class RecetteController {

    private final RecetteService recetteService;
    private final CategorieService categorieService;

    public RecetteController(RecetteService recetteService, CategorieService categorieService) {
        this.recetteService = recetteService;
        this.categorieService = categorieService;
    }

    @GetMapping
    public String listRecettes(Model model) {
        List<Recette> recettes = recetteService.getAllRecettes();
        model.addAttribute("recettes", recettes);
        return "recette/list";
    }

    @GetMapping("/add")
    public String addRecetteForm(Model model) {
        model.addAttribute("recette", new Recette());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "recette/form";
    }

    @PostMapping("/add")
    public String addRecette(@Valid @ModelAttribute Recette recette, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categorieService.getAllCategories());
            return "recette/form";
        }
        Optional<Categorie> categorie = categorieService.getCategorieById(recette.getCategorie().getId());
        categorie.ifPresent(recette::setCategorie);
        recetteService.addRecette(recette);
        return "redirect:/recettes";
    }

    @GetMapping("/edit/{id}")
    public String editRecetteForm(@PathVariable Long id, Model model) {
        Optional<Recette> recette = recetteService.getRecetteById(id);
        if (recette.isPresent()) {
            model.addAttribute("recette", recette.get());
            model.addAttribute("categories", categorieService.getAllCategories());
            return "recette/form";
        }
        return "redirect:/recettes";
    }

    @PostMapping("/edit/{id}")
    public String updateRecette(@PathVariable Long id, @Valid @ModelAttribute Recette recette, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categorieService.getAllCategories());
            return "recette/form";
        }
        Optional<Categorie> categorie = categorieService.getCategorieById(recette.getCategorie().getId());
        categorie.ifPresent(recette::setCategorie);
        recette.setId(id); // Ensure the ID is set for the update
        recetteService.updateRecette(recette);
        return "redirect:/recettes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecette(@PathVariable Long id) {
        recetteService.deleteRecette(id);
        return "redirect:/recettes";
    }
}
