package rkaniecki.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rkaniecki.spring5recipeapp.commands.CategoryCommand;
import rkaniecki.spring5recipeapp.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    public CategoryCommand convert(Category source) {

        if (source != null) {
            final CategoryCommand categoryCommand = new CategoryCommand();
            categoryCommand.setId(source.getId());
            categoryCommand.setDescription(source.getDescription());

            return categoryCommand;
        }

        return null;
    }
}
