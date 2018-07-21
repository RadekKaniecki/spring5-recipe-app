package rkaniecki.spring5recipeapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import rkaniecki.spring5recipeapp.domain.Recipe;
import rkaniecki.spring5recipeapp.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        //Given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

        //When
        String indexPage = indexController.getIndexPage(model);

        //Then
        assertEquals("index", indexPage);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        Set<Recipe> recipeSetInGetIndexPage = captor.getValue();
        assertEquals(2, recipeSetInGetIndexPage.size());
    }
}