package rkaniecki.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rkaniecki.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("")
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
