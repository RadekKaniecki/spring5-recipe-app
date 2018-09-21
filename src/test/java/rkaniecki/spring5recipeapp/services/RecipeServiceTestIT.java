package rkaniecki.spring5recipeapp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rkaniecki.spring5recipeapp.commands.RecipeCommand;
import rkaniecki.spring5recipeapp.converters.RecipeCommandToRecipe;
import rkaniecki.spring5recipeapp.converters.RecipeToRecipeCommand;
import rkaniecki.spring5recipeapp.domain.Recipe;
import rkaniecki.spring5recipeapp.repositories.RecipeRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTestIT {

    private static final String NEW_DESCRIPTION = "New description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Test
    @Transactional
    public void testSaveDescription() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
    }
}