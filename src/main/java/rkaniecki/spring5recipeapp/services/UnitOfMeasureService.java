package rkaniecki.spring5recipeapp.services;

import rkaniecki.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAllUomsCommand();
}
