package org.example.exercice4_recipes.controller;

import jakarta.validation.Valid;
import org.example.exercice4_recipes.model.Categorie;
import org.example.exercice4_recipes.service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public String listCategories(Model model) {
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categorie/list";
    }

    @GetMapping("/add")
    public String addCategorieForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categorie/form";
    }

    @PostMapping({"/add", "/edit/{id}"})
    public String saveCategorie(@PathVariable(required = false) Long id, @Valid @ModelAttribute Categorie categorie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categorie/form";
        }
        if (id != null) {
            categorie.setId(id); // Ensure the ID is set for the update
            categorieService.updateCategorie(categorie);
        } else {
            categorieService.addCategorie(categorie);
        }
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategorieForm(@PathVariable Long id, Model model) {
        Optional<Categorie> categorie = categorieService.getCategorieById(id);
        if (categorie.isPresent()) {
            model.addAttribute("categorie", categorie.get());
            return "categorie/form";
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return "redirect:/categories";
    }
}
