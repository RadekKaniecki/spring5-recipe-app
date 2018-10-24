package rkaniecki.spring5recipeapp.services;

import rkaniecki.spring5recipeapp.commands.IngredientCommand;
import rkaniecki.spring5recipeapp.domain.Ingredient;

public interface IngredientService {

    Ingredient findById(Long id);

    IngredientCommand findCommandById(Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long id);
}
