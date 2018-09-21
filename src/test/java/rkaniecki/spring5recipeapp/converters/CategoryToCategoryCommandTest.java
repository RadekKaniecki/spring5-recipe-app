package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.CategoryCommand;
import rkaniecki.spring5recipeapp.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test";
    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void testConvert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand command = converter.convert(category);

        //then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}