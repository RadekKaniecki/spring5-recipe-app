package rkaniecki.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.exceptions.NotFoundException;
import rkaniecki.spring5recipeapp.services.RecipeService;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_FORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/show")
    public String showById(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_FORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return RECIPE_FORM_URL;
    }

    @PostMapping("recipe")
    public String saveOrUpdate(
            @Valid @ModelAttribute("recipe") RecipeCommand recipeCommand,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError ->
                log.error(objectError.toString()));

            return RECIPE_FORM_URL;
        }

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handing not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
