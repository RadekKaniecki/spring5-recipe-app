package rkaniecki.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rkaniecki.spring5recipeapp.commands.CategoryCommand;
import rkaniecki.spring5recipeapp.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    public Category convert(CategoryCommand source) {

        if (source != null) {
            final Category category = new Category();
            category.setId(source.getId());
            category.setDescription(source.getDescription());

            return category;
        }

        return null;
    }
}
