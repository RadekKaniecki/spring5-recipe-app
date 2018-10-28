package rkaniecki.spring5recipeapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rkaniecki.spring5recipeapp.commands.IngredientCommand;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.commands.UnitOfMeasureCommand;
import rkaniecki.spring5recipeapp.services.IngredientService;
import rkaniecki.spring5recipeapp.services.RecipeService;
import rkaniecki.spring5recipeapp.services.UnitOfMeasureService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

    private IngredientController ingredientController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientController = new IngredientController(
                recipeService,
                ingredientService,
                unitOfMeasureService);

        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testShowIngredientList() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"));
    }

    @Test
    public void testShowIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        when(ingredientService.findCommandById(anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(view().name("recipe/ingredient/show"));
    }

    @Test
    public void testUpdateIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        Set<UnitOfMeasureCommand> uomCommandList = new HashSet<>();
        uomCommandList.add(new UnitOfMeasureCommand());

        when(ingredientService.findCommandById(anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.findAllUomsCommand()).thenReturn(uomCommandList);

        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient", "uomList"))
                .andExpect(view().name("recipe/ingredient/ingredientform"));
    }

    @Test
    public void testAddNewIngredient() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        Set<UnitOfMeasureCommand> uomCommandList = new HashSet<>();

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.findAllUomsCommand()).thenReturn(uomCommandList);

        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient", "uomList"))
                .andExpect(view().name("recipe/ingredient/ingredientform"));
    }

    @Test
    public void testSaveOrUpdateIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(2L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "any description")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/1/show"));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(1L);

        when(ingredientService.findCommandById(anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));

        verify(ingredientService, times(1)).deleteById(anyLong());
    }
}