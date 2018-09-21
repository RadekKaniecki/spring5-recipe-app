package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.CategoryCommand;
import rkaniecki.spring5recipeapp.commands.IngredientCommand;
import rkaniecki.spring5recipeapp.commands.NotesCommand;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.domain.Difficulty;
import rkaniecki.spring5recipeapp.domain.Ingredient;
import rkaniecki.spring5recipeapp.domain.Notes;
import rkaniecki.spring5recipeapp.domain.Recipe;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test_description";
    private static final Integer PREPTIME = 2;
    private static final Integer COOKTIME = 3;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "test_source";
    private static final String URL = "test_url";
    private static final String DIRECTIONS = "test_directions";
    private static final Long NOTES_ID_VALUE = 5L;
    private static final Long INGREDIENT_ID_VALUE = 6L;
    private static final Long INGREDIENT_ID_VALUE_2 = 7L;
    private static final Long CATEGORY_ID_VALUE = 8L;
    private static final Long CATEGORY_ID_VALUE_2 = 9L;
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }


    @Test
    public void convertWithNullIngredientAndCategory() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREPTIME);
        recipeCommand.setCookTime(COOKTIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setIngredients(null);
        recipeCommand.setCategories(null);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID_VALUE);
        recipeCommand.setNotes(notes);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertNull(recipe.getCategories());
        assertNull(recipe.getIngredients());
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREPTIME, recipe.getPrepTime());
        assertEquals(COOKTIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(NOTES_ID_VALUE, recipe.getNotes().getId());
    }

    @Test
    public void convertWithNotNullIngredientAndCategory() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREPTIME);
        recipeCommand.setCookTime(COOKTIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID_VALUE);
        recipeCommand.setNotes(notesCommand);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGREDIENT_ID_VALUE);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGREDIENT_ID_VALUE_2);

        CategoryCommand category = new CategoryCommand();
        category.setId(CATEGORY_ID_VALUE);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CATEGORY_ID_VALUE_2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);
        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);


        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREPTIME, recipe.getPrepTime());
        assertEquals(COOKTIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(NOTES_ID_VALUE, recipe.getNotes().getId());
    }
}