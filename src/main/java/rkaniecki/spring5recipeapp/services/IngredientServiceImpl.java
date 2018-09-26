package rkaniecki.spring5recipeapp.services;

import org.springframework.stereotype.Service;
import rkaniecki.spring5recipeapp.commands.IngredientCommand;
import rkaniecki.spring5recipeapp.converters.IngredientToIngredientCommand;
import rkaniecki.spring5recipeapp.domain.Ingredient;
import rkaniecki.spring5recipeapp.repositories.IngredientRepository;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public Ingredient findById(Long id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

        if (!optionalIngredient.isPresent()) {
            throw new RuntimeException("Couldn't find ingredient by id " + id);
        }

        return optionalIngredient.get();
    }

    @Override
    public IngredientCommand findCommandById(Long id) {
        return ingredientToIngredientCommand.convert(findById(id));
    }
}
