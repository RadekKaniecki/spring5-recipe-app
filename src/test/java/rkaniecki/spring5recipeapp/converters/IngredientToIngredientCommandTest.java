package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.IngredientCommand;
import rkaniecki.spring5recipeapp.domain.Ingredient;
import rkaniecki.spring5recipeapp.domain.Recipe;
import rkaniecki.spring5recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test_ingredient";
    private static final BigDecimal AMOUNT = new BigDecimal(1.0);
    private static final Long UOM_ID_VALUE = 1L;
    private static final String UOM_DESCRIPTION = "test_uom";
    private static final Long RECIPE_ID_VALUE = 1L;
    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convertWithNullUnitOfMeasureAndRecipe() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(null);
        ingredient.setRecipe(null);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertNull(command.getUnitOfMeasureCommand());
        assertNull(command.getRecipeId());
    }

    @Test
    public void convertWithUnitOfMeasureAndRecipe() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID_VALUE);

        ingredient.setRecipe(recipe);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID_VALUE);
        uom.setDescription(UOM_DESCRIPTION);

        ingredient.setUnitOfMeasure(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(RECIPE_ID_VALUE, ingredientCommand.getRecipeId());
        assertEquals(UOM_ID_VALUE, ingredientCommand.getUnitOfMeasureCommand().getId());
        assertEquals(UOM_DESCRIPTION, ingredientCommand.getUnitOfMeasureCommand().getDescription());

    }
}