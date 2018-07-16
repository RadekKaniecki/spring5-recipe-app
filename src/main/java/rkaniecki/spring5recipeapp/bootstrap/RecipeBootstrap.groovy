package rkaniecki.spring5recipeapp.bootstrap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import rkaniecki.spring5recipeapp.domain.Category
import rkaniecki.spring5recipeapp.domain.Difficulty
import rkaniecki.spring5recipeapp.domain.Ingredient
import rkaniecki.spring5recipeapp.domain.Notes
import rkaniecki.spring5recipeapp.domain.Recipe
import rkaniecki.spring5recipeapp.domain.UnitOfMeasure
import rkaniecki.spring5recipeapp.repositories.CategoryRepository
import rkaniecki.spring5recipeapp.repositories.RecipeRepository
import rkaniecki.spring5recipeapp.repositories.UnitOfMeasureRepository

@Component
class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository
    private final CategoryRepository categoryRepository
    private final RecipeRepository recipeRepository

    @Autowired
    RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository
        this.categoryRepository = categoryRepository
        this.recipeRepository = recipeRepository
    }

    @Override
    void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes())
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2)

        //retrieve uom from database
        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon")
        if (!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found")
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon")
        if (!tablespoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found")
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup")
        if (!cupOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found")
        }

        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch")
        if (!pinchOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found")
        }

        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce")
        if (!ounceOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found")
        }

        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("Each")
        if (!eachOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found")
        }

        //get unit of measures
        UnitOfMeasure teaspoon = teaspoonOptional.get()
        UnitOfMeasure tablespoon = tablespoonOptional.get()
        UnitOfMeasure cup = cupOptional.get()
        UnitOfMeasure pinch = pinchOptional.get()
        UnitOfMeasure ounce = ounceOptional.get()
        UnitOfMeasure each = eachOptional.get()

        //retrieve categories from database
        Optional<Category> americanOptional = categoryRepository.findByDescription("American")
        if (!americanOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found")
        }

        Optional<Category> italianOptional= categoryRepository.findByDescription("Italian")
        if (!italianOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found")
        }

        Optional<Category> mexicanOptional= categoryRepository.findByDescription("Mexican")
        if (!mexicanOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found")
        }

        Optional<Category> fastFoodOptional = categoryRepository.findByDescription("Fast Food")
        if (!fastFoodOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found")
        }

        //get categories
        Category american = americanOptional.get()
        Category italian = italianOptional.get()
        Category mexican = mexicanOptional.get()
        Category fastFood = fastFoodOptional.get()

        //perfect guacamole recipe
        Recipe guacamoleRecipe = new Recipe()
        guacamoleRecipe.description = ("Perfect Guacamole")
        guacamoleRecipe.prepTime = 10
        guacamoleRecipe.cookTime = 0
        guacamoleRecipe.difficulty = Difficulty.EASY
        guacamoleRecipe.directions = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."

        guacamoleRecipe.categories.add(mexican)
        guacamoleRecipe.categories.add(american)

        guacamoleRecipe.ingredients.add(new Ingredient(description: "ripe avocados", amount: 2, unitOfMeasure: each, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "salt", amount: 5, unitOfMeasure: teaspoon, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "fresh lime juice or lemon juice", amount: 2, unitOfMeasure: tablespoon, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "minced red onion or thinly sliced green onion", amount: 2, unitOfMeasure: tablespoon, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "serrano chillies, stems and seeds removed, minced", amount: 2, unitOfMeasure: each, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "Cilantro", amount: 2, unitOfMeasure: tablespoon, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "freshly grated black pepper", amount: 2, unitOfMeasure: pinch, recipe: guacamoleRecipe))
        guacamoleRecipe.ingredients.add(new Ingredient(description: "ripe tomato, seeds and pulp removed, chopped", amount: 0.5, unitOfMeasure: each, recipe: guacamoleRecipe))

        Notes guacamoleNotes = new Notes()
        guacamoleNotes.recipeNotes = "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great."
        guacamoleNotes.recipe = guacamoleRecipe
        guacamoleRecipe.notes = guacamoleNotes

        //add to return list
        recipes.add(guacamoleRecipe)

        recipes
    }
}
