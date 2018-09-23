package rkaniecki.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rkaniecki.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String showList(@PathVariable Long recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id:" + recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }
}
