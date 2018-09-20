package rkaniecki.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source != null) {
            final Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setNotes(notesConverter.convert(source.getNotesCommand()));
            recipe.setDirections(source.getDirections());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setDescription(source.getDescription());
            recipe.setCookTime(source.getCookTime());
            recipe.setDifficulty(source.getDifficulty());
            recipe.setServings(source.getServings());
            recipe.setSource(source.getSource());
            recipe.setUrl(source.getUrl());

            if (source.getIngredients() != null && source.getIngredients().size() > 0) {
                source.getIngredients().forEach(
                        ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
            }

            if (source.getCategories() != null && source.getCategories().size() >0) {
                source.getCategories().forEach(
                        categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand))
                );
            }

            return recipe;
        }

        return null;
    }
}
