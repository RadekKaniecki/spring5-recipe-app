package rkaniecki.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if (source != null) {
            final RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(source.getId());
            recipeCommand.setCookTime(source.getCookTime());
            recipeCommand.setPrepTime(source.getPrepTime());
            recipeCommand.setServings(source.getServings());
            recipeCommand.setSource(source.getSource());
            recipeCommand.setUrl(source.getUrl());
            recipeCommand.setDescription(source.getDescription());
            recipeCommand.setDifficulty(source.getDifficulty());
            recipeCommand.setDirections(source.getDirections());
            recipeCommand.setImage(source.getImage());
            recipeCommand.setNotes(notesConverter.convert(source.getNotes()));

            if (source.getCategories() != null && source.getCategories().size() > 0) {
                source.getCategories().forEach(
                        category -> recipeCommand.getCategories().add(categoryConverter.convert(category))
                );
            } else {
                recipeCommand.setCategories(null);
            }

            if (source.getIngredients() != null && source.getIngredients().size() > 0) {
                source.getIngredients().forEach(
                        ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient))
                );
            } else {
                recipeCommand.setIngredients(null);
            }

            return recipeCommand;

        }
        return null;
    }
}
