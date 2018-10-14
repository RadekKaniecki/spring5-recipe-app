package rkaniecki.spring5recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rkaniecki.spring5recipeapp.converters.IngredientCommandToIngredient;
import rkaniecki.spring5recipeapp.converters.IngredientToIngredientCommand;
import rkaniecki.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import rkaniecki.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import rkaniecki.spring5recipeapp.domain.Ingredient;
import rkaniecki.spring5recipeapp.repositories.IngredientRepository;
import rkaniecki.spring5recipeapp.repositories.RecipeRepository;
import rkaniecki.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientRepository,
                ingredientToIngredientCommand,
                ingredientCommandToIngredient,
                recipeRepository,
                unitOfMeasureRepository);
    }

    @Test
    public void findById() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Optional<Ingredient> ingredientOptional = Optional.of(ingredient);

        when(ingredientRepository.findById(anyLong())).thenReturn(ingredientOptional);

        //when
        Ingredient foundIngredient = ingredientService.findById(1L);

        //then
        assertEquals(Long.valueOf(1), foundIngredient.getId());
        verify(ingredientRepository, times(1)).findById(anyLong());
    }
}