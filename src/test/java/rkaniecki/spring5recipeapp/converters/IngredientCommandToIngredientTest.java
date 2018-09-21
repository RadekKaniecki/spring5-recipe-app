package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.IngredientCommand;
import rkaniecki.spring5recipeapp.commands.UnitOfMeasureCommand;
import rkaniecki.spring5recipeapp.domain.Ingredient;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test_ingredient";
    private static final BigDecimal AMOUNT = new BigDecimal(1.0);
    private static final Long UOM_ID_VALUE = 1L;
    private static final String UOM_DESCRIPTION = "test_uom";
    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convertWithNullUnitOfMeasuer() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        command.setUnitOfMeasureCommand(null);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNull(ingredient.getUnitOfMeasure());
    }

    @Test
    public void convertWithUnitOfMeasure() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID_VALUE);
        uomCommand.setDescription(UOM_DESCRIPTION);

        ingredientCommand.setUnitOfMeasureCommand(uomCommand);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID_VALUE, ingredient.getUnitOfMeasure().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUnitOfMeasure().getDescription());
    }
}