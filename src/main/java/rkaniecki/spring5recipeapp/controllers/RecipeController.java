package rkaniecki.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        log.debug("Saved recipeId: " + savedRecipe.getId());

        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @RequestMapping(method = {RequestMethod.DELETE, RequestMethod.GET}, value = "recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        log.debug("Deleting recipe id:" + id);
        recipeService.deleteById(id);

        return "redirect:/";
    }
}
