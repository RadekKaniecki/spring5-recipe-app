package rkaniecki.spring5recipeapp.services;

import rkaniecki.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}