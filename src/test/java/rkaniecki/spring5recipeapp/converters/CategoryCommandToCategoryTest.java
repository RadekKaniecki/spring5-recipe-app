package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.CategoryCommand;
import rkaniecki.spring5recipeapp.domain.Category;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test";
    private CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void testConvert() {
        //given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(command);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}