package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.domain.Category;
import rkaniecki.spring5recipeapp.domain.Difficulty;
import rkaniecki.spring5recipeapp.domain.Ingredient;
import rkaniecki.spring5recipeapp.domain.Notes;
import rkaniecki.spring5recipeapp.domain.Recipe;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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
    private RecipeToRecipeCommand converter;


    @Before
    public void setUp() throws Exception {
        this.converter = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convertWithNullIngredientAndCategory() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREPTIME);
        recipe.setCookTime(COOKTIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setIngredients(null);
        recipe.setCategories(null);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID_VALUE);
        recipe.setNotes(notes);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNull(command.getCategories());
        assertNull(command.getIngredients());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREPTIME, command.getPrepTime());
        assertEquals(COOKTIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(NOTES_ID_VALUE, command.getNotes().getId());
    }

    @Test
    public void convertWithNotNullIngredientAndCategory() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREPTIME);
        recipe.setCookTime(COOKTIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID_VALUE);
        recipe.setNotes(notes);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_VALUE);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_VALUE_2);

        Category category = new Category();
        category.setId(CATEGORY_ID_VALUE);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID_VALUE_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);
        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);


        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertEquals(2, command.getIngredients().size());
        assertEquals(2, command.getCategories().size());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREPTIME, command.getPrepTime());
        assertEquals(COOKTIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(NOTES_ID_VALUE, command.getNotes().getId());
    }
}